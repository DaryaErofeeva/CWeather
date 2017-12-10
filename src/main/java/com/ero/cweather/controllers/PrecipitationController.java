package com.ero.cweather.controllers;

import com.ero.cweather.models.Weather;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class PrecipitationController {
    @FXML
    public JFXToggleButton tbtnRain;

    @FXML
    public JFXToggleButton tbtnSnow;

    private Weather weather;
    private boolean submitted;

    public void onOKActionListener(ActionEvent actionEvent) {
        weather.raining = tbtnRain.isSelected();
        weather.snowing = tbtnSnow.isSelected();
        submitted = true;
        onCancelActionListener(actionEvent);
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
        tbtnRain.setSelected(weather.raining == null ? false : weather.raining);
        tbtnSnow.setSelected(weather.snowing == null ? false : weather.snowing);

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
