package es.uji.ei1048.typhoon.weather;

import javafx.util.converter.LocalTimeStringConverter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class WeatherStatus {
    private String description;
    private double temp;
    private double pressure;
    private double humidity;
    private double tempMin;
    private double tempMax;
    private double windSpeed;
    private LocalTime time;

    public WeatherStatus(){
        this.description = null;
        this.temp = -1000;
        this.pressure = -1000;
        this.humidity = -1000;
        this.tempMin = -1000;
        this.tempMax = 1000;
        this.windSpeed = -1000;
    }

    public WeatherStatus(String description, double temp, double pressure, double humidity, double tempMin, double tempMax, double windSpeed, LocalTime time) {
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.windSpeed = windSpeed;
        this.time = time;
    }

    public WeatherStatus(String description, double temp, double pressure, double humidity, double tempMin, double tempMax, double windSpeed) {
        this.description = description;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.windSpeed = windSpeed;
        this.time = LocalTime.now();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherStatus that = (WeatherStatus) o;
        return Double.compare(that.temp, temp) == 0 &&
                Double.compare(that.pressure, pressure) == 0 &&
                Double.compare(that.humidity, humidity) == 0 &&
                Double.compare(that.tempMin, tempMin) == 0 &&
                Double.compare(that.tempMax, tempMax) == 0 &&
                Double.compare(that.windSpeed, windSpeed) == 0 &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, temp, pressure, humidity, tempMin, tempMax);
    }

    @Override
    public String toString() {
        return "Description: " +description+ '\n'+
                "Temperature: " + temp + '\n' +
                "Pressure: " + pressure + '\n'+
                "Humidity: " + humidity + '\n'+
                "Temperature Min: " + tempMin + '\n' +
                "Temperature Max: " + tempMax + '\n' +
                "Wind speed: " + windSpeed + '\n' +
                "Last call: " + time.getHour() + ":" + time.getMinute();

    }
}
