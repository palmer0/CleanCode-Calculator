package es.ulpgc.eite.cleancode.calculator;

import org.junit.Test;

import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

import static org.junit.Assert.assertEquals;

public class BrainModelUnitTests {

  @Test
  public void testAdd() {

    // Given
    BrainModel model = new BrainModel();

    // When
    model.setNumber(2);
    model.setOperator("+");
    model.execute();
    model.setNumber(4);
    model.setOperator("+");
    model.execute();


    // Then
    assertEquals(
        "TestAdd: Get add result failed",
        6, model.getResult()
    );
  }

  @Test
  public void testMultiply() {

    // Given
    BrainModel model = new BrainModel();

    // When
    model.setNumber(2);
    model.setOperator("+");
    model.execute();
    model.setNumber(4);
    model.setOperator("x");
    model.execute();


    // Then
    assertEquals(
        "TestMultiply: Get multiply result failed",
        8, model.getResult()
    );
  }

  @Test
  public void testDivideByZero() {

    // Given
    BrainModel model = new BrainModel();

    // When
    try {
      model.setNumber(2);
      model.setOperator("+");
      model.execute();
      model.setNumber(0);
      model.setOperator("/");
      model.execute();
    } catch (Exception e) {

    }

    // Then
    assertEquals(
        "TestDivideByZero: Get divide by zero result failed",
        2, model.getResult()
    );
  }
}
