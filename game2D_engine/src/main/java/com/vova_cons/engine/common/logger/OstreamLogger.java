package com.vova_cons.engine.common.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

class OstreamLogger extends Logger {
    private OutputStreamWriter writter;

    OstreamLogger(OutputStream destination){
        writter = new OutputStreamWriter(destination);
    }

    @Override
    void print(String message) {
        try {
            writter.write(message);
            writter.flush();
        } catch (IOException e) {
            System.err.println("Ошибка логгера: " + e.getMessage() + "\n" + message);
        }
    }
}
