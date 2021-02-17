package es.ulpgc.eite.cleancode.calculator.basic;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainPresenter;

public class BasicPresenter
    extends BrainPresenter implements BasicContract.Presenter {

  public static String TAG = BasicPresenter.class.getSimpleName();

  private WeakReference<BasicContract.View> view;
  private BasicState state;
  //private BasicContract.Router router;
  private AppMediator mediator;

  public BasicPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.basicState;
  }


//  public BasicPresenter(BasicState state) {
//    this.state = state;
//  }


  protected void updateDisplay() {
    view.get().display(getDisplay());

  }

  protected void displayWarning(String text) {
    view.get().displayWarning(text);
  }


  @Override
  public void start() {
    model.reset();

    //CalculatorState calcState = router.getStateFromStandardScreen();
    CalculatorState calcState = getStateFromStandardScreen();
    if(calcState != null) {

      model.setCommandList(calcState.commandList);
      model.setResult(calcState.result);
      model.setOperator(calcState.operator);
      model.setNumber(Integer.parseInt(calcState.number));
      setBackspaceEnabled(calcState.backspaceEnabled);
      setDisplay(calcState.display);

      updateDisplay();

      return;
    }

    model.setCommandList(state.commandList);
    model.setResult(state.result);
    model.setOperator(state.operator);
    model.setNumber(Integer.parseInt(state.number));
    setBackspaceEnabled(state.backspaceEnabled);
    setDisplay(state.display);

    updateDisplay();
  }


  private void passStateToStandardScreen(CalculatorState state) {
    mediator.calculatorState = state;
  }

  private CalculatorState getStateFromStandardScreen() {
    CalculatorState state = mediator.calculatorState;
    mediator.calculatorState = null;
    return state;
  }

  @Override
  public void configChanged() {
    //Log.e(TAG, "configChanged()");

    CalculatorState calcState = new CalculatorState();
    calcState.operator = model.getOperator();
    calcState.number = String.valueOf(model.getNumber());
    calcState.result = model.getResult();
    calcState.commandList = model.getCommandList();
    calcState.display = state.display;
    calcState.backspaceEnabled = state.backspaceEnabled;

//    router.passStateToStandardScreen(calcState);
//    router.navigateToStandardScreen();
    passStateToStandardScreen(calcState);
    view.get().navigateToStandardScreen();
    view.get().finishStandardScreen();

  }

  @Override
  public void buttonClicked(String button) {

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


  private void updateState() {
    state.operator = model.getOperator();
    state.number = String.valueOf(model.getNumber());
    state.result = model.getResult();

    state.commandList = model.getCommandList();
  }

  @Override
  public void stop() {
    //Log.e(TAG, "stop()");
    updateState();
  }

  @Override
  public void undoPressed() {
    //Log.e(TAG, "undoPressed()");

    model.undo();
    updateState();

    setDisplay(String.valueOf(model.getResult()));
    updateDisplay();
  }

  protected String getDisplay() {
    return state.display;
  }

  protected void setDisplay(String display) {
    //Log.e(TAG, "display: " + display);
    state.display = display;
  }

  protected void setBackspaceEnabled(boolean enabled) {
    state.backspaceEnabled = enabled;
  }

  protected boolean isBackspaceEnabled(){
    return state.backspaceEnabled;
  }

  @Override
  public void injectView(WeakReference<BasicContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(BrainContract.Model model) {
    this.model = model;
  }


//  @Override
//  public void injectRouter(BasicContract.Router router) {
//    this.router = router;
//  }


}
