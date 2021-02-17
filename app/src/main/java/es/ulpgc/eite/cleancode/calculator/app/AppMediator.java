package es.ulpgc.eite.cleancode.calculator.app;

import es.ulpgc.eite.cleancode.calculator.basic.BasicState;
import es.ulpgc.eite.cleancode.calculator.standard.StandardState;

public class AppMediator  {

  public BasicState basicState = new BasicState();
  public StandardState standardState = new StandardState();

  public CalculatorState calculatorState;


  private static AppMediator INSTANCE;

  private AppMediator() {

  }

  public static void resetInstance() {
    INSTANCE = null;
  }


  public static AppMediator getInstance() {
    if(INSTANCE ==null){
      INSTANCE = new AppMediator();
    }

    return INSTANCE;
  }


}
