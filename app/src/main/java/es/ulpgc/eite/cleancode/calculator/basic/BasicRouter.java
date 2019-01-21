package es.ulpgc.eite.cleancode.calculator.basic;

import android.content.Intent;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;

public class BasicRouter implements BasicContract.Router {

  public static String TAG = BasicRouter.class.getSimpleName();

  public WeakReference<FragmentActivity> context;

  public BasicRouter(WeakReference<FragmentActivity> context) {
    this.context = context;
  }

  @Override
  public void navigateToNextScreen() {
    Intent intent = new Intent(context.get(), BasicActivity.class);
    context.get().startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(String data) {
    //AppMediator mediator = (AppMediator) context.get().getApplication();
    //mediator.setData(data);
  }

  @Override
  public String getDataFromPreviousScreen() {
    //AppMediator mediator = (AppMediator) context.get().getApplication();
    //String data = mediator.getData();
    //mediator.resetData();
    //return data;

    return null;
  }
}
