package es.ulpgc.eite.cleancode.calculator.basic.mocks;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.CommandState;
import es.ulpgc.eite.cleancode.calculator.basic.BasicContract;

public class MockBasicRouter implements BasicContract.Router {

  @Override
  public void navigateToStandardScreen() {

  }

  @Override
  public void passStateToStandardScreen
      (CommandState state, List<CommandState> history) {

  }

  @Override
  public CommandState getStateFromStandardScreen() {
    return null;
  }

  @Override
  public List<CommandState> getHistoryFromStandardScreen() {
    return null;
  }
}
