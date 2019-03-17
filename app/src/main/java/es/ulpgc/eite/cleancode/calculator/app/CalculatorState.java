package es.ulpgc.eite.cleancode.calculator.app;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.brain.BrainCommand;

public class CalculatorState {

  public String display;
  public String number;
  public String operator;
  public Integer result;

  public boolean backspaceEnabled;
  public List<BrainCommand> commandList;
}

