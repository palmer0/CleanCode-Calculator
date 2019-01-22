package es.ulpgc.eite.cleancode.calculator.basic;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainPresenter;

public class BasicPresenter
    extends BrainPresenter implements BasicContract.Presenter {

  public static String TAG = BasicPresenter.class.getSimpleName();

  private WeakReference<BasicContract.View> view;
  private BasicViewModel viewModel;
  //private BrainContract.Model model;
  private BasicContract.Router router;


  public BasicPresenter(WeakReference<FragmentActivity> context) {
    viewModel = ViewModelProviders
        .of(context.get())
        .get(BasicViewModel.class);
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

    SharedState state = router.getDataFromStandardScreen();
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
    SharedState state = router.getDataFromStandardScreen();
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

    viewModel.result = model.getResult();
    //setResult(model.getResult());

    router.passDataToStandardScreen(viewModel);
    router.navigateToStandardScreen();
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
