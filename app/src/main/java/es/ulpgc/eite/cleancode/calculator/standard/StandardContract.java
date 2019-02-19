package es.ulpgc.eite.cleancode.calculator.standard;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;
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

    void init();
    void configChanged();
    void buttonClicked(String button);
  }

  interface Router {
    void navigateToBasicScreen();
    //void passStateToBasicScreen(StandardViewModel viewModel);
    void passStateToBasicScreen(SharedState state, List<SharedState> history);
    SharedState getStateFromBasicScreen();
    List<SharedState> getHistoryFromBasicScreen();
  }
}
