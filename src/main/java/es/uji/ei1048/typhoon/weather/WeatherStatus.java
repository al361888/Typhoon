package es.uji.ei1048.typhoon.weather;

import java.util.Objects;

public class WeatherStatus {
    private float temp;
    private float pressure;
    private int humidity;
    private float tempMin;
    private float tempMax;


    public WeatherStatus(){
        this.temp = 0;
        this.pressure = 0;
        this.humidity = 0;
        this.tempMin = 0;
        this.tempMax = 0;
    }

    public WeatherStatus(float temp, float pressure, int humidity, float tempMin, float tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherStatus)) return false;
        WeatherStatus status = (WeatherStatus) o;
        return Float.compare(status.getTemp(), getTemp()) == 0 &&
                Float.compare(status.getPressure(), getPressure()) == 0 &&
                getHumidity() == status.getHumidity() &&
                Float.compare(status.getTempMin(), getTempMin()) == 0 &&
                Float.compare(status.getTempMax(), getTempMax()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemp(), getPressure(), getHumidity(), getTempMin(), getTempMax());
    }
}
