package es.uji.ei1048.typhoon.core.conexion;

import es.uji.ei1048.typhoon.core.exception.StatusNotFoundException;
import es.uji.ei1048.typhoon.core.model.City;
import es.uji.ei1048.typhoon.core.model.Coordinates;
import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.util.List;

public interface IDataBaseOp {
    void insertCity(City city, WeatherStatus w);
    void insertCoord(Coordinates coordinates, WeatherStatus w);
    WeatherStatus getStatusCity(City city) throws StatusNotFoundException;
    WeatherStatus getStatusCoord(Coordinates coordinates) throws StatusNotFoundException;
    void updateFavouriteCity(City city);
    void updateFavouriteCoord(Coordinates coordinates);
    void deleteFavouriteCity(City city);
    void deleteFavouriteCoord(Coordinates coordinates);
    List<City> getFavouriteCity();
    List<Coordinates> getFavouriteCoord();
    void deleteStatus(City city);


}
