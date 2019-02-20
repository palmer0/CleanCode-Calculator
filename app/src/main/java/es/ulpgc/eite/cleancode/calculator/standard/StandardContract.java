package es.ulpgc.eite.cleancode.calculator.standard;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.CommandState;
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
    void passStateToBasicScreen(CommandState state, List<CommandState> history);
    CommandState getStateFromBasicScreen();
    List<CommandState> getHistoryFromBasicScreen();
  }
}
