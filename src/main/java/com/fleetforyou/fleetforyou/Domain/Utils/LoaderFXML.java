package com.fleetforyou.fleetforyou.Domain.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoaderFXML {
    public void loadDynamic(AnchorPane anchorPane, String fxmlFileName, String tabTitle) {
        try {
            anchorPane.getChildren().clear();

            // Load FXML file
            FXMLLoader loader = new FXMLLoader(LoaderFXML.class.getResource("/com/fleetforyou/fleetforyou/FXML/" + fxmlFileName));
            AnchorPane content = loader.load();

            // Set the loaded content to the AnchorPane
            anchorPane.getChildren().setAll(content);

            // Reset the anchors
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);
            AnchorPane.setTopAnchor(content, 0.0);

            Scene scene = anchorPane.getScene();
            if (scene != null) {
                Stage stage = (Stage) scene.getWindow();

                stage.setTitle(tabTitle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInitial(Stage stage){
        try {
            stage.close();

            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(LoaderFXML.class.getResource("/com/fleetforyou/fleetforyou/FXML/TopBar.fxml"));
            Scene scene = new Scene(loader.load());

            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.setTitle("Main");
            newStage.setScene(scene);

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLogin(Stage stage){
        try {
            stage.close();

            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(LoaderFXML.class.getResource("/com/fleetforyou/fleetforyou/FXML/Login.fxml"));
            Scene scene = new Scene(loader.load());

            newStage.initStyle(StageStyle.DECORATED);
            newStage.setTitle("Login");
            newStage.setScene(scene);

            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
