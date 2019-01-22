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

  /*
  private void notifyOperationErrorWarning() {
    view.get().notifyOperationErrorWarning();

  }

  private void notifyWrongNumberWarning() {
    view.get().notifyWrongNumberWarning();

  }
  */

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

  /*
  @Override
  public void buttonClicked(String button) {

    try {

      Integer.parseInt(button);
      digitPressed(button);

    } catch (NumberFormatException ex) {

      if(button.equals("+") || button.equals("-") || button.equals("=")) {
        operatorPressed(button);
      } else if(button.equals("x") || button.equals("/")){
        operatorPressed(button);
      } else if(button.equals("Clr")){
        clearPressed();
      } else if(button.equals("Del")){
        backspacePressed();
      } else if(button.equals(".")){
        dotPressed();
      }
    }
  }
  */

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



//  @Override
//  public void backspacePressed() {
//    setSavedOperand("");
//
//    if (getNumber().length() == 1) {
//      setNumber("0");
//    } else {
//      setNumber(removeRightChar(getNumber()));
//    }
//
//    setDisplay(getNumber());
//    displayNumber();
//  }
//
//  @Override
//  public void clearPressed() {
//    model.reset();
//
//    setNumber("0");
//    setSavedOperand("");
//
//    setDisplay(getNumber());
//    displayNumber();
//  }
//
//  @Override
//  public void digitPressed(String c) {
//    Integer maxInt = Integer.MAX_VALUE;
//    int maxDigits = maxInt.toString().length();
//
//    if (getNumber().length() != maxDigits) {
//      if (getNumber().equals("0")) {
//        setNumber(c);
//      } else {
//        setNumber(getNumber() + c);
//
//      }
//    }
//
//    setDisplay(getNumber());
//    displayNumber();
//  }
//
//  @Override
//  public void operatorPressed(String c) {
//
//    Integer n;
//
//    try {
//      //n = convertToInteger(getNumber());
//      n = Integer.parseInt(getNumber());
//    } catch (Exception ex) {
//      displayWarning("Wrong number");
//      //notifyWrongNumberWarning();
//      Integer currentValue = model.getResult();
//      setNumber(currentValue.toString());
//      setDisplay(getNumber());
//      displayNumber();
//      setNumber("0");
//      return;
//    }
//
//
//    if (getSavedOperand().equals("") && model.getResult() == 0) {
//      model.setResult(n);
//      viewModel.result = n;
//
//    } else {
//      try {
//
//        if (getSavedOperand().equals("+")) {
//          model.add(n);
//
//        } else if (getSavedOperand().equals("-")) {
//          model.subtract(n);
//
//        } else if (getSavedOperand().equals("x")) {
//          model.multiply(n);
//
//        } else if (getSavedOperand().equals("/")) {
//          model.divide(n);
//        }
//
//      } catch (Exception ex) {
//        displayWarning("Operation error");
//        //notifyOperationErrorWarning();
//      }
//    }
//
//    Integer currentValue = model.getResult();
//    setNumber(currentValue.toString());
//    setDisplay(getNumber());
//    displayNumber();
//
//    setNumber("0");
//    setSavedOperand(c);
//  }
//
//
//  private String removeRightChar(String text) {
//    int numChars = text.length();
//
//    if (numChars != 0) {
//      return text.substring(0, numChars - 1);
//    } else {
//      return "";
//    }
//  }


  /*
  private Integer convertToInteger(String text) throws Exception {
    Integer number = Integer.parseInt(text);
    return number;
  }
  */

}
