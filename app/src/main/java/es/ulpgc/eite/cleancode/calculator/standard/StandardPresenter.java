package es.ulpgc.eite.cleancode.calculator.standard;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainPresenter;

public class StandardPresenter
    extends BrainPresenter implements StandardContract.Presenter {

  public static String TAG = StandardPresenter.class.getSimpleName();

  private WeakReference<StandardContract.View> view;
  private StandardViewModel viewModel;
  private StandardState state;
  private StandardContract.Router router;

  public StandardPresenter(StandardState state) {
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

    CalculatorState calcState = router.getStateFromBasicScreen();
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
    setBackspaceEnabled(viewModel.backspaceEnabled);
    setDisplay(viewModel.display);

    updateDisplay();
  }



  @Override
  public void configChanged() {
    Log.e(TAG, "configChanged()");

    CalculatorState calcState = new CalculatorState();
    calcState.operator = model.getOperator();
    calcState.number = String.valueOf(model.getNumber());
    calcState.result = model.getResult();
    calcState.commandList = model.getCommandList();
    calcState.display = viewModel.display;
    calcState.backspaceEnabled = viewModel.backspaceEnabled;

    router.passStateToBasicScreen(calcState);
    router.navigateToBasicScreen();
    view.get().finishBasicScreen();
  }

  @Override
  public void buttonClicked(String button) {

    try {

      Integer.parseInt(button);
      digitPressed(button);

    } catch (NumberFormatException ex) {

      if (button.equals("+") || button.equals("-") || button.equals("=")) {
        operatorPressed(button);
      } else if (button.equals("x") || button.equals("/")) {
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
    updateState();
  }

  @Override
  public void undoPressed() {
    //Log.e(TAG, "undoPressed()");

    model.undo();
    updateState();

    updateDisplay();

  }

  protected String getDisplay() {
    return viewModel.display;
  }

  protected void setDisplay(String display) {
    Log.e(TAG, "display: " + display);
    viewModel.display = display;
  }

  protected void setBackspaceEnabled(boolean enabled) {
    viewModel.backspaceEnabled = enabled;
  }

  protected boolean isBackspaceEnabled(){
    return viewModel.backspaceEnabled;
  }

  @Override
  public void injectView(WeakReference<StandardContract.View> view) {
    this.view = view;
  }


  @Override
  public void injectModel(BrainContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(StandardContract.Router router) {
    this.router = router;
  }

}
