package es.ulpgc.eite.cleancode.calculator.basic;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.CommandState;
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
      CommandState state, List<CommandState> history) {
    mediator.commandState = state;
    mediator.commandHistory = history;
  }


  @Override
  public CommandState getStateFromStandardScreen() {
    CommandState state = mediator.commandState;
    mediator.commandState = null;
    return state;
  }

  @Override
  public List<CommandState> getHistoryFromStandardScreen() {
    List<CommandState> history = mediator.commandHistory;
    mediator.commandHistory = null;
    return history;
  }

}
