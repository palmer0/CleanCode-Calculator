package es.ulpgc.eite.cleancode.calculator.basic.mocks;

import es.ulpgc.eite.cleancode.calculator.basic.BasicContract;

public class MockBasicActivity implements BasicContract.View {

  private String display;

  public String getDisplay(){
    return display;
  }

  @Override
  public void injectPresenter(BasicContract.Presenter presenter) {

  }

  @Override
  public void display(String text) {
    display = text;
  }

  @Override
  public void displayWarning(String text) {

  }

  @Override
  public void finishStandardScreen() {

  }
}
