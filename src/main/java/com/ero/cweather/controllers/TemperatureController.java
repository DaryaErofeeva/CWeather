package com.ero.cweather.controllers;

import com.ero.cweather.models.Weather;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TemperatureController implements Initializable {
    @FXML
    public JFXSlider sldrTemperature;

    @FXML
    public JFXCheckBox chckbxNull;

    private Weather weather;
    private boolean submitted;

    public void onOKActionListener(ActionEvent actionEvent) {
        if (!chckbxNull.isSelected()) weather.temp = sldrTemperature.getValue();
        submitted = true;
        onCancelActionListener(actionEvent);
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;

        if (weather.temp != -60.0) {
            sldrTemperature.setValue(weather.temp);
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
        weather.temp = -60.0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sldrTemperature.valueProperty().addListener((observable, oldValue, newValue) -> chckbxNull.setSelected(false));
    }
}
