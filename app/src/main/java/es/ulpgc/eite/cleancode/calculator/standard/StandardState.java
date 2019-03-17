package es.ulpgc.eite.cleancode.calculator.standard;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.brain.BrainCommand;

public class StandardState extends StandardViewModel {

  public Integer result = 0;
  public String number = "0";
  public String operator = "";

  public List<BrainCommand> commandList = new ArrayList();
}
