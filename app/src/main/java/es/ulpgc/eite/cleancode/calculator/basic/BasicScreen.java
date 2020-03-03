package es.ulpgc.eite.cleancode.calculator.basic;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

public class BasicScreen {

  public static void configure(BasicContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    BasicState state = mediator.basicState;

    BasicContract.Router router = new BasicRouter(mediator);
    BasicContract.Presenter presenter = new BasicPresenter(state);
    BrainContract.Model model = new BrainModel();
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }


}
