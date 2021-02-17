package es.ulpgc.eite.cleancode.calculator;


import org.junit.Test;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.basic.BasicContract;
import es.ulpgc.eite.cleancode.calculator.basic.BasicPresenter;
import es.ulpgc.eite.cleancode.calculator.basic.mocks.MockBasicActivity;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

import static org.junit.Assert.assertEquals;

public class BasicPresenterUnitTests {

  @Test
  public void testDisplayFromPresenter() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();

    // Then
    assertEquals(
        "TestDisplayFromPresenter: Get display failed",
        "0", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayFromView() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();

    // Then
    assertEquals(
        "TestDisplayFromView: Get display failed",
        "0", activity.getDisplay()
    );

  }



  @Test
  public void testDisplayNumberClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");

    // Then
    assertEquals(
        "TestDisplayNumberClicked: Get display failed",
        "23", activity.getDisplay()
    );

  }

  @Test
  public void testDisplayDelClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("Del");

    // Then
    assertEquals(
        "TestDisplayDelClicked: Get display failed",
        "2", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayClrClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("Clr");

    // Then
    assertEquals(
        "TestDisplayClrClicked: Get display failed",
        "0", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayAddClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("+");

    // Then
    assertEquals(
        "TestDisplayAddClicked: Get display failed",
        "23", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayAddAndNumberClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("+");
    presenter.buttonClicked("5");

    // Then
    assertEquals(
        "TestDisplayAddAndNumberClicked: Get display failed",
        "5", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayAddAndEqualClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("+");
    presenter.buttonClicked("5");
    presenter.buttonClicked("=");

    // Then
    assertEquals(
        "TestDisplayAddAndEqualClicked: Get display failed",
        "28", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayAddAndSubtractClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("+");
    presenter.buttonClicked("5");
    presenter.buttonClicked("-");

    // Then
    assertEquals(
        "TestDisplayAddAndSubtractClicked: Get display failed",
        "28", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayAddAndSubtractAndNumberClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("+");
    presenter.buttonClicked("5");
    presenter.buttonClicked("-");
    presenter.buttonClicked("8");

    // Then
    assertEquals(
        "TestDisplayAddAndSubtractAndNumberClicked: Get display failed",
        "8", activity.getDisplay()
    );
  }

  @Test
  public void testDisplayAddAndSubtractAndEqualClicked() {

    // Given
    AppMediator mediator = AppMediator.getInstance();
    BasicPresenter presenter = new BasicPresenter(mediator);
    BrainContract.Model model = new BrainModel();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.start();
    presenter.buttonClicked("2");
    presenter.buttonClicked("3");
    presenter.buttonClicked("+");
    presenter.buttonClicked("5");
    presenter.buttonClicked("-");
    presenter.buttonClicked("8");
    presenter.buttonClicked("=");

    // Then
    assertEquals(
        "TestDisplayAddAndSubtractAndEqualClicked: Get display failed",
        "20", activity.getDisplay()
    );
  }
}
