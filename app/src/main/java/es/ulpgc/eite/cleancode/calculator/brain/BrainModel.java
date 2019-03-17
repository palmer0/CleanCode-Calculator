package es.ulpgc.eite.cleancode.calculator.brain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppCommand;

public class BrainModel implements BrainContract.Model {

  public static String TAG = BrainModel.class.getSimpleName();

  /*
  public class CalcDivideByZeroException extends Exception {
    private static final long serialVersionUID = 1L;
  }
  */

  private List<BrainCommand> commands;

  private int result;
  private String operator;
  private int number;

  public BrainModel(){
    commands = new ArrayList();
  }

  @Override
  public void setCommands(List<BrainCommand> commands) {
    this.commands = commands;
  }

  @Override
  public List<BrainCommand> getCommands() {
    return commands;
  }

  /*
  @Override
  public void add(int operand) throws Exception {
    long newResult = (long) getResult() + operand;
    setResult((int) newResult);
  }

  @Override
  public void subtract(int operand) throws Exception {
    long newResult = (long) getResult() - operand;
    setResult((int) newResult);
  }

  @Override
  public void multiply(int operand) throws Exception {
    long newResult = (long) getResult() * operand;
    setResult((int) newResult);
  }

  @Override
  public void divide(int operand) throws Exception {
    if (operand == 0) {
      throw new CalcDivideByZeroException();
    }

    long newResult = (long) getResult() / operand;
    setResult((int) newResult);
  }
  */


//  @Override
//  public void execute(BrainCommand command) {
//
//    command.execute();
//    result = command.getResult();
//
//    /*
//    if (command.getOperator().equals("+")) {
//      add(command.getOperand());
//
//    } else if (command.getOperator().equals("-")) {
//      subtract(command.getOperand());
//
//    } else if (command.getOperator().equals("x")) {
//      multiply(command.getOperand());
//
//    } else if (command.getOperator().equals("/")) {
//      divide(command.getOperand());
//    }
//    */
//  }


//  private void add(int operand)  {
//    long newResult = (long) getResult() + operand;
//    setResult((int) newResult);
//  }
//
//  private void subtract(int operand)  {
//    long newResult = (long) getResult() - operand;
//    setResult((int) newResult);
//  }
//
//  private void multiply(int operand)  {
//    long newResult = (long) getResult() * operand;
//    setResult((int) newResult);
//  }
//
//  private void divide(int operand) {
//
//    long newResult = (long) getResult() / operand;
//    setResult((int) newResult);
//  }


//  @Override
//  public void execute(String operator, Integer operand) {
//    Log.e(TAG, "execute()");
//
//    BrainCommand command = new BrainCommand(result, operator, operand);
//    Log.e(TAG, "command: " + command);
//    command.execute();
//
//    commands.add(command);
//    result = command.getResult();
//
//    /*
//    if (operator.equals("+")) {
//      add(operand);
//
//    } else if (operator.equals("-")) {
//      subtract(operand);
//
//    } else if (operator.equals("x")) {
//      multiply(operand);
//
//    } else if (operator.equals("/")) {
//      divide(operand);
//    }
//    */
//  }

  @Override
  public void execute() {
    //Log.e(TAG, "execute()");

    BrainCommand command = new BrainCommand(result, operator, number);
    //Log.e(TAG, "command: " + command);
    command.execute();

    commands.add(command);
    result = command.getResult();

    /*
    if (operator.equals("+")) {
      add(operand);

    } else if (operator.equals("-")) {
      subtract(operand);

    } else if (operator.equals("x")) {
      multiply(operand);

    } else if (operator.equals("/")) {
      divide(operand);
    }
    */
  }

  @Override
  public void undo() {
    //Log.e(TAG, "undo()");

    if(commands.size() - 2 < 0) {
      result = 0;
      operator = "";
      number = 0;

    } else {

      BrainCommand command = commands.get(commands.size() - 2);
      //Log.e(TAG, "command: " + command);

      result = command.getResult();
      operator = command.getOperator();
      number = command.getOperand();
    }

    if(commands.size() != 0) {
      commands.remove(commands.size() - 1);
    }
  }

  /*
  @Override
  public void add(int operand)  {
    long newResult = (long) getResult() + operand;
    setResult((int) newResult);
  }

  @Override
  public void subtract(int operand)  {
    long newResult = (long) getResult() - operand;
    setResult((int) newResult);
  }

  @Override
  public void multiply(int operand)  {
    long newResult = (long) getResult() * operand;
    setResult((int) newResult);
  }

  @Override
  public void divide(int operand) {

    long newResult = (long) getResult() / operand;
    setResult((int) newResult);
  }
  */

  /*
  private void execute() {
    BrainCommand command = new BrainCommand(getSavedOperator(), operand);
    model.execute();
  }
  */


  @Override
  public void reset() {
    setResult(0);
  }

  @Override
  public int getResult() {
    return result;
  }


  @Override
  public void setResult(int value) {
    result = value;
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public String getOperator() {
    return operator;
  }

  @Override
  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public void setOperator(String operator) {
    this.operator = operator;
  }


}
