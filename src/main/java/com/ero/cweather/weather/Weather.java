package com.ero.cweather.weather;

import com.ero.cweather.weather.weatherlibrary.datamodel.Forecastday;
import com.ero.cweather.weather.weatherlibrary.datamodel.WeatherModel;
import com.ero.cweather.weather.weatherlibraryjava.IRepository;
import com.ero.cweather.weather.weatherlibraryjava.Repository;
import com.ero.cweather.weather.weatherlibraryjava.RequestBlocks;

public class Weather {

    private static final String KEY = "0d473994bb574b6ea83165457171211";

    public static void main(String[] args) {

        Weather obj = new Weather();

        IRepository repo = new Repository();

        try {
            WeatherModel weatherModel = repo.GetWeatherData(obj.KEY, RequestBlocks.GetBy.CityName, "Paris", RequestBlocks.Days.Ten);

            System.out.println("WeatherTestJava : location name==============>" + weatherModel.location.name);
            System.out.println("WeatherTestJava : Temp==============>" + weatherModel.current.temp_c);
            for (Forecastday f : weatherModel.getForecast().getForecastday())
                System.out.println("\tDay=>" + f.date
                        + "\n\tTemp=>" + f.getDay().getMintempC());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
