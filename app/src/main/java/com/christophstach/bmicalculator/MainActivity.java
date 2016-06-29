package com.christophstach.bmicalculator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private BmiCalculatorApplication app;
    private AdView avBottom;
    private EditText etHeight;
    private EditText etWeight;
    private TextView tvBmi;
    private TextView tvBmiMessage;
    private Toolbar toolbar;

    /**
     * Main
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar);

        this.etHeight = (EditText) findViewById(R.id.height);
        this.etWeight = (EditText) findViewById(R.id.weight);
        this.tvBmi = (TextView) findViewById(R.id.bmi);
        this.tvBmiMessage = (TextView) findViewById(R.id.bmiMessage);
        this.avBottom = (AdView) findViewById(R.id.adView);
        this.app            = (BmiCalculatorApplication) getApplication();


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9070336419260953~7443672429");

        AdRequest.Builder builder = new AdRequest.Builder();
        AdRequest adRequest = builder.build();



        this.avBottom.loadAd(adRequest);



        this.etHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.recalculateBmi();
            }
        });


        this.etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.recalculateBmi();
            }
        });

        this.etWeight.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER) {
                    MainActivity.this.etWeight.clearFocus();

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    /**
     * Überträgt das Menü auf die Toolbar.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Behandelt die Events beim Klicken der Menü-Buttons
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_help:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setTitle("BMI Beschreibung");
                builder.setMessage(getString(R.string.bmi_description));
                AlertDialog dialog = builder.create();
                dialog.show();

                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTextSize(18);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Berechnet den BMI und schreibt ihn in das Textfeld.
     */
    private void recalculateBmi() {
        this.app.setBmiWeight(this.etWeight.getText().toString());
        this.app.setBmiHeight(this.etHeight.getText().toString());

        this.app.calculateBmi();

        this.tvBmi.setText(this.app.getBmi() != 0.0 ? String.format(getString(R.string.txt_bmi_value), this.app.getBmi()) : "");
        this.tvBmi.setTextColor(this.app.getClassColor());
        this.tvBmiMessage.setText(this.app.getMessage());
        this.tvBmiMessage.setTextColor(this.app.getClassColor());

        //this.tvBmiMessage.setTextColor(R.color.overweight));


    }


    /**
     * AdMob
     */
    @Override
    public void onPause() {
        if (this.avBottom != null) {
            this.avBottom.pause();
        }

        super.onPause();
    }

    /**
     * AdMob
     */
    @Override
    public void onResume() {
        super.onResume();

        if (this.avBottom != null) {
            this.avBottom.resume();
        }
    }

    /**
     * AdMob
     */
    @Override
    public void onDestroy() {
        if (this.avBottom != null) {
            this.avBottom.destroy();
        }

        super.onDestroy();
    }
}
