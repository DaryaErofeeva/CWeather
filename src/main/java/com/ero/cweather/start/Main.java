package com.ero.cweather.start;

import com.ero.cweather.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        Parent fxmlMain = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(stage);

        stage.setMinHeight(340);
        stage.setMinWidth(320);
        stage.setTitle("CWeather");


        stage.setScene(new Scene(fxmlMain));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
