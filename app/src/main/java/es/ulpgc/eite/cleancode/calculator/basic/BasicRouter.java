package es.ulpgc.eite.cleancode.calculator.basic;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.standard.StandardActivity;

public class BasicRouter implements BasicContract.Router {

  public static String TAG = BasicRouter.class.getSimpleName();

  public WeakReference<FragmentActivity> context;

  public BasicRouter(WeakReference<FragmentActivity> context) {
    this.context = context;
  }

  @Override
  public void navigateToStandardScreen() {
    Intent intent = new Intent(context.get(), StandardActivity.class);
    context.get().startActivity(intent);
    context.get().finish();
  }

  @Override
  public void passDataToStandardScreen(BasicViewModel viewModel) {
    AppMediator mediator = (AppMediator) context.get().getApplication();
    SharedState state = new SharedState();
    state.number = viewModel.number;
    state.display = viewModel.display;
    state.savedOperand = viewModel.savedOperand;
    state.result = viewModel.result;
    mediator.state = state;

    /*
    mediator.number = viewModel.number;
    mediator.display = viewModel.display;
    mediator.savedOperand = viewModel.savedOperand;
    mediator.result = viewModel.result;
    */
  }

  /*
  @Override
  public void passDataToStandardScreen(
      String display, String number, String savedOperand, Integer result
  ) {
    AppMediator mediator = (AppMediator) context.get().getApplication();

  }
  */

  @Override
  public SharedState getDataFromStandardScreen() {
    AppMediator mediator = (AppMediator) context.get().getApplication();
    SharedState state = mediator.state;
    mediator.state = null;
    return state;
  }

}
