package com.vova_cons.engine.common.logger;

import java.io.OutputStream;

public abstract class Logger {
    private static Logger instance = new OstreamLogger(System.out);

    public static final int INFO = 1;
    public static final int ERROR = 2;
    public static final int FATAL_ERROR = 3;

    public static void log(String message){
        instance.print(message + "\n");
    }

    public static void log(String message, int type){
        switch(type){
            case INFO:
                instance.print(FormatedLog.INFO + message + "\n"); break;
            case ERROR:
                instance.print(FormatedLog.ERROR + message + "\n"); break;
            case FATAL_ERROR:
                instance.print(FormatedLog.FATAL_ERROR + message + "\n");
                instance.print("Принудительная остановка программы.\n");
                System.exit(-1);
        }
    }



    public static void create(OutputStream out){
        instance = new OstreamLogger(out);
    }

    abstract void print(String message);
}
