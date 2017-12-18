package com.ero.cweather.weather;

import com.ero.cweather.ip.Address;
import com.ero.cweather.models.Weather;
import com.ero.cweather.weather.weatherlibrary.datamodel.Forecastday;
import com.ero.cweather.weather.weatherlibrary.datamodel.Hour;
import com.ero.cweather.weather.weatherlibrary.datamodel.WeatherModel;
import com.ero.cweather.weather.weatherlibraryjava.IRepository;
import com.ero.cweather.weather.weatherlibraryjava.Repository;
import com.ero.cweather.weather.weatherlibraryjava.RequestBlocks;

import java.util.ArrayList;
import java.util.List;

public class WeatherSearcher {

    private static final String KEY = "0d473994bb574b6ea83165457171211";

    public static List<String> getDates(Weather Weather) {
        List<String> dates = new ArrayList<>();
        IRepository repo = new Repository();
        try {
            WeatherModel weatherModel = repo.GetWeatherData(KEY, RequestBlocks.GetBy.IPAddress, Address.getIPAddress(), RequestBlocks.Days.Ten);
            for (Forecastday f : weatherModel.getForecast().getForecastday()) {
                if (Weather.temp != -60 && (Weather.temp > f.getDay().maxtemp_c || Weather.temp < f.getDay().mintemp_c))
                    continue;
                if (Weather.wind_speed != -1 && Weather.wind_speed > f.getDay().maxwind_kph)
                    continue;
                if (Weather.raining != null && Weather.raining != getWillItRain(f.getHour()))
                    continue;
                if (Weather.snowing != null && Weather.snowing != getWillItSnow(f.getHour()))
                    continue;
                dates.add(f.date.substring(8) + "-" + f.date.substring(5, 7));
            }
            if (dates.size() == 0) {
                dates.add("Не удалось");
                dates.add("подобрать");
                dates.add("дату");
            }
        } catch (
                Exception e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dates;
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
