package es.ulpgc.eite.cleancode.calculator.brain;

public class NumberValidator {

  private long lowBound;
  private long highBound;

  public NumberValidator(int lowBound, int highBound) {
    setLowBound(lowBound);
    setHighBound(highBound);
  }

  public void checkBounds(long value) throws Exception {
    if (value > getHighBound()) {
      throw new CalcOverflowException();

    } else if (value < getLowBound()) {
      throw new CalcUnderflowException();
    }
  }


  public long getLowBound() {
    return lowBound;
  }

  public void setLowBound(long value) {
    lowBound = value;
  }

  public long getHighBound() {
    return highBound;
  }

  public void setHighBound(long value) {
    highBound = value;
  }

  public class CalcUnderflowException extends Exception {
    private static final long serialVersionUID = 1L;
  }

  public class CalcOverflowException extends Exception {
    private static final long serialVersionUID = 1L;
  }

}
