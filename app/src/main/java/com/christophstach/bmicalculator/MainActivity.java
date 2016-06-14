package com.christophstach.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private Button btnFullscreenAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView bmiDescription     = (TextView) findViewById(R.id.bmiDescription);
        final EditText txtHeight = (EditText) findViewById(R.id.height);
        final EditText txtWeight = (EditText) findViewById(R.id.weight);
        final EditText txtBmi    = (EditText) findViewById(R.id.bmi);

        bmiDescription.setText(Html.fromHtml(getResources().getText(R.string.bmi_description).toString()));


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!txtWeight.getText().toString().equals("") && !txtHeight.getText().toString().equals("")) {
                    Float weight = Float.parseFloat(txtWeight.getText().toString());
                    Float height = Float.parseFloat(txtHeight.getText().toString()) / (float) 100;

                    if(height > 0) {
                        Float bmi = weight / (float) Math.pow(height, 2);

                        txtBmi.setText(String.format("%1$.2f", bmi));
                    }
                }
            }
        };

        txtWeight.addTextChangedListener(textWatcher);
        txtHeight.addTextChangedListener(textWatcher);



        //ArrayList<Integer> array = new ArrayList<Integer>();


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }

        super.onDestroy();
    }
}
