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

    public Weather add(Weather weather) {
        weather = WeatherDAO.insert(weather);
        weatherObservableList.add(weather);
        return weather;
    }

    public Weather edit(Weather weather) {
        int index = weatherObservableList.indexOf(weather);
        weather = WeatherDAO.update(weather);
        weatherObservableList.set(index, weather);
        return weather;
    }

    public int delete(Weather weather) {
        int index = weatherObservableList.indexOf(weather);
        WeatherDAO.drop(weather);
        weatherObservableList.remove(weather);
        return index;
    }

    public ObservableList<Weather> getWeatherObservableList() {
        return weatherObservableList;
    }

    public void fillWeatherList() {
        weatherObservableList.clear();
        weatherObservableList.addAll(WeatherDAO.selectAll());
    }
}
