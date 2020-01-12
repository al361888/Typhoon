package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.List;

public interface IDataBaseOp {
    void insertCity(City city, WeatherStatus w);
    void insertCoord(Coordinates coordinates, WeatherStatus w);
    WeatherStatus getStatusCity(City city) throws StatusNotFound;
    WeatherStatus getStatusCoord(Coordinates coordinates) throws StatusNotFound;
    void updateFavouriteCity(City city);
    void updateFavouriteCoord(Coordinates coordinates);
    void deleteFavouriteCity(City city);
    void deleteFavouriteCoord(Coordinates coordinates);
    List<City> getFavouriteCity();
    List<Coordinates> getFavouriteCoord();
    void deleteStatus(City city);


}
