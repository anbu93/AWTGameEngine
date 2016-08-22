package com.vova_cons.engine;

import com.vova_cons.engine.core.Core;
import com.vova_cons.engine.core_loader.CoreLoader;

public class App {
    public static void main( String[] args ){
        new App().main();
    }

    public void main() {
        CoreLoader.loadFrom("assets/core.xml");
        Core.core.startMainLoop();
    }
}
