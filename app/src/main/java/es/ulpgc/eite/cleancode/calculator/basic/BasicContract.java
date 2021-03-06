package es.ulpgc.eite.cleancode.calculator.basic;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;

public interface BasicContract {


  interface View {
    void injectPresenter(Presenter presenter);

    void navigateToStandardScreen();

    void display(String text);
    void displayWarning(String text);
    void finishStandardScreen();

  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(BrainContract.Model model);

    void start();
    void configChanged();
    void buttonClicked(String button);
    void stop();
  }

}
