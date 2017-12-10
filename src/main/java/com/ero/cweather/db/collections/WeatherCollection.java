package com.ero.cweather.db.collections;

import com.ero.cweather.db.WeatherDAO;
import com.ero.cweather.models.Weather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WeatherCollection {
    private ObservableList<Weather> weatherObservableList;

    public WeatherCollection() {
        weatherObservableList = FXCollections.observableArrayList();
    }

    public void add(Weather weather) {
        WeatherDAO.insert(weather);
        fillWeatherList();
    }

    public void edit(Weather weather) {
        WeatherDAO.update(weather);
        fillWeatherList();
    }

    public void delete(Weather weather) {
        WeatherDAO.drop(weather);
        weatherObservableList.remove(weather);
    }

    public ObservableList<Weather> getWeatherObservableList() {
        return weatherObservableList;
    }

    public void fillWeatherList() {
        weatherObservableList.clear();
        weatherObservableList.addAll(WeatherDAO.selectAll());
    }
}
