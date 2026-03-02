package com.fleetforyou.fleetforyou;

import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.LoaderFXML;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final LoaderFXML loaderFXML = new LoaderFXML();

    @Override
    public void start(Stage stage) throws IOException {
        InjectorProvider.setUp();
        loaderFXML.loadLogin(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}