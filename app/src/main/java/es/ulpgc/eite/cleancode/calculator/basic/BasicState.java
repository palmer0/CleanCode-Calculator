package es.ulpgc.eite.cleancode.calculator.basic;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.brain.BrainCommand;

public class BasicState extends BasicViewModel {

  public Integer result = 0;
  public String number = "0";
  public String operator = "";

  public List<BrainCommand> commandList = new ArrayList();
}
