package es.ulpgc.eite.cleancode.calculator.brain;

import java.util.List;

public interface BrainContract {


  interface Presenter {

    void undoPressed();
    void backspacePressed();
    void clearPressed();
    void digitPressed(String number);
    void operatorPressed(String operator);
  }

  interface Model {

    void setCommandList(List<BrainCommand> commandList);
    List<BrainCommand> getCommandList();

    void undo();
    void execute();

    void reset();

    int getResult();
    void setResult(int result);
    int getNumber();
    String getOperator();
    void setNumber(int number);
    void setOperator(String operator);

  }

}
