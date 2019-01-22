package es.ulpgc.eite.cleancode.calculator.standard;

import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.brain.BrainContract;
import es.ulpgc.eite.cleancode.calculator.brain.BrainModel;

public class StandardScreen {

  public static void configure(StandardContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    StandardContract.Router router = new StandardRouter(context);
    StandardContract.Presenter presenter = new StandardPresenter(context);
    BrainContract.Model model = new BrainModel(context);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
