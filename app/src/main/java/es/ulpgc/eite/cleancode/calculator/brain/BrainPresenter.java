package es.ulpgc.eite.cleancode.calculator.brain;

import android.util.Log;


public abstract class BrainPresenter implements BrainContract.Presenter {

  public static String TAG = BrainPresenter.class.getSimpleName();

  protected abstract String getDisplay();
  protected abstract void setDisplay(String display);
  protected abstract void setBackspaceEnabled(boolean enabled);
  protected abstract boolean isBackspaceEnabled();

  protected abstract void updateDisplay();
  protected abstract void displayWarning(String text);

  protected BrainContract.Model model;

  @Override
  public void backspacePressed() {
    if(!isBackspaceEnabled()) {
      return;
    }

    //setSavedOperator(""); //?

    if (getNumber().length() == 1) {
      setNumber("0");
    } else {
      String number = getNumber();
      number = number.substring(0, number.length() - 1);
      setNumber(number);
    }

    setDisplay(getNumber());
    updateDisplay();
  }

  @Override
  public void clearPressed() {
    setBackspaceEnabled(false);

    model.reset();

    setNumber("0");
    setSavedOperator("");

    setDisplay(getNumber());
    updateDisplay();
  }

  @Override
  public void digitPressed(String number) {

    setBackspaceEnabled(true);

    if (getNumber().equals("0")) {
      setNumber(number);
    } else {
      setNumber(getNumber() + number);

    }

    setDisplay(getNumber());
    updateDisplay();
  }

  @Override
  public void operatorPressed(String operator) {

    setBackspaceEnabled(false);

    Integer operand;

    try {

      operand = Integer.parseInt(getNumber());

    } catch (Exception ex) {

      displayWarning("Wrong number");

      Integer number = model.getResult();
      setNumber(number.toString());
      setDisplay(getNumber());
      updateDisplay();
      setNumber("0");

      return;
    }


    //if (getSavedOperator().equals("") && model.getResult() == 0) {
    if (getSavedOperator().equals("")) {
      setResult(operand);

    } else {

      if (getSavedOperator().equals("/") && operand == 0) {
        displayWarning("Operation error");

      } else {
        model.execute();
      }

    }

    Integer number = model.getResult();
    setNumber(number.toString());
    setDisplay(getNumber());
    updateDisplay();

    setNumber("0");
    setSavedOperator(operator);
    //Log.e(TAG, "operator: " + operator);

  }

  private void setNumber(String number) {
    model.setNumber(Integer.parseInt(number));
  }

  private void setSavedOperator(String operator) {
    model.setOperator(operator);
  }


  private void setResult(Integer result) {
    model.setResult(result);
  }

  private String getNumber() {
    return String.valueOf(model.getNumber());
  }

  private String getSavedOperator() {
    return model.getOperator();
  }
}
