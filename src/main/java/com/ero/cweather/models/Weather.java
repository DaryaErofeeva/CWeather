package com.ero.cweather.models;

public class Weather {
    public String title;
    public Double temp;
    public Double wind_speed;
    public Boolean cloudy;
    public Boolean raining;
    public Boolean snowing;
    public Boolean finished;
    public Integer duration;
    public String tag;
    public String objectId;

    public Weather() {
        temp = -60.0;
        wind_speed = -1.0;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "title='" + title + '\'' +
                ", temp=" + temp +
                ", wind_speed=" + wind_speed +
                ", cloudy=" + cloudy +
                ", raining=" + raining +
                ", snowing=" + snowing +
                ", finished=" + finished +
                ", duration=" + duration +
                ", tag=" + tag +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
