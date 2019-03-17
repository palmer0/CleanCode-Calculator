package es.ulpgc.eite.cleancode.calculator.basic;

import java.util.Objects;


public class BasicViewModel  {

  public String display = "0";
  public boolean backspaceEnabled = false;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    BasicViewModel that = (BasicViewModel) obj;
    return backspaceEnabled ==
        that.backspaceEnabled && Objects.equals(display, that.display);
  }

  @Override
  public int hashCode() {
    return Objects.hash(display, backspaceEnabled);
  }
}

