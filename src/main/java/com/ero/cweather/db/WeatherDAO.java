package com.ero.cweather.db;

import com.backendless.Backendless;
import com.ero.cweather.models.Weather;

import java.util.List;

public class WeatherDAO {
    private static final String APPLICATION_ID = "D8F33248-2281-9701-FF95-E6D737E37900";
    private static final String SECRET_KEY = "AEAADC5C-EE33-858B-FF15-7EA0EA25C300";

    public static void insert(Weather weather) {
        Backendless.initApp(APPLICATION_ID, SECRET_KEY);
        Backendless.Persistence.of(Weather.class).save(weather);
    }

    public static void update(Weather Weather) {
        Backendless.initApp(APPLICATION_ID, SECRET_KEY);
        Backendless.Persistence.of(Weather.class).save(Weather);
    }

    public static List<Weather> selectAll() {
        Backendless.initApp(APPLICATION_ID, SECRET_KEY);
        return Backendless.Persistence.of(Weather.class).find();
    }

    public static void drop(Weather weather) {
        Backendless.initApp(APPLICATION_ID, SECRET_KEY);
        Backendless.Persistence.of(Weather.class).remove(weather);
    }
}