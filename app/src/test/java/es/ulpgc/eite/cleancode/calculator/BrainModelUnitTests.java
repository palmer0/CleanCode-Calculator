package es.ulpgc.eite.cleancode.calculator;

import org.junit.Test;

import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

import static org.junit.Assert.assertEquals;

public class BrainModelUnitTests {

  @Test
  public void testAdd() {

    // Given
    BrainModel model = new BrainModel();
    model.init();

    // When
    try {
      model.add(2);
      model.add(4);
    } catch (Exception e) {

    }

    // Then
    assertEquals(
        "Get add result failed",
        6, model.getResult()
    );
  }

  @Test
  public void testMultiply() {

    // Given
    BrainModel model = new BrainModel();
    model.init();

    // When
    try {
      model.add(2);
      model.multiply(4);
    } catch (Exception e) {

    }

    // Then
    assertEquals(
        "Get multiply result failed",
        8, model.getResult()
    );
  }

  @Test
  public void testDivideByZero() {

    // Given
    BrainModel model = new BrainModel();
    model.init();

    // When
    try {
      model.add(2);
      model.divide(0);
    } catch (Exception e) {

    }

    // Then
    assertEquals(
        "Get divide by zero result failed",
        2, model.getResult()
    );
  }
}
