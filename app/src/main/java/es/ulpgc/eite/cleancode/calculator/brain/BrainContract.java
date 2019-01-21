package es.ulpgc.eite.cleancode.calculator.brain;

import java.lang.ref.WeakReference;

public interface BrainContract {

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
