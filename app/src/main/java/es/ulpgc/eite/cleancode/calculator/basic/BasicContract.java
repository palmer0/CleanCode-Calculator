package es.ulpgc.eite.cleancode.calculator.basic;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;

public interface BasicContract {


  interface View {
    void injectPresenter(Presenter presenter);

    void display(String text);
    void displayWarning(String text);
    void finishStandardScreen();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(BrainContract.Model model);
    void injectRouter(Router router);

    void init();
    void configChanged();
    void buttonClicked(String button);

  }

  interface Router {
    void navigateToStandardScreen();
    void passStateToStandardScreen(SharedState state, List<SharedState> history);
    SharedState getStateFromStandardScreen();
    List<SharedState> getHistoryFromStandardScreen();

  }
}
