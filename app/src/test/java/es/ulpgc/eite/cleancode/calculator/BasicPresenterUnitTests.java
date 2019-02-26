package es.ulpgc.eite.cleancode.calculator;


import org.junit.Test;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.basic.BasicContract;
import es.ulpgc.eite.cleancode.calculator.basic.BasicPresenter;
import es.ulpgc.eite.cleancode.calculator.basic.BasicState;
import es.ulpgc.eite.cleancode.calculator.basic.mocks.MockBasicRouter;
import es.ulpgc.eite.cleancode.calculator.basic.mocks.MockBasicActivity;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

import static org.junit.Assert.assertEquals;

public class BasicPresenterUnitTests {

  @Test
  public void testDisplayFromPresenter() {

    // Given
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();

    // Then
    assertEquals(
        "TestDisplayFromPresenter: Get display failed",
        "0", presenter.getDisplay()
    );
  }

  @Test
  public void testDisplayFromView() {

    // Given
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();

    // Then
    assertEquals(
        "TestDisplayFromView: Get display failed",
        "0", activity.getDisplay()
    );

  }



  @Test
  public void testDisplayNumberClicked() {

    // Given
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
    BasicState state = new BasicState();
    BasicPresenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    MockBasicRouter router = new MockBasicRouter();
    MockBasicActivity activity = new MockBasicActivity();
    BasicContract.View view = activity;
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.init();
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
