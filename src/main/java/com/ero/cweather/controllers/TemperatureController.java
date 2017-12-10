package com.ero.cweather.controllers;

import com.ero.cweather.models.Weather;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TemperatureController {
    @FXML
    public JFXSlider sldrTemperature;

    private Weather weather;
    private boolean submitted;

    public void onOKActionListener(ActionEvent actionEvent) {
        weather.temp = sldrTemperature.getValue();
        submitted = true;
        onCancelActionListener(actionEvent);
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;

        if (weather.temp != null)
            sldrTemperature.setValue(weather.temp);

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
