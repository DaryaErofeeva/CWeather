package com.ero.cweather.controllers;

import com.ero.cweather.models.Weather;
import com.jfoenix.controls.JFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class WindController {
    @FXML
    public JFXSlider sldrWind;

    private Weather weather;
    private boolean submitted;

    public void onOKActionListener(ActionEvent actionEvent) {
        weather.wind_speed = sldrWind.getValue();
        submitted = true;
        onCancelActionListener(actionEvent);
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;

        if (weather.wind_speed != null)
            sldrWind.setValue(weather.wind_speed);

        submitted = false;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void onCancelActionListener(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.hide();
    }
}
