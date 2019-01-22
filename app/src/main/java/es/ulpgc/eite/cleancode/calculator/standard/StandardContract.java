package es.ulpgc.eite.cleancode.calculator.standard;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;

interface StandardContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void display(String text);
    void displayWarning(String text);
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
    void passDataToBasicScreen(StandardViewModel viewModel);
    SharedState getDataFromBasicScreen();
  }
}
