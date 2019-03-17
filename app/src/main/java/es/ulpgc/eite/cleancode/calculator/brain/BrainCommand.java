package es.ulpgc.eite.cleancode.calculator.brain;

import java.util.Objects;

import es.ulpgc.eite.cleancode.calculator.app.AppCommand;

public class BrainCommand implements AppCommand {

  private String operator; 
  private int operand;
  private int result;

  public BrainCommand(int result, String operator, int operand) {
    this.result = result;
    this.operator = operator;
    this.operand = operand;
  }


  @Override
  public void execute() {

    if (operator.equals("+")) {
      add(operand);

    } else if (operator.equals("-")) {
      subtract(operand);

    } else if (operator.equals("x")) {
      multiply(operand);

    } else if (operator.equals("/")) {
      divide(operand);
    }
  }

  private void add(int operand)  {
    long newResult = (long) getResult() + operand;
    setResult((int) newResult);
  }


  private void setResult(int newResult) {
    result = newResult;
  }

  public int getResult() {
    return result;
  }

  public String getOperator() {
    return operator;
  }

  public int getOperand() {
    return operand;
  }


  private void subtract(int operand)  {
    long newResult = (long) getResult() - operand;
    setResult((int) newResult);
  }

  private void multiply(int operand)  {
    long newResult = (long) getResult() * operand;
    setResult((int) newResult);
  }

  private void divide(int operand) {

    long newResult = (long) getResult() / operand;
    setResult((int) newResult);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    BrainCommand that = (BrainCommand) obj;
    return operand == that.operand && result == that.result
        && Objects.equals(operator, that.operator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operator, operand, result);
  }

  @Override
  public String toString() {
    return
        "operator='" + operator + '\'' +
        ", operand=" + operand +
        ", result=" + result;
  }
}
