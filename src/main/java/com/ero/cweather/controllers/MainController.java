package com.ero.cweather.controllers;

import com.ero.cweather.weather.weatherlibrary.datamodel.Location;
import com.ero.cweather.weather.weatherlibrary.datamodel.WeatherModel;
import com.ero.cweather.weather.weatherlibraryjava.IRepository;
import com.ero.cweather.weather.weatherlibraryjava.Repository;
import com.ero.cweather.weather.weatherlibraryjava.RequestBlocks;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXMasonryPane masonryPane;

    private FXMLLoader fxmlLoader = new FXMLLoader();
    private static final String KEY = "0d473994bb574b6ea83165457171211";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/Main.fxml"));
        fxmlLoader.setResources(resources);
        init();
    }


    public void init() {
        IRepository repo = new Repository();
        ArrayList<Node> children = new ArrayList<>();
        try {
//            WeatherModel weatherModel = repo.GetWeatherData(KEY, RequestBlocks.GetBy.CityName, "Paris", RequestBlocks.Days.Ten);
            WeatherModel weatherModel = repo.GetWeatherDataByAutoIP(KEY, RequestBlocks.Days.Ten);
            for (int i = 0; i < 10; i++) {
                StackPane child = new StackPane();
                double width = Math.random() * 100 + 100;
                double height = Math.random() * 100 + 100;
                child.setMinSize(width, height);
                child.setMaxSize(width, height);
                child.setPrefSize(width, height);

                JFXDepthManager.setDepth(child, 1);
                children.add(child);

                // create content
                StackPane header = new StackPane();
                String headerColor = getDefaultColor(i);
                header.setStyle("-fx-background-color: " + headerColor);
                header.getChildren().add(
                        new Label("Min temp: " + weatherModel.getForecast().getForecastday().get(i).getDay().mintemp_c
                                + "\nAvg temp: " + weatherModel.getForecast().getForecastday().get(i).getDay().avgtemp_c
                                + "\nMax temp: " + weatherModel.getForecast().getForecastday().get(i).getDay().maxtemp_c)
                );
                VBox.setVgrow(header, Priority.ALWAYS);
                StackPane body = new StackPane();
                body.setMinHeight(Math.random() * 20 + 50);
                body.getChildren().add(new Label(weatherModel.getForecast().getForecastday().get(i).date));
                VBox content = new VBox();
                content.getChildren().addAll(header, body);
                body.setStyle("-fx-background-color: rgb(255,255,255,0.87);");
                child.getChildren().add(content);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        masonryPane.getChildren().addAll(children);
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
}
