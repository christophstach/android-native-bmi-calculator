package com.christophstach.bmicalculator;

import android.content.Context;
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
    private AdView adView;
    private EditText txtHeight;
    private EditText txtWeight;
    private TextView txtBmi;
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

        this.txtHeight      = (EditText) findViewById(R.id.height);
        this.txtWeight      = (EditText) findViewById(R.id.weight);
        this.txtBmi         = (TextView) findViewById(R.id.bmi);
        this.adView         = (AdView) findViewById(R.id.adView);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9070336419260953~7443672429");

        AdRequest.Builder builder = new AdRequest.Builder();
        builder.addTestDevice("27071FC12B2D0D94B3220FE2C41EE76F"); //Mein Huawei
        builder.addTestDevice("B98CEB65155AF75CE32D26295C54986C"); //Genymotion
        AdRequest adRequest = builder.build();



        this.adView.loadAd(adRequest);



        this.txtHeight.addTextChangedListener(new TextWatcher() {
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


        this.txtWeight.addTextChangedListener(new TextWatcher() {
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

        this.txtWeight.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER) {
                    MainActivity.this.txtWeight.clearFocus();

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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Berechnet den BMI und schreibt ihn in das Textfeld.
     */
    private void recalculateBmi() {
        if(!this.txtWeight.getText().toString().equals("") && !this.txtHeight.getText().toString().equals("")) {
            Float weight = Float.parseFloat(this.txtWeight.getText().toString());
            Float height = Float.parseFloat(this.txtHeight.getText().toString()) / (float) 100;

            if(height > 0 && weight > 0) {
                Float bmi = weight / (float) Math.pow(height, 2);

                this.txtBmi.setText(String.format(getString(R.string.txt_bmi_value), bmi));

                if(bmi < 18.5) {
                    this.txtBmi.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.orange));
                } else if(bmi < 25) {
                    this.txtBmi.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green));
                } else if(bmi < 30) {
                    this.txtBmi.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.orange));
                } else {
                    this.txtBmi.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                }
            } else {
                this.txtBmi.setText(getString(R.string.txt_bmi_default_value));
                this.txtBmi.setTextColor(Color.BLACK);
            }
        } else {
            this.txtBmi.setText(getString(R.string.txt_bmi_default_value));
            this.txtBmi.setTextColor(Color.BLACK);
        }
    }

    /**
     * AdMob
     */
    @Override
    public void onPause() {
        if (this.adView != null) {
            this.adView.pause();
        }

        super.onPause();
    }

    /**
     * AdMob
     */
    @Override
    public void onResume() {
        super.onResume();

        if (this.adView != null) {
            this.adView.resume();
        }
    }

    /**
     * AdMob
     */
    @Override
    public void onDestroy() {
        if (this.adView != null) {
            this.adView.destroy();
        }

        super.onDestroy();
    }
}
