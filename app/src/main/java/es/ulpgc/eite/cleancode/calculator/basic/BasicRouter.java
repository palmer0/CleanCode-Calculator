package es.ulpgc.eite.cleancode.calculator.basic;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.standard.StandardActivity;

public class BasicRouter implements BasicContract.Router {

  public static String TAG = BasicRouter.class.getSimpleName();

  private AppMediator mediator;

  public BasicRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToStandardScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, StandardActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passStateToStandardScreen(
      SharedState state, List<SharedState> history) {
    mediator.state = state;
    mediator.history = history;
  }


  @Override
  public SharedState getStateFromStandardScreen() {
    SharedState state = mediator.state;
    mediator.state = null;
    return state;
  }

  @Override
  public List<SharedState> getHistoryFromStandardScreen() {
    List<SharedState> history = mediator.history;
    mediator.history = null;
    return history;
  }

}
