package es.uji.ei1048.typhoon.weather;

import java.util.Objects;

public class WeatherStatus {
    private double temp;
    private double pressure;
    private double humidity;
    private double tempMin;
    private double tempMax;


    public WeatherStatus(){
        this.temp = 0;
        this.pressure = 0;
        this.humidity = 0;
        this.tempMin = 0;
        this.tempMax = 0;
    }

    public WeatherStatus(double temp, double pressure, double humidity, double tempMin, double tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherStatus)) return false;
        WeatherStatus status = (WeatherStatus) o;
        return Double.compare(status.getTemp(), getTemp()) == 0 &&
                Double.compare(status.getPressure(), getPressure()) == 0 &&
                Double.compare(status.getHumidity(), getHumidity()) == 0 &&
                Double.compare(status.getTempMin(), getTempMin()) == 0 &&
                Double.compare(status.getTempMax(), getTempMax()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemp(), getPressure(), getHumidity(), getTempMin(), getTempMax());
    }

    @Override
    public String toString() {
        return "WeatherStatus{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}
