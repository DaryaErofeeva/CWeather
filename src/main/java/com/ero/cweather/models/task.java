package com.ero.cweather.models;

import java.time.DayOfWeek;
import java.util.List;

public class task {
    private String title;
    private double min_temp;
    private double max_temp;
    private double min_wind_speed;
    private double max_wind_speed;
    private boolean is_cloudly;
    private boolean is_raining;
    private boolean is_finished;
    private int duration;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public double getMin_wind_speed() {
        return min_wind_speed;
    }

    public void setMin_wind_speed(double min_wind_speed) {
        this.min_wind_speed = min_wind_speed;
    }

    public double getMax_wind_speed() {
        return max_wind_speed;
    }

    public void setMax_wind_speed(double max_wind_speed) {
        this.max_wind_speed = max_wind_speed;
    }

    public boolean getIs_cloudly() {
        return is_cloudly;
    }

    public void setIs_cloudly(boolean is_cloudly) {
        this.is_cloudly = is_cloudly;
    }

    public boolean isIs_raining() {
        return is_raining;
    }

    public void setIs_raining(boolean is_raining) {
        this.is_raining = is_raining;
    }

    public boolean getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(boolean is_finished) {
        this.is_finished = is_finished;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "task{" +
                "title='" + title + '\'' +
                ", min_temp=" + min_temp +
                ", max_temp=" + max_temp +
                ", min_wind_speed=" + min_wind_speed +
                ", max_wind_speed=" + max_wind_speed +
                ", is_cloudly=" + is_cloudly +
                ", is_raining=" + is_raining +
                ", is_finished=" + is_finished +
                ", duration=" + duration +
                '}';
    }
}
