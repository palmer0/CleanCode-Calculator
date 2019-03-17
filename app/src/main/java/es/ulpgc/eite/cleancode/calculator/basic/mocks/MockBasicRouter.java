package es.ulpgc.eite.cleancode.calculator.basic.mocks;

import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
import es.ulpgc.eite.cleancode.calculator.basic.BasicContract;

public class MockBasicRouter implements BasicContract.Router {

  @Override
  public void navigateToStandardScreen() {

  }

  @Override
  public void passStateToStandardScreen(CalculatorState state) {

  }

  @Override
  public CalculatorState getStateFromStandardScreen() {
    return null;
  }

  /*
  @Override
  public void passStateToStandardScreen
      (CalculatorState state, List<CalculatorState> history) {

  }

  @Override
  public List<CalculatorState> getHistoryFromStandardScreen() {
    return null;
  }
  */
}
