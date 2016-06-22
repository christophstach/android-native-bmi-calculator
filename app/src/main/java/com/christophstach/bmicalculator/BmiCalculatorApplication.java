package com.christophstach.bmicalculator;

import android.app.Application;

/**
 * Created by Christoph Stach on 22.06.2016.
 */
public class BmiCalculatorApplication extends Application {
    private boolean useImperialUnits = false;

    public float calculateBmi(float height, float weight) {
        return 5;
    }

    public float calculateBmiImperial(float heightFt, float heightIn, float weight) {
        return 5;
    }

    protected float ftInToCm(float ft, float in) {
        return 5;
    }


}
