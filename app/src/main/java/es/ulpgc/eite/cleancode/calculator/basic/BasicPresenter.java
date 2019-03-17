package es.ulpgc.eite.cleancode.calculator.basic;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainPresenter;

public class BasicPresenter
    extends BrainPresenter implements BasicContract.Presenter {

  public static String TAG = BasicPresenter.class.getSimpleName();

  private WeakReference<BasicContract.View> view;
  private BasicViewModel viewModel;
  private BasicState state;
  private BasicContract.Router router;

  public BasicPresenter(BasicState state) {
    this.state = state;
    viewModel = state;
  }


  protected void updateDisplay() {
    view.get().display(getDisplay());

  }

  protected void displayWarning(String text) {
    view.get().displayWarning(text);
  }


  @Override
  public void start() {
    model.reset();

    /*
    List<CalculatorState> history = router.getHistoryFromStandardScreen();
    if(history != null) {
      commandHistory.addAll(history);
    }
    */

    CalculatorState calcState = router.getStateFromStandardScreen();
    if(calcState != null) {

      /*
      //setCommands(calcState.commands);
      setResult(calcState.result);
      setSavedOperator(calcState.operator);
      setNumber(calcState.number);
      setDisplay(calcState.display);
      */

      model.setCommands(calcState.commands);
      model.setResult(calcState.result);
      model.setOperator(calcState.operator);
      model.setNumber(Integer.parseInt(calcState.number));
      setBackspaceEnabled(calcState.backspaceEnabled);
      setDisplay(calcState.display);

      updateDisplay();

      return;
    }

    /*
    //setCommands(state.commands);
    setResult(state.result);
    setSavedOperator(state.operator);
    setNumber(state.number);
    setDisplay(state.display);
    */

    model.setCommands(state.commands);
    model.setResult(state.result);
    model.setOperator(state.operator);
    model.setNumber(Integer.parseInt(state.number));
    setBackspaceEnabled(viewModel.backspaceEnabled);
    setDisplay(viewModel.display);

    updateDisplay();
  }




  @Override
  public void configChanged() {
    //Log.e(TAG, "configChanged()");

    /*
    state.result = model.getResult();

    CalculatorState calcState = new CalculatorState();
    calcState.number = state.number;
    calcState.operator = state.operator;
    calcState.result = state.result;
    calcState.display = viewModel.display;
    */

    CalculatorState calcState = new CalculatorState();
    calcState.operator = model.getOperator();
    calcState.number = String.valueOf(model.getNumber());
    calcState.result = model.getResult();
    calcState.commands = model.getCommands();
    calcState.display = viewModel.display;
    calcState.backspaceEnabled = viewModel.backspaceEnabled;

    router.passStateToStandardScreen(calcState);
    //router.passStateToStandardScreen(calculatorState, commandHistory);
    router.navigateToStandardScreen();
    view.get().finishStandardScreen();

  }

  @Override
  public void buttonClicked(String button) {

    /*
    if (!button.equals("Undo")) {
      commandExecuted();
    }
    */

    try {

      Integer.parseInt(button);
      digitPressed(button);

    } catch (NumberFormatException ex) {

      if (button.equals("+") || button.equals("-") || button.equals("=")) {
        operatorPressed(button);
      } else if (button.equals("Clr")) {
        clearPressed();
      } else if (button.equals("Del")) {
        backspacePressed();
      } else if (button.equals("Undo")) {
        undoPressed();
      } else {
        displayWarning("Wrong number");
      }
    }

  }

  /*
  private void commandExecuted() {
    //Log.e(TAG, "commandExecuted()");

    state.result = model.getResult();

    CalculatorState calculatorState = new CalculatorState();
    calculatorState.number = viewModel.number;
    calculatorState.display = viewModel.display;
    calculatorState.operator = viewModel.operator;
    calculatorState.result = state.result;

//    Log.e(TAG, "display: " + viewModel.display);
//    Log.e(TAG, "number: " + viewModel.number);
//    Log.e(TAG, "operand: " + viewModel.operator);
//    Log.e(TAG, "result: " + state.result);

    commandExecuted(calculatorState);
  }
  */

  private void saveState() {
    state.operator = model.getOperator();
    state.number = String.valueOf(model.getNumber());
    state.result = model.getResult();

    state.commands = model.getCommands();
  }

  @Override
  public void stop() {
    Log.e(TAG, "stop()");
    saveState();
  }

  @Override
  public void undoPressed() {
    //Log.e(TAG, "undoPressed()");

    model.undo();
    saveState();

    /*
    state.operator = model.getSavedOperator();
    state.number = String.valueOf(model.getNumber());
    state.result = model.getResult();
    */

    /*
    CalculatorState state = commandUndo();
    if(state == null) {
      return;
    }

    setResult(state.result);
    setSavedOperator(state.operator);
    setNumber(state.number);
    setDisplay(state.display);
    */

    setDisplay(String.valueOf(model.getResult()));
    updateDisplay();
  }

  protected String getDisplay() {
    return viewModel.display;
  }

  protected void setDisplay(String display) {
    //Log.e(TAG, "display: " + display);
    viewModel.display = display;
  }

  protected void setBackspaceEnabled(boolean enabled) {
    viewModel.backspaceEnabled = enabled;
  }

  protected boolean isBackspaceEnabled(){
    return viewModel.backspaceEnabled;
  }

//  protected void setNumber(String number) {
//    //Log.e(TAG, "number: " + number);
//    model.setNumber(Integer.parseInt(number));
//    state.number = number;
//  }
//
//  protected void setSavedOperator(String operator) {
//    //Log.e(TAG, "operator: " + operator);
//    model.setSavedOperator(operator);
//    state.operator = operator;
//  }
//
//
//  protected void setResult(Integer result) {
//    //Log.e(TAG, "result: " + result);
//    model.setResult(result);
//    state.result = result;
//  }
//
//  protected String getNumber() {
//    return state.number;
//  }
//
//  protected String getSavedOperator() {
//    return state.operator;
//  }

  /*
  private void setCommands(List<BrainCommand> commands) {
    model.setCommands(commands);
  }
  */

  @Override
  public void injectView(WeakReference<BasicContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(BrainContract.Model model) {
    this.model = model;
  }


  @Override
  public void injectRouter(BasicContract.Router router) {
    this.router = router;
  }


}
