package es.ulpgc.eite.cleancode.calculator.app;

import android.app.Application;

import es.ulpgc.eite.cleancode.calculator.basic.BasicState;
import es.ulpgc.eite.cleancode.calculator.standard.StandardState;

public class AppMediator extends Application {

  public BasicState basicState = new BasicState();
  public StandardState standardState = new StandardState();

  public CalculatorState calculatorState;
  //public List<CalculatorState> commandHistory;

}
