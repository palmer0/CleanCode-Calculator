package es.ulpgc.eite.cleancode.calculator.standard;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainPresenter;

public class StandardPresenter
    extends BrainPresenter implements StandardContract.Presenter {

  public static String TAG = StandardPresenter.class.getSimpleName();

  private WeakReference<StandardContract.View> view;
  private StandardViewModel viewModel;
  //private BrainContract.Model model;
  private StandardContract.Router router;

  public StandardPresenter(WeakReference<FragmentActivity> context) {
    viewModel = ViewModelProviders
        .of(context.get())
        .get(StandardViewModel.class);
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


  protected void displayNumber() {
    view.get().display(getDisplay());

  }

  protected void displayWarning(String text) {
    view.get().displayWarning(text);
  }

  @Override
  public void init() {
    model.init();

    SharedState state = router.getDataFromBasicScreen();
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
    SharedState state = router.getDataFromBasicScreen();
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

    /*
    String savedOperand = viewModel.savedOperand;
    if (savedOperand.equals("*") || savedOperand.equals("/") ){
      return;
    }
    */

    viewModel.result = model.getResult();
    //setResult(model.getResult());

    router.passDataToBasicScreen(viewModel);
    router.navigateToBasicScreen();
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
      } else if (button.equals(".")) {
        dotPressed();
      }
    }
  }

  @Override
  public void dotPressed() {

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
    viewModel.result = n;
  }

  protected String getNumber() {
    return viewModel.number;
  }

  protected String getSavedOperand() {
    return viewModel.savedOperand;
  }



}
