package es.ulpgc.eite.cleancode.calculator.standard;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;

interface StandardContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void display(String text);
    void displayWarning(String text);

    void finishBasicScreen();
    void navigateToBasicScreen();
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
