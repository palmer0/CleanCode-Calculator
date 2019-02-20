package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.basic.BasicActivity;

public class StandardRouter implements StandardContract.Router {

  public static String TAG = StandardRouter.class.getSimpleName();

  private AppMediator mediator;

  public StandardRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToBasicScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, BasicActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passStateToBasicScreen(
      SharedState state, List<SharedState> history) {
    mediator.state = state;
    mediator.history = history;
  }

  @Override
  public SharedState getStateFromBasicScreen() {
    SharedState state = mediator.state;
    mediator.state = null;
    return state;
  }

  @Override
  public List<SharedState> getHistoryFromBasicScreen() {
    List<SharedState> history = mediator.history;
    mediator.history = null;
    return history;
  }
}
