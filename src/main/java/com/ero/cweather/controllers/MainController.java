package com.ero.cweather.controllers;

import com.ero.cweather.db.collections.WeatherCollection;
import com.ero.cweather.models.Weather;
import com.ero.cweather.weather.WeatherSearcher;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXMasonryPane masonryPane;

    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage mainStage;

    private FXMLLoader fxmlLoaderTitle = new FXMLLoader();
    private Parent fxmlTitle;
    private Stage titleStage;

    private FXMLLoader fxmlLoaderTemperature = new FXMLLoader();
    private Parent fxmlTemperature;
    private Stage temperatureStage;

    private FXMLLoader fxmlLoaderWind = new FXMLLoader();
    private Parent fxmlWind;
    private Stage windStage;

    private static WeatherCollection weatherCollection;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        fxmlLoader.setResources(resources);

        weatherCollection = new WeatherCollection();
        fillData();

        init();
    }

    private static void fillData() {
        weatherCollection.fillWeatherList();
    }


    private void init() {
        ArrayList<Node> children = new ArrayList<>();
        for (Weather weather : weatherCollection.getWeatherObservableList()) {
            StackPane child = new StackPane();
            double width = 200;
            double height = 200;
            child.setMinSize(width, height);
            child.setMaxSize(width, height);
            child.setPrefSize(width, height);

            JFXDepthManager.setDepth(child, 1);
            children.add(child);

            // create content
            StackPane header = new StackPane();
            String headerColor = getDefaultColor(weatherCollection.getWeatherObservableList().indexOf(weather));
            header.setStyle("-fx-background-color: " + headerColor);

            Label l = new Label();
            for (String date : WeatherSearcher.getDates(weather))
                l.setText(l.getText() + date + "\n");
            header.getChildren().add(l);

            VBox.setVgrow(header, Priority.ALWAYS);
            StackPane body = new StackPane();
            body.setMinHeight(Math.random() * 20 + 50);
            body.getChildren().add(new Label(weather.getTitle()));
            VBox content = new VBox();
            content.getChildren().addAll(header, body);
            body.setStyle("-fx-background-color: rgb(255,255,255,0.87);");
            child.getChildren().add(content);

            setContextMenu(child);
        }
        masonryPane.getChildren().addAll(children);
    }

    private void setContextMenu(Node node) {
        MenuItem titleItem = new MenuItem("Имя задачи");
        titleItem.setOnAction(event -> showTitleWindow());

        MenuItem temperatureItem = new MenuItem("Температура");
        temperatureItem.setOnAction(event -> showTemperatureWindow());

        MenuItem windItem = new MenuItem("Скорость ветра");
        windItem.setOnAction(event -> showWindWindow());

        ContextMenu contextMenu = new ContextMenu(
                titleItem,
                temperatureItem,
                windItem
        );

        node.setOnContextMenuRequested(event -> contextMenu.show(node, Side.TOP, 0, 90));
    }

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
    }

    public void addTask(ActionEvent actionEvent) {
        showTitleWindow();
    }

    private void showTitleWindow(){
        try {
            if (titleStage == null) {
                fxmlLoaderTitle.setLocation(getClass().getResource("/fxml/Title.fxml"));
                fxmlTitle = fxmlLoaderTitle.load();
                titleStage = new Stage();
                titleStage.setScene(new Scene(fxmlTitle));
                titleStage.setResizable(false);
                titleStage.initModality(Modality.WINDOW_MODAL);
                titleStage.initOwner(mainStage);
            }
            titleStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTemperatureWindow(){
        try {
            if (temperatureStage == null) {
                fxmlLoaderTemperature.setLocation(getClass().getResource("/fxml/Temperature.fxml"));
                fxmlTemperature = fxmlLoaderTemperature.load();
                temperatureStage = new Stage();
                temperatureStage.setScene(new Scene(fxmlTemperature));
                temperatureStage.setResizable(false);
                temperatureStage.initModality(Modality.WINDOW_MODAL);
                temperatureStage.initOwner(mainStage);
            }
            temperatureStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWindWindow(){
        try {
            if (windStage == null) {
                fxmlLoaderWind.setLocation(getClass().getResource("/fxml/Wind.fxml"));
                fxmlWind = fxmlLoaderWind.load();
                windStage = new Stage();
                windStage.setScene(new Scene(fxmlWind));
                windStage.setResizable(false);
                windStage.initModality(Modality.WINDOW_MODAL);
                windStage.initOwner(mainStage);
            }
            windStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
