package es.ulpgc.eite.cleancode.calculator.standard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.ulpgc.eite.cleancode.calculator.brain.BrainCommand;

public class StandardState extends StandardViewModel {

  public Integer result = 0;
  public String number = "0";
  public String operator = "";

  public List<BrainCommand> commandList = new ArrayList();

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    if (!super.equals(obj)) return false;
    StandardState that = (StandardState) obj;
    return Objects.equals(result, that.result) &&
        Objects.equals(number, that.number) &&
        Objects.equals(operator, that.operator) &&
        Objects.equals(commandList, that.commandList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), result, number, operator, commandList);
  }
}
