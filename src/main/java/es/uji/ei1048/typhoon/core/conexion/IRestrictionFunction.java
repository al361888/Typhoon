package es.uji.ei1048.typhoon.core.conexion;

import es.uji.ei1048.typhoon.core.exception.InvalidCoordinatesException;
import es.uji.ei1048.typhoon.core.exception.NoCityFoundException;
import es.uji.ei1048.typhoon.core.model.City;
import es.uji.ei1048.typhoon.core.model.Coordinates;
import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.IOException;

public interface IRestrictionFunction {
    WeatherStatus getStatusCity(City city) throws IOException, NoCityFoundException;
    WeatherStatus getStatusCoord(Coordinates coordinates) throws IOException, InvalidCoordinatesException;

}
