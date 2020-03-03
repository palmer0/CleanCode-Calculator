package es.ulpgc.eite.cleancode.calculator.standard;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

public class StandardScreen {

  public static void configure(StandardContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    StandardState state = mediator.standardState;

    StandardContract.Router router = new StandardRouter(mediator);
    StandardContract.Presenter presenter = new StandardPresenter(state);
    BrainContract.Model model = new BrainModel();
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
