package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

public interface IDataBaseConexion {
    WeatherStatus getCityStatus(String city);
    WeatherStatus getCoordStatus(String lat, String lon);

}
