package es.ulpgc.eite.cleancode.calculator.basic;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.NumberValidator;

public interface BasicContract {


  interface View {
    void injectPresenter(Presenter presenter);

    void display(String text);
    void displayWarning(String text);
    //void notifyWrongNumberWarning();
    //void notifyOperationErrorWarning();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(BrainContract.Model model);
    void injectRouter(Router router);

    void init();

    void buttonClicked(String button);
//    void dotPressed();
//    String getDisplay();
//    void setDisplay(String d);
//    void backspacePressed();
//    void clearPressed();
//    void digitPressed(String c);
//    void operatorPressed(String c);
  }

  interface Router {
    void navigateToNextScreen();
    void passDataToNextScreen(String data);
    String getDataFromPreviousScreen();
  }
}
