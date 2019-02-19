package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import es.ulpgc.eite.cleancode.calculator.app.AppMediator;
import es.ulpgc.eite.cleancode.calculator.app.SharedState;
import es.ulpgc.eite.cleancode.calculator.basic.BasicActivity;

public class StandardRouter implements StandardContract.Router {

  public static String TAG = StandardRouter.class.getSimpleName();

  private AppMediator mediator;

  /*
  public WeakReference<FragmentActivity> context;

  public StandardRouter(WeakReference<FragmentActivity> context) {
    this.context = context;
  }
  */

  public StandardRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToBasicScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, BasicActivity.class);
    context.startActivity(intent);
  }


//  @Override
//  public void navigateToBasicScreen() {
//    Intent intent = new Intent(context.get(), BasicActivity.class);
//    context.get().startActivity(intent);
//    context.get().finish();
//  }

  @Override
  public void passStateToBasicScreen(
      SharedState state, List<SharedState> history) {
    //AppMediator mediator = (AppMediator) context.get().getApplication();
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
    //AppMediator mediator = (AppMediator) context.get().getApplication();
    SharedState state = mediator.state;
    mediator.state = null;
    return state;
  }

  @Override
  public List<SharedState> getHistoryFromBasicScreen() {
    //AppMediator mediator = (AppMediator) context.get().getApplication();
    List<SharedState> history = mediator.history;
    mediator.history = null;
    return history;
  }
}
