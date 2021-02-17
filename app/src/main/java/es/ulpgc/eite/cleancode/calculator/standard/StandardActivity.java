package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.cleancode.calculator.R;
import es.ulpgc.eite.cleancode.calculator.basic.BasicActivity;

public class StandardActivity extends AppCompatActivity
    implements StandardContract.View, View.OnClickListener {

  public static String TAG = StandardActivity.class.getSimpleName();

  private StandardContract.Presenter presenter;

  private boolean configChanged;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_standard);

    findViewById(R.id.button0).setOnClickListener(this);
    findViewById(R.id.button1).setOnClickListener(this);
    findViewById(R.id.button2).setOnClickListener(this);
    findViewById(R.id.button3).setOnClickListener(this);
    findViewById(R.id.button4).setOnClickListener(this);
    findViewById(R.id.button5).setOnClickListener(this);
    findViewById(R.id.button6).setOnClickListener(this);
    findViewById(R.id.button7).setOnClickListener(this);
    findViewById(R.id.button8).setOnClickListener(this);
    findViewById(R.id.button9).setOnClickListener(this);

    findViewById(R.id.buttonClear).setOnClickListener(this);
    findViewById(R.id.buttonBackspace).setOnClickListener(this);
    findViewById(R.id.buttonUndo).setOnClickListener(this);
    findViewById(R.id.buttonEqual).setOnClickListener(this);
    findViewById(R.id.buttonMinus).setOnClickListener(this);
    findViewById(R.id.buttonPlus).setOnClickListener(this);

    findViewById(R.id.buttonMult).setOnClickListener(this);
    findViewById(R.id.buttonDiv).setOnClickListener(this);

    // do the setup
    StandardScreen.configure(this);

    if(savedInstanceState != null){
      configChanged = true;
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    presenter.start();

    if(configChanged) {
      presenter.configChanged();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.stop();
  }


  @Override
  public void navigateToBasicScreen() {
    Intent intent = new Intent(this, BasicActivity.class);
    startActivity(intent);
  }


  @Override
  public void onClick(View view) {
    String button = ((Button) view).getText().toString();
    presenter.buttonClicked(button);
  }


  @Override
  public void display(String text) {
    TextView display = findViewById(R.id.textDisplay);
    display.setText(text);
  }

  @Override
  public void displayWarning(String text) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }

  @Override
  public void finishBasicScreen() {
    finish();
  }


  @Override
  public void injectPresenter(StandardContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
