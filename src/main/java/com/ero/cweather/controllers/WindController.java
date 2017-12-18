package com.ero.cweather.controllers;

import com.ero.cweather.models.Weather;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class WindController {
    @FXML
    public JFXSlider sldrWind;

    @FXML
    public JFXCheckBox chckbxNull;

    private Weather weather;
    private boolean submitted;

    public void onOKActionListener(ActionEvent actionEvent) {
        if (!chckbxNull.isSelected()) weather.wind_speed = sldrWind.getValue();
        submitted = true;
        onCancelActionListener(actionEvent);
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;

        if (weather.wind_speed != -1.0) {
            sldrWind.setValue(weather.wind_speed);
            chckbxNull.setSelected(false);
        } else chckbxNull.setSelected(true);

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

    public void onNullChecked(ActionEvent actionEvent) {
        weather.wind_speed = -1.0;
    }
}
