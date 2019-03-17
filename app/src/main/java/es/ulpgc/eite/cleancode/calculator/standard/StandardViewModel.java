package es.ulpgc.eite.cleancode.calculator.standard;

import java.util.Objects;

public class StandardViewModel  {

  public String display = "0";
  public boolean backspaceEnabled = false;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    StandardViewModel that = (StandardViewModel) obj;
    return backspaceEnabled ==
        that.backspaceEnabled && Objects.equals(display, that.display);
  }

  @Override
  public int hashCode() {
    return Objects.hash(display, backspaceEnabled);
  }
}

