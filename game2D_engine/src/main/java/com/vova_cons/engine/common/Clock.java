package com.vova_cons.engine.common;

public class Clock {
    private long start;

    public Clock(){
        start = getTime();
    }

    public void start(){
        this.start = getTime();
    }
    public long restartMillis(){
        long result = getMillis();
        start = getTime();
        return result;
    }
    public double restartSeconds(){
        return restartMillis() / 1000.0;
    }

    public long getMillis(){
        return getTime() - start;
    }
    public double getSeconds(){
        return getMillis() / 1000.0;
    }

    public static void sleepMillis(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }


    public static void sleepSeconds(double second){
        sleepMillis((long)(second * 1000));
    }

    private static long getTime(){
        return System.currentTimeMillis();
    }
}
