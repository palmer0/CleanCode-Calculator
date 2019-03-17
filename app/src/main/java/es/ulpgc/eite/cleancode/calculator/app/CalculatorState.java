package es.ulpgc.eite.cleancode.calculator.app;

import java.util.List;
import java.util.Objects;

import es.ulpgc.eite.cleancode.calculator.brain.BrainCommand;

public class CalculatorState {

  public String display;
  public String number;
  public String operator;
  public Integer result;

  public boolean backspaceEnabled;
  public List<BrainCommand> commandList;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CalculatorState that = (CalculatorState) obj;
    return backspaceEnabled == that.backspaceEnabled &&
        Objects.equals(display, that.display) &&
        Objects.equals(number, that.number) &&
        Objects.equals(operator, that.operator) &&
        Objects.equals(result, that.result) &&
        Objects.equals(commandList, that.commandList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        display, number, operator, result, backspaceEnabled, commandList
    );
  }
}

