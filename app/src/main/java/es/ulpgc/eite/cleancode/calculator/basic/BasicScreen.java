package es.ulpgc.eite.cleancode.calculator.basic;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

public class BasicScreen {

  public static void configure(BasicContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    BasicContract.Router router = new BasicRouter(context);
    BasicContract.Presenter presenter = new BasicPresenter(context);
    BrainContract.Model model = new BrainModel(context);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }


}
