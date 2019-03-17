package es.ulpgc.eite.cleancode.calculator.brain;

public abstract class BrainPresenter implements BrainContract.Presenter {

//  protected abstract void setSavedOperator(String operator);
//  protected abstract String getNumber();
//  protected abstract void setNumber(String number);
//  protected abstract String getSavedOperator();
//  protected abstract void setResult(Integer result);

  protected abstract String getDisplay();
  protected abstract void setDisplay(String display);
  protected abstract void setBackspaceEnabled(boolean enabled);
  protected abstract boolean isBackspaceEnabled();

  protected abstract void updateDisplay();
  protected abstract void displayWarning(String text);

  protected BrainContract.Model model;

  //protected List<CalculatorState> commandHistory = new ArrayList<>();
  //protected boolean backspaceEnabled;

  /*
  protected void commandExecuted(CalculatorState state) {
    commandHistory.add(state);
  }

  protected CalculatorState commandUndo() {
    if (commandHistory.isEmpty()) {
      return null;
    }

    int index = commandHistory.size() - 1;
    CalculatorState state = commandHistory.get(index);
    commandHistory.remove(index);
    return state;
  }
  */

  @Override
  public void backspacePressed() {
    if(!isBackspaceEnabled()) {
      return;
    }

    /*
    if(!backspaceEnabled) {
      return;
    }
    */

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
    //backspaceEnabled = false;
    setBackspaceEnabled(false);

    model.reset();

    setNumber("0");
    setSavedOperator("");

    setDisplay(getNumber());
    updateDisplay();
  }

  @Override
  public void digitPressed(String number) {

    //backspaceEnabled = true;
    setBackspaceEnabled(true);

    /*
    Integer maxInt = Integer.MAX_VALUE;
    int maxDigits = maxInt.toString().length();

    if (getNumber().length() != maxDigits) {
      if (getNumber().equals("0")) {
        setNumber(number);
      } else {
        setNumber(getNumber() + number);

      }
    }
    */

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

    //backspaceEnabled = false;
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

      /*
      if (getSavedOperator().equals("+")) {
        model.add(operand);

      } else if (getSavedOperator().equals("-")) {
        model.subtract(operand);

      } else if (getSavedOperator().equals("x")) {
        model.multiply(operand);

      } else if (getSavedOperator().equals("/")) {
        if (operand == 0) {
          displayWarning("Operation error");
        } else {
          model.divide(operand);
        }
      }
      */

      if (getSavedOperator().equals("/") && operand == 0) {
        displayWarning("Operation error");

      } else {

        //model.execute(getSavedOperator(), operand);
        model.execute();

        //BrainCommand command = new BrainCommand(getSavedOperator(), operand);
        //model.execute(command);
      }


      /*
      try {

        if (getSavedOperator().equals("+")) {
          model.add(operand);

        } else if (getSavedOperator().equals("-")) {
          model.subtract(operand);

        } else if (getSavedOperator().equals("x")) {
          model.multiply(operand);

        } else if (getSavedOperator().equals("/")) {
          model.divide(operand);
        }

      } catch (Exception ex) {
        displayWarning("Operation error");
      }
      */
    }

    Integer number = model.getResult();
    setNumber(number.toString());
    setDisplay(getNumber());
    updateDisplay();

    setNumber("0");
    setSavedOperator(operator);

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
