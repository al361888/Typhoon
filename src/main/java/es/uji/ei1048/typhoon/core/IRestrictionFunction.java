package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.IOException;

public interface IRestrictionFunction {
    WeatherStatus getStatusCity(City city) throws IOException, NoCityFoundException;
    WeatherStatus getStatusCoord(Coordinates coordinates) throws IOException, InvalidCoordinatesException;

}
