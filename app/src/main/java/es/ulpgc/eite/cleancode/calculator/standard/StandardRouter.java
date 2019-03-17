package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
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
  public void passStateToBasicScreen(CalculatorState state) {
    mediator.calculatorState = state;
  }

  @Override
  public CalculatorState getStateFromBasicScreen() {
    CalculatorState state = mediator.calculatorState;
    mediator.calculatorState = null;
    return state;
  }

  /*
  @Override
  public void passStateToBasicScreen(
      CalculatorState state, List<CalculatorState> history) {
    mediator.calculatorState = state;
    mediator.commandHistory = history;
  }

  @Override
  public List<CalculatorState> getHistoryFromBasicScreen() {
    List<CalculatorState> history = mediator.commandHistory;
    mediator.commandHistory = null;
    return history;
  }
  */

}
