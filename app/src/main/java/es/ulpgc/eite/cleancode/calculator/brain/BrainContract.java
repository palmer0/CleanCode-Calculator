package es.ulpgc.eite.cleancode.calculator.brain;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppCommand;

public interface BrainContract {


  interface Presenter {

    //void init();

    void undoPressed();
    //String getDisplay();
    //void setDisplay(String display);
    void backspacePressed();
    void clearPressed();
    void digitPressed(String number);
    void operatorPressed(String operator);
  }

  interface Model {

    /*
    void add(int operand) throws Exception;
    void subtract(int operand) throws Exception;
    void multiply(int operand) throws Exception;
    void divide(int operand) throws Exception;
    */

    /*
    void add(int operand);
    void subtract(int operand);
    void multiply(int operand);
    void divide(int operand);
    */

    void setCommands(List<BrainCommand> commands);
    List<BrainCommand> getCommands();

    void undo();
    void execute();
    //void execute(String operator, Integer operand);

    void reset();

    int getResult();
    void setResult(int result);
    int getNumber();
    String getOperator();
    void setNumber(int number);
    void setOperator(String operator);



    //void execute(BrainCommand command);
  }

}
