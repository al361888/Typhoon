package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.IOException;
import java.io.InputStream;

public interface ICurrentWeather {

    WeatherStatus getCurrentWeatherAtCity(City city) throws IOException, NoCityFoundException;
    WeatherStatus getCurrentWeatherAtCoordinates(Coordinates coord) throws InvalidCoordinatesException, IOException;
    InputStream connection(String apiUrl);
    WeatherStatus fetchJsonData(InputStream inputStream) throws IOException;
}
