package es.ulpgc.eite.cleancode.calculator.app;

import android.app.Application;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.basic.BasicState;
import es.ulpgc.eite.cleancode.calculator.standard.StandardState;

public class AppMediator extends Application {

  public BasicState basicState = new BasicState();
  public StandardState standardState = new StandardState();

  public SharedState state;
  public List<SharedState> history;

}
