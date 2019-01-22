package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.basic.BasicActivity;

public class StandardRouter implements StandardContract.Router {

  public static String TAG = StandardRouter.class.getSimpleName();

  public WeakReference<FragmentActivity> context;

  public StandardRouter(WeakReference<FragmentActivity> context) {
    this.context = context;
  }

  @Override
  public void navigateToBasicScreen() {
    Intent intent = new Intent(context.get(), BasicActivity.class);
    context.get().startActivity(intent);
    context.get().finish();
  }

  @Override
  public void passStateToBasicScreen(
      SharedState state, List<SharedState> history
  ) {
    AppMediator mediator = (AppMediator) context.get().getApplication();
    mediator.state = state;
    mediator.history = history;
  }

//  @Override
//  public void passStateToBasicScreen(StandardViewModel viewModel) {
//    AppMediator mediator = (AppMediator) context.get().getApplication();
//    SharedState state = new SharedState();
//    state.number = viewModel.number;
//    state.display = viewModel.display;
//    state.savedOperand = viewModel.savedOperand;
//    state.result = viewModel.result;
//    mediator.state = state;
//
//    /*
//    mediator.number = viewModel.number;
//    mediator.display = viewModel.display;
//    mediator.savedOperand = viewModel.savedOperand;
//    mediator.result = viewModel.result;
//    */
//  }

  @Override
  public SharedState getStateFromBasicScreen() {
    AppMediator mediator = (AppMediator) context.get().getApplication();
    SharedState state = mediator.state;
    mediator.state = null;
    return state;
  }

  @Override
  public List<SharedState> getHistoryFromBasicScreen() {
    AppMediator mediator = (AppMediator) context.get().getApplication();
    List<SharedState> history = mediator.history;
    mediator.history = null;
    return history;
  }
}
