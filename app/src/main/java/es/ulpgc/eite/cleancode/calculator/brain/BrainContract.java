package es.ulpgc.eite.cleancode.calculator.brain;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.basic.BasicContract;

public interface BrainContract {



  interface Presenter {
//    void injectView(WeakReference<BasicContract.View> view);
//    void injectModel(BrainContract.Model model);
//    void injectRouter(BasicContract.Router router);

    void init();

    //void buttonClicked(String button);
    void dotPressed();
    String getDisplay();
    void setDisplay(String d);
    void backspacePressed();
    void clearPressed();
    void digitPressed(String c);
    void operatorPressed(String c);
  }

  interface Model {

    void init();
    void init(int minValue, int maxValue);

    void add(int operand) throws Exception;
    void subtract(int operand) throws Exception;
    void multiply(int operand) throws Exception;
    void divide(int operand) throws Exception;
    void reset();
    int getResult();
    void setResult(int value);

    NumberValidator getValidator();
    void setValidator(NumberValidator v);

  }

}
