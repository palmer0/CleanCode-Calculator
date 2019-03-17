package es.ulpgc.eite.cleancode.calculator.standard;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.CalculatorState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;

interface StandardContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void display(String text);
    void displayWarning(String text);
    void finishBasicScreen();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(BrainContract.Model model);
    void injectRouter(Router router);

    void start();
    void configChanged();
    void buttonClicked(String button);
    void stop();
  }

  interface Router {
    void navigateToBasicScreen();
    void passStateToBasicScreen(CalculatorState state);
    CalculatorState getStateFromBasicScreen();
  }
}
