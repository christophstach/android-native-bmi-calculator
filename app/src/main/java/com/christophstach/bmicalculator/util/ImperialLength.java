package com.christophstach.bmicalculator.util;

/**
 * Created by Christoph Stach on 24.06.2016.
 */
public class ImperialLength {
    private int feet;
    private float inches;

    final static float DIVERDER = 5.5f;

    /**
     *
     * @param feet
     */
    public ImperialLength(float feet) {
        this.feet = (int) Math.floor(feet);
    }

    /**
     *
     * @param feet
     * @param inches
     */
    public ImperialLength(int feet, float inches) {
        this.feet   = feet;
        this.inches = inches;
    }

    /**
     *
     * @return
     */
    public int getFeet() {
        return feet;
    }

    /**
     *
     * @param feet
     */
    public void setFeet(float feet) {
        this.feet = (int) Math.floor(feet);
    }

    /**
     *
     * @return
     */
    public float getInches() {
        return this.inches;
    }

    /**
     *
     * @param inches
     */
    public void setInches(float inches) {
        this.inches = inches;
    }

    /**
     *
     * @param feet
     * @return
     */
    public static float feetToInches(float feet) {
        return feet;
    }

    /**
     *
     * @param inches
     * @return
     */
    public static float inchesToFeet(float inches) {
        return inches;
    }
}
