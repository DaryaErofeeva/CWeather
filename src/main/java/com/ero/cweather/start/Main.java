package com.ero.cweather.start;

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

        stage.setScene(new Scene(fxmlMain, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
