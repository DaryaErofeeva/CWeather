package com.ero.cweather.weather.weatherlibraryjava;

import com.ero.cweather.weather.weatherlibrary.datamodel.WeatherModel;
import com.ero.cweather.weather.weatherlibraryjava.RequestBlocks.MethodType;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Repository implements IRepository {

    private String APIURL = "http://api.apixu.com/v1";
    private WeatherModel weatherModel;
    String url = "";

    Gson gson = new Gson();

    @Override
    public WeatherModel GetWeatherData(String key, RequestBlocks.GetBy getBy, String value,
                                       RequestBlocks.Days ForecastOfDays) throws Exception {
        // TODO Auto-generated method stub
        url = APIURL + RequestBuilder.PrepareRequest(MethodType.Forecast, key, getBy, value, ForecastOfDays);

        WeatherModel tryWeatherModel = GetData(url);

        return tryWeatherModel;
    }

    @Override
    public WeatherModel GetWeatherDataByLatLong(String key, String latitude,
                                                String longitude, RequestBlocks.Days ForecastOfDays) throws Exception {
        // TODO Auto-generated method stub
        url = APIURL + RequestBuilder.PrepareRequestByLatLong(MethodType.Forecast, key, latitude, longitude, ForecastOfDays);

        WeatherModel tryWeatherModel = GetData(url);

        return tryWeatherModel;
    }

    @Override
    public WeatherModel GetWeatherDataByAutoIP(String key, RequestBlocks.Days ForecastOfDays) throws Exception {
        // TODO Auto-generated method stub

        url = APIURL + RequestBuilder.PrepareRequestByAutoIP(MethodType.Forecast, key, ForecastOfDays);

        WeatherModel tryWeatherModel = GetData(url);

        return tryWeatherModel;
    }

    //endregion

    //region Get WeatherSearcher Current Data

    @Override
    public WeatherModel GetWeatherData(String key, RequestBlocks.GetBy getBy, String value) throws Exception {
        // TODO Auto-generated method stub

        url = APIURL + RequestBuilder.PrepareRequest(MethodType.Current, key, getBy, value);

        WeatherModel tryWeatherModel = GetData(url);

        return tryWeatherModel;
    }

    @Override
    public WeatherModel GetWeatherDataByLatLong(String key, String latitude,
                                                String longitude) throws Exception {
        // TODO Auto-generated method stub

        url = APIURL + RequestBuilder.PrepareRequestByLatLong(MethodType.Current, key, latitude, longitude);

        WeatherModel tryWeatherModel = GetData(url);

        return tryWeatherModel;
    }

    @Override
    public WeatherModel GetWeatherDataByAutoIP(String key) throws Exception {
        // TODO Auto-generated method stub

        url = APIURL + RequestBuilder.PrepareRequestByAutoIP(MethodType.Current, key);

        WeatherModel tryWeatherModel = GetData(url);

        return tryWeatherModel;
    }

    //endregion

    private WeatherModel GetData(String url) {
//		getWeatherList WeatherSearcher = new getWeatherList();
//		WeatherSearcher.execute();

        URL obj;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "user");

            int responseCode = con.getResponseCode();
            //        System.out.println("\nSending 'GET' request to URL : " + url);
            //       System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            //	        System.out.println(response.toString());

            JSONParser parser = new JSONParser();

            try {
                Object obj1 = parser.parse(response.toString());


                JSONObject jObj = (JSONObject) obj1;

                weatherModel = gson.fromJson(jObj.toString(), WeatherModel.class);


            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return weatherModel;


    }


}
