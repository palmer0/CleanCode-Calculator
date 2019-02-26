package es.ulpgc.eite.cleancode.calculator.standard;

import java.util.Objects;

public class StandardViewModel  {

  public String display = "0";
  public String number = "0";
  public String savedOperand = "";

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    StandardViewModel that = (StandardViewModel) obj;
    return Objects.equals(display, that.display) &&
        Objects.equals(number, that.number) &&
        Objects.equals(savedOperand, that.savedOperand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(display, number, savedOperand);
  }
}

