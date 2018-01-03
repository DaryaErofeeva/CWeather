package com.ero.cweather.weather;

import com.ero.cweather.ip.Address;
import com.ero.cweather.models.Weather;
import com.ero.cweather.weather.weatherlibrary.datamodel.Forecast;
import com.ero.cweather.weather.weatherlibrary.datamodel.Forecastday;
import com.ero.cweather.weather.weatherlibrary.datamodel.Hour;
import com.ero.cweather.weather.weatherlibrary.datamodel.WeatherModel;
import com.ero.cweather.weather.weatherlibraryjava.IRepository;
import com.ero.cweather.weather.weatherlibraryjava.Repository;
import com.ero.cweather.weather.weatherlibraryjava.RequestBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeatherSearcher {

    private static final String KEY = "0d473994bb574b6ea83165457171211";
    private static List<Forecastday> forecastdays;

    public static List<String> getDates(Weather weather) {
        initForecastdays();

        List<String> dates = new ArrayList<>();

        for (Forecastday forecastday : forecastdays)
            if (isForecastEqualsWeather(forecastday, weather))
                dates.add(forecastday.date.substring(8) + "-" + forecastday.date.substring(5, 7));

        if (dates.size() == 0) {
            dates.add("Не удалось");
            dates.add("подобрать");
            dates.add("дату");
        }

        return dates;
    }

    public static List<Forecastday> getDays(Weather weather) {
        initForecastdays();

        List<Forecastday> days = new ArrayList<>();

        for (Forecastday forecastday : forecastdays)
            if (isForecastEqualsWeather(forecastday, weather)) days.add(forecastday);

        return days;
    }

    private static void initForecastdays() {
        if (forecastdays == null) {
            IRepository repo = new Repository();
            try {
                WeatherModel weatherModel = repo.GetWeatherData(KEY, RequestBlocks.GetBy.IPAddress, Address.getIPAddress(), RequestBlocks.Days.Ten);
                forecastdays = weatherModel.getForecast().getForecastday();
            } catch (
                    Exception e)

            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static boolean isForecastEqualsWeather(Forecastday forecastday, Weather weather) {
        if (weather.temp != -60 && (weather.temp > forecastday.getDay().maxtemp_c || weather.temp < forecastday.getDay().mintemp_c))
            return false;
        if (weather.wind_speed != -1 && weather.wind_speed > forecastday.getDay().maxwind_kph)
            return false;
        if (weather.raining != null && weather.raining != getWillItRain(forecastday.getHour()))
            return false;
        if (weather.snowing != null && weather.snowing != getWillItSnow(forecastday.getHour()))
            return false;
        return true;
    }

    private static boolean getWillItRain(List<Hour> hours) {
        for (Hour hour : hours)
            if (hour.will_it_rain == 1) return true;
        return false;
    }

    private static boolean getWillItSnow(List<Hour> hours) {
        for (Hour hour : hours)
            if (hour.will_it_snow == 1) return true;
        return false;
    }
}
