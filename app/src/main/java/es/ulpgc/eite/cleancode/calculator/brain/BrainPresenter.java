package es.ulpgc.eite.cleancode.calculator.brain;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;

public abstract class BrainPresenter implements BrainContract.Presenter {

  protected abstract void setSavedOperand(String so);
  protected abstract String getNumber();
  protected abstract void setNumber(String n);
  protected abstract String getSavedOperand();

  protected abstract void setResult(Integer n);

  protected abstract void displayNumber();
  protected abstract void displayWarning(String text);

  protected BrainContract.Model model;

  protected List<SharedState> commandHistory = new ArrayList<>();


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
      n = Integer.parseInt(getNumber());
    } catch (Exception ex) {
      displayWarning("Wrong number");
      Integer currentValue = model.getResult();
      setNumber(currentValue.toString());
      setDisplay(getNumber());
      displayNumber();
      setNumber("0");
      return;
    }


    if (getSavedOperand().equals("") && model.getResult() == 0) {
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

}
