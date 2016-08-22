package com.vova_cons.common;

public class FloatCommon {
    public static float EPSILON = (float) 0.01;

    public static boolean isEquals(float a, float b){
        return Math.abs(a - b) < EPSILON;
    }
}
