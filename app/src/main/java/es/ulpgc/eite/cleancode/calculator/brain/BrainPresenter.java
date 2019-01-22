package es.ulpgc.eite.cleancode.calculator.brain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;

public abstract class BrainPresenter implements BrainContract.Presenter {

  //  public static String TAG = BrainPresenter.class.getSimpleName();

//  private WeakReference<BasicContract.View> view;
//  private BasicViewModel viewModel;
//  private BasicContract.Router router;

  protected BrainContract.Model model;

  protected List<SharedState> commandHistory = new ArrayList<>();

  protected abstract void setSavedOperand(String so);
  protected abstract String getNumber();
  protected abstract void setNumber(String n);
  protected abstract String getSavedOperand();

  protected abstract void setResult(Integer n);

  protected abstract void displayNumber();
  protected abstract void displayWarning(String text);



//  public BrainPresenter(WeakReference<FragmentActivity> context) {
//    viewModel = ViewModelProviders
//        .of(context.get())
//        .get(BasicViewModel.class);
//  }
//
//  @Override
//  public void injectView(WeakReference<BasicContract.View> view) {
//    this.view = view;
//  }
//
//  @Override
//  public void injectModel(BrainContract.Model model) {
//    this.model = model;
//  }
//
//
//  @Override
//  public void injectRouter(BasicContract.Router router) {
//    this.router = router;
//  }



//  @Override
//  public void injectModel(BrainContract.Model model) {
//    this.model = model;
//  }


//  private void displayNumber() {
//    view.get().display(getDisplay());
//
//  }
//
//  private void displayWarning(String text) {
//    view.get().displayWarning(text);
//  }

  /*
  private void notifyOperationErrorWarning() {
    view.get().notifyOperationErrorWarning();

  }

  private void notifyWrongNumberWarning() {
    view.get().notifyWrongNumberWarning();

  }
  */

//  @Override
//  public void init() {
//    model.init();
//    model.setResult(viewModel.result);
//
//    //setNumber("0");
//    //setSavedOperand("");
//    //setDisplay("0");
//
//    displayNumber();
//  }

//  private void setResult(Integer n) {
//    model.setResult(n);
//    viewModel.result = n;
//  }

//  @Override
//  public void buttonClicked(String button) {
//
//    try {
//
//      Integer.parseInt(button);
//      digitPressed(button);
//
//    } catch (NumberFormatException ex) {
//
//      if(button.equals("+") || button.equals("-") || button.equals("=")){
//        operatorPressed(button);
//      } else if(button.equals("Clr")){
//        clearPressed();
//      } else if(button.equals("Del")){
//        backspacePressed();
//      } else if(button.equals(".")){
//        undoPressed();
//      }
//    }
//  }


//  @Override
//  public void undoPressed() {
//
//  }
//
//  @Override
//  public String getDisplay() {
//    return viewModel.display;
//  }
//
//  @Override
//  public void setDisplay(String d) {
//    viewModel.display = d;
//  }


//  private void setNumber(String n) {
//    viewModel.number = n;
//  }
//
//  private void setSavedOperand(String so) {
//    viewModel.savedOperand = so;
//  }
//
//  private String getNumber() {
//    return viewModel.number;
//  }
//
//  private String getSavedOperand() {
//    return viewModel.savedOperand;
//  }


  protected void commandExecuted(SharedState state) {
    commandHistory.add(state);
  }

  protected SharedState commandUndo() {
    if (commandHistory.isEmpty()) {
      return null;
    }

    int index = commandHistory.size() - 1;
    SharedState state = commandHistory.get(index);
    commandHistory.remove(index);
    return state;
  }

  @Override
  public void backspacePressed() {
    setSavedOperand("");

    if (getNumber().length() == 1) {
      setNumber("0");
    } else {
      setNumber(removeRightChar(getNumber()));
    }

    setDisplay(getNumber());
    displayNumber();
  }

  @Override
  public void clearPressed() {
    model.reset();

    setNumber("0");
    setSavedOperand("");

    setDisplay(getNumber());
    displayNumber();
  }

  @Override
  public void digitPressed(String c) {
    Integer maxInt = Integer.MAX_VALUE;
    int maxDigits = maxInt.toString().length();

    if (getNumber().length() != maxDigits) {
      if (getNumber().equals("0")) {
        setNumber(c);
      } else {
        setNumber(getNumber() + c);

      }
    }

    setDisplay(getNumber());
    displayNumber();
  }

  @Override
  public void operatorPressed(String c) {

    Integer n;

    try {
      //n = convertToInteger(getNumber());
      n = Integer.parseInt(getNumber());
    } catch (Exception ex) {
      displayWarning("Wrong number");
      //notifyWrongNumberWarning();
      Integer currentValue = model.getResult();
      setNumber(currentValue.toString());
      setDisplay(getNumber());
      displayNumber();
      setNumber("0");
      return;
    }


    if (getSavedOperand().equals("") && model.getResult() == 0) {
      //model.setResult(n);
      //viewModel.result = n;
      setResult(n);

    } else {
      try {

        if (getSavedOperand().equals("+")) {
          model.add(n);

        } else if (getSavedOperand().equals("-")) {
          model.subtract(n);

        } else if (getSavedOperand().equals("x")) {
          model.multiply(n);

        } else if (getSavedOperand().equals("/")) {
          model.divide(n);
        }

      } catch (Exception ex) {
        displayWarning("Operation error");
        //notifyOperationErrorWarning();
      }
    }

    Integer currentValue = model.getResult();
    setNumber(currentValue.toString());
    setDisplay(getNumber());
    displayNumber();

    setNumber("0");
    setSavedOperand(c);
  }


  private String removeRightChar(String text) {
    int numChars = text.length();

    if (numChars != 0) {
      return text.substring(0, numChars - 1);
    } else {
      return "";
    }
  }


  /*
  private Integer convertToInteger(String text) throws Exception {
    Integer number = Integer.parseInt(text);
    return number;
  }
  */

}
