package es.ulpgc.eite.cleancode.calculator.brain;

public class BrainModel implements BrainContract.Model {

  public static String TAG = BrainModel.class.getSimpleName();

  public class CalcDivideByZeroException extends Exception {
    private static final long serialVersionUID = 1L;
  }

  //private WeakReference<FragmentActivity> context;

  private int result;
  private NumberValidator validator;

  public BrainModel(){

  }

  /*
  public BrainModel(WeakReference<FragmentActivity> context) {
    this.context = context;
  }
  */

  /*
  @Override
  public String fetchData() {
    // Log.e(TAG, "fetchData()");
    return "Hello";
  }
  */

  @Override
  public void init() {
    setValidator(new NumberValidator(Integer.MIN_VALUE, Integer.MAX_VALUE));
    reset();
  }

  @Override
  public void init(int minValue, int maxValue) {
    setValidator(new NumberValidator(minValue, maxValue));
    reset();
  }

  @Override
  public void add(int operand) throws Exception {
    long newResult = (long) getResult() + operand;
    getValidator().checkBounds(newResult);
    setResult((int) newResult);
  }

  @Override
  public void subtract(int operand) throws Exception {
    long newResult = (long) getResult() - operand;
    getValidator().checkBounds(newResult);
    setResult((int) newResult);
  }

  @Override
  public void multiply(int operand) throws Exception {
    long newResult = (long) getResult() * operand;
    getValidator().checkBounds(newResult);
    setResult((int) newResult);
  }

  @Override
  public void divide(int operand) throws Exception {
    if (operand == 0) {
      throw new CalcDivideByZeroException();
    }

    long newResult = (long) getResult() / operand;
    getValidator().checkBounds(newResult);
    setResult((int) newResult);
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
  public NumberValidator getValidator() {
    return validator;
  }

  @Override
  public void setValidator(NumberValidator v) {
    validator = v;
  }


}
