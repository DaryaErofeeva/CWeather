package com.ero.cweather.controllers;

import com.ero.cweather.models.Weather;
import com.ero.cweather.weather.WeatherSearcher;
import com.ero.cweather.weather.weatherlibrary.datamodel.Forecastday;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class DatesController {
    @FXML
    FlowPane flowPane;

    private String color;
    private Weather weather;

    public void setDates(Weather weather, String color) {
        this.color = color;
        this.weather = weather;

        flowPane.getChildren().clear();
        List<Forecastday> forecastdays = WeatherSearcher.getDays(weather);
        for (Forecastday forecastday : forecastdays)
            flowPane.getChildren().add(createChild(forecastday));
    }


    private Node createChild(Forecastday forecastday) {
        VBox child = new VBox();

        // create content
        VBox header = new VBox();
        header.setStyle("-fx-background-color: " + color);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10, 10, 10, 10));

        Label minTemp = new Label("Минимальная температура " + forecastday.getDay().mintemp_c);
        Label maxTemp = new Label("Максимальная температура " + forecastday.getDay().maxtemp_c);
        Label wind = new Label("Максимальная скорость ветра " + forecastday.getDay().maxwind_mph + " м/час");

        minTemp.setFont(Font.font("Ayuthaya", 14));
        minTemp.setTextFill(Color.WHITE);

        maxTemp.setFont(Font.font("Ayuthaya", 14));
        maxTemp.setTextFill(Color.WHITE);

        wind.setFont(Font.font("Ayuthaya", 14));
        wind.setTextFill(Color.WHITE);

        header.getChildren().addAll(minTemp, maxTemp, wind);


        StackPane footer = new StackPane();
        footer.setStyle("-fx-background-color: white");

        Label titleLabel = new Label(forecastday.date.substring(8) + "-" + forecastday.date.substring(5, 7));
        titleLabel.setTextFill(Color.web(color));
        titleLabel.setFont(Font.font("Ayuthaya", 20));
        footer.getChildren().add(titleLabel);
        footer.setPadding(new Insets(10, 10, 10, 10));

        child.getChildren().addAll(header, footer);

        return child;
    }
}
