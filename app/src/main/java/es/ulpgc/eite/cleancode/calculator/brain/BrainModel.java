package es.ulpgc.eite.cleancode.calculator.brain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BrainModel implements BrainContract.Model {

  public static String TAG = BrainModel.class.getSimpleName();

  private List<BrainCommand> commandList;

  private int result;
  private String operator;
  private int number;

  public BrainModel(){
    commandList = new ArrayList();
  }

  @Override
  public void setCommandList(List<BrainCommand> commands) {
    commandList = commands;
  }

  @Override
  public List<BrainCommand> getCommandList() {
    return commandList;
  }


  @Override
  public void execute() {
    //Log.e(TAG, "execute()");

    BrainCommand command = new BrainCommand(result, operator, number);
    //Log.e(TAG, "command: " + command);
    command.execute();

    commandList.add(command);
    result = command.getResult();
  }

  @Override
  public void undo() {
    //Log.e(TAG, "undo()");

    if(commandList.size() - 2 < 0) {
      result = 0;
      operator = "";
      number = 0;

    } else {

      BrainCommand command = commandList.get(commandList.size() - 2);
      //Log.e(TAG, "command: " + command);

      result = command.getResult();
      operator = command.getOperator();
      number = command.getOperand();
    }

    if(commandList.size() != 0) {
      commandList.remove(commandList.size() - 1);
    }
  }

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
