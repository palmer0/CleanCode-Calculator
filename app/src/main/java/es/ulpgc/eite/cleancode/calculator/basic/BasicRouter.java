package es.ulpgc.eite.cleancode.calculator.basic;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
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
  public void passStateToStandardScreen(CalculatorState state) {
    mediator.calculatorState = state;
  }

  @Override
  public CalculatorState getStateFromStandardScreen() {
    CalculatorState state = mediator.calculatorState;
    mediator.calculatorState = null;
    return state;
  }

  /*
  @Override
  public void passStateToStandardScreen(
      CalculatorState state, List<CalculatorState> history) {
    mediator.calculatorState = state;
    mediator.commandHistory = history;
  }


  @Override
  public List<CalculatorState> getHistoryFromStandardScreen() {
    List<CalculatorState> history = mediator.commandHistory;
    mediator.commandHistory = null;
    return history;
  }
  */

}
