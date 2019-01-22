package es.ulpgc.eite.cleancode.calculator.standard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.ulpgc.eite.cleancode.calculator.R;

public class StandardActivity extends AppCompatActivity
    implements StandardContract.View, View.OnClickListener {

  public static String TAG = StandardActivity.class.getSimpleName();

  private StandardContract.Presenter presenter;

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
    findViewById(R.id.buttonDot).setOnClickListener(this);
    findViewById(R.id.buttonEqual).setOnClickListener(this);
    findViewById(R.id.buttonMinus).setOnClickListener(this);
    findViewById(R.id.buttonPlus).setOnClickListener(this);

    findViewById(R.id.buttonMult).setOnClickListener(this);
    findViewById(R.id.buttonDiv).setOnClickListener(this);

    // do the setup
    StandardScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.init();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);

    Log.e(TAG, "onConfigurationChanged()");

    int orientation = newConfig.orientation;
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
      presenter.configChanged();
    }

  }

  @Override
  public void injectPresenter(StandardContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void onClick(View view) {
    String button = ((Button) view).getText().toString();
    presenter.buttonClicked(button);
  }


  @Override
  public void display(String text) {
    //Log.e(TAG, "display()");
    TextView display = findViewById(R.id.textDisplay);
    display.setText(text);
  }

  @Override
  public void displayWarning(String text) {
    //Log.e(TAG, "displayWarning()");
    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
  }
}
