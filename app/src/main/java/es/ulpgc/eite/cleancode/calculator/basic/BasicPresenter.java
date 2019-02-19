package es.ulpgc.eite.cleancode.calculator.basic;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainPresenter;

public class BasicPresenter
    extends BrainPresenter implements BasicContract.Presenter {

  public static String TAG = BasicPresenter.class.getSimpleName();

  private WeakReference<BasicContract.View> view;
  private BasicViewModel viewModel;
  private BasicState state;
  //private BrainContract.Model model;
  private BasicContract.Router router;

  /*
  public BasicPresenter(WeakReference<FragmentActivity> context) {
    viewModel = ViewModelProviders
        .of(context.get())
        .get(BasicViewModel.class);
  }
  */

  public BasicPresenter(BasicState state) {
    this.state = state;
    viewModel = state;
  }

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

  protected void displayNumber() {
    view.get().display(getDisplay());

  }

  protected void displayWarning(String text) {
    view.get().displayWarning(text);
  }


  @Override
  public void init() {
    model.init();

    List<SharedState> history = router.getHistoryFromStandardScreen();
    if(history != null) {
      commandHistory.addAll(history);
    }

    SharedState state = router.getStateFromStandardScreen();
    if(state != null) {
      setResult(state.result);
      setSavedOperand(state.savedOperand);
      setNumber(state.number);
      setDisplay(state.display);
    }

    displayNumber();
  }

  /*
  @Override
  public void init() {
    SharedState state = router.getStateFromStandardScreen();
    if(state != null) {
      viewModel.result = state.result;
      viewModel.savedOperand = state.savedOperand;
      viewModel.number = state.number;
      viewModel.display = state.display;
    }

    model.init();
    model.setResult(viewModel.result);

    //setNumber("0");
    //setSavedOperand("");
    //setDisplay("0");

    displayNumber();
  }
  */

  @Override
  public void configChanged() {
    Log.e(TAG, "configChanged()");

    //viewModel.result = model.getResult();
    state.result = model.getResult();
    //setResult(model.getResult());

    SharedState commandState = new SharedState();
    commandState.number = viewModel.number;
    commandState.display = viewModel.display;
    commandState.savedOperand = viewModel.savedOperand;
    //commandState.result = viewModel.result;
    commandState.result = state.result;

    router.passStateToStandardScreen(commandState, commandHistory);
    //router.passStateToStandardScreen(viewModel);
    router.navigateToStandardScreen();
    view.get().finishStandardScreen();
  }

  @Override
  public void buttonClicked(String button) {
    if (!button.equals("Undo")) {
      commandExecuted();
    }

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
      }
    }

    //commandExecuted();
  }

  private void commandExecuted() {
    Log.e(TAG, "commandExecuted()");

    //viewModel.result = model.getResult();
    state.result = model.getResult();

    SharedState sharedState = new SharedState();
    sharedState.number = viewModel.number;
    sharedState.display = viewModel.display;
    sharedState.savedOperand = viewModel.savedOperand;
    //sharedState.result = viewModel.result;
    sharedState.result = state.result;

    Log.e(TAG, "display: " + viewModel.display);
    Log.e(TAG, "number: " + viewModel.number);
    Log.e(TAG, "operand: " + viewModel.savedOperand);
    //Log.e(TAG, "result: " + viewModel.result);
    Log.e(TAG, "result: " + state.result);

    commandExecuted(sharedState);
  }



  @Override
  public void undoPressed() {
    Log.e(TAG, "undoPressed()");

    SharedState state = commandUndo();
    if(state == null) {
      return;
    }

    /*
    viewModel.result = state.result;
    viewModel.savedOperand = state.savedOperand;
    viewModel.number = state.number;
    viewModel.display = state.display;
    */

    setResult(state.result);
    setSavedOperand(state.savedOperand);
    setNumber(state.number);
    setDisplay(state.display);

    displayNumber();
  }

  @Override
  public String getDisplay() {
    return viewModel.display;
  }

  @Override
  public void setDisplay(String d) {
    Log.e(TAG, "display: " + d);
    viewModel.display = d;
  }


  protected void setNumber(String n) {
    Log.e(TAG, "number: " + n);
    viewModel.number = n;
  }

  protected void setSavedOperand(String so) {
    Log.e(TAG, "operand: " + so);
    viewModel.savedOperand = so;
  }


  protected void setResult(Integer n) {
    Log.e(TAG, "result: " + n);
    model.setResult(n);
    //viewModel.result = n;
    state.result = n;
  }

  protected String getNumber() {
    return viewModel.number;
  }

  protected String getSavedOperand() {
    return viewModel.savedOperand;
  }




}
