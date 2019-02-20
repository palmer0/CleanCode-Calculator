package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.CommandState;
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
      CommandState state, List<CommandState> history) {
    mediator.commandState = state;
    mediator.commandHistory = history;
  }

  @Override
  public CommandState getStateFromBasicScreen() {
    CommandState state = mediator.commandState;
    mediator.commandState = null;
    return state;
  }

  @Override
  public List<CommandState> getHistoryFromBasicScreen() {
    List<CommandState> history = mediator.commandHistory;
    mediator.commandHistory = null;
    return history;
  }
}
