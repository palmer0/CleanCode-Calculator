package es.ulpgc.eite.cleancode.calculator.basic;

import android.arch.lifecycle.ViewModel;

public class BasicViewModel extends ViewModel {

  public String display = "0";
  public String number = "0";
  public String savedOperand = "";

  public Integer result = 0;
}

