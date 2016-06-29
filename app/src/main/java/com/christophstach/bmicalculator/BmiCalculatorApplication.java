package com.christophstach.bmicalculator;

import android.app.Application;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

/**
 * Created by Christoph Stach on 22.06.2016.
 */
public class BmiCalculatorApplication extends Application {
    public final static int COLOR_UNDERWEIGHT = R.color.underweight;
    public final static int COLOR_NORMAL_WEIGHT = R.color.normal_weight;
    public final static int COLOR_OVERWEIGHT = R.color.overweight;
    public final static int COLOR_OBESE_CLASS_1 = R.color.obese_class_1;
    public final static int COLOR_OBESE_CLASS_2 = R.color.obese_class_2;
    public final static int COLOR_OBESE_CLASS_3 = R.color.obese_class_3;

    private boolean imperial = false;
    private float bmiHeight;
    private float bmiWeight;
    private float bmi;
    private String message;
    private int classColor;


    public boolean isImperial() {
        return this.imperial;
    }

    public void setImperial(boolean imperial) {
        this.imperial = imperial;
    }

    public float getBmiHeight() {
        return bmiHeight;
    }

    public void setBmiHeight(float bmiHeight) {
        this.bmiHeight = bmiHeight;
    }

    /**
     *
     * @param bmiHeight
     */
    public void setBmiHeight(String bmiHeight) {
        this.bmiHeight = !bmiHeight.equals("") ? Float.parseFloat(bmiHeight) : 0;
    }

    public float getBmiWeight() {
        return bmiWeight;
    }

    public void setBmiWeight(float bmiWeight) {
        this.bmiWeight = bmiWeight;
    }

    /**
     *
     * @param bmiWeight
     */
    public void setBmiWeight(String bmiWeight) {
        this.bmiWeight = !bmiWeight.equals("") ? Float.parseFloat(bmiWeight) : 0;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public int getClassColor() {
        return this.classColor;
    }

    public void setClassColor(int classColor) {
        this.classColor = classColor;
    }


    /**
     * Berechnet den BMI und alle notwendigen Zusatzinformationen
     */
    public void calculateBmi() {
        if(this.bmiHeight > 0 && this.bmiWeight > 0) {
            this.bmi = this.bmiWeight / (float) Math.pow(this.bmiHeight / 100, 2);

            if(this.bmi < 18.5) {
                this.classColor = ContextCompat.getColor(getApplicationContext(), BmiCalculatorApplication.COLOR_UNDERWEIGHT);
                this.message = getString(R.string.txt_bmi_message_underweight);
            } else if(this.bmi < 25) {
                this.classColor = ContextCompat.getColor(getApplicationContext(), BmiCalculatorApplication.COLOR_NORMAL_WEIGHT);
                this.message = getString(R.string.txt_bmi_message_normal_weight);
            } else if(this.bmi < 30) {
                this.classColor = ContextCompat.getColor(getApplicationContext(), BmiCalculatorApplication.COLOR_OVERWEIGHT);
                this.message = getString(R.string.txt_bmi_message_overweight);
            } else if(bmi < 35) {
                this.classColor = ContextCompat.getColor(getApplicationContext(), BmiCalculatorApplication.COLOR_OBESE_CLASS_1);
                this.message = getString(R.string.txt_bmi_message_obese_class_1);
            } else if(bmi < 40) {
                this.classColor = ContextCompat.getColor(getApplicationContext(), BmiCalculatorApplication.COLOR_OBESE_CLASS_2);
                this.message = getString(R.string.txt_bmi_message_obese_class_2);
            } else {
                this.classColor = ContextCompat.getColor(getApplicationContext(), BmiCalculatorApplication.COLOR_OBESE_CLASS_3);
                this.message = getString(R.string.txt_bmi_message_obese_class_3);
            }
        } else {
            this.bmi = 0;
            this.classColor = Color.GRAY;
            this.message = getString(R.string.txt_bmi_message_default_value);
        }
    }
}
