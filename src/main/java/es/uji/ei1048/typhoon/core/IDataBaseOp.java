package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

public interface IDataBaseOp {
    void insertCity(City city, WeatherStatus w);
    void insertCoord(Coordinates coordinates, WeatherStatus w);
    WeatherStatus getStatusCity(City city);
    WeatherStatus getStatusCoord(Coordinates coordinates);
    void deleteCity(City city);

}
