package com.ero.cweather.models;

import javafx.beans.property.SimpleStringProperty;

public class Weather {
    private String title;
    private Double temp;
    private Double wind_speed;
    private Boolean cloudy;
    private Boolean raining;
    private Boolean snowing;
    private Boolean finished;
    private Integer duration;
    private String objectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Double getWindSpeed() {
        return wind_speed;
    }

    public void setWindSpeed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public Boolean isCloudy() {
        return cloudy;
    }

    public void setCloudy(boolean cloudy) {
        this.cloudy = cloudy;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Boolean isSnowing() {
        return snowing;
    }

    public void setSnowing(boolean snowing) {
        this.snowing = snowing;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "WeatherSearcher{" +
                "title='" + title + '\'' +
                ", temp=" + temp +
                ", wind_speed=" + wind_speed +
                ", cloudy=" + cloudy +
                ", raining=" + raining +
                ", finished=" + finished +
                ", duration=" + duration +
                '}';
    }
}
