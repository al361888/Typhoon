package es.uji.ei1048.typhoon.core.conexion;

import es.uji.ei1048.typhoon.core.exception.InvalidCoordinatesException;
import es.uji.ei1048.typhoon.core.exception.NoCityFoundException;
import es.uji.ei1048.typhoon.core.model.City;
import es.uji.ei1048.typhoon.core.model.Coordinates;
import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

public interface IServerConexion {
    WeatherStatus getCurrentWeatherAtCity(City city) throws IOException, NoCityFoundException;
    WeatherStatus getCurrentWeatherAtCoordinates(Coordinates coord) throws InvalidCoordinatesException, IOException;
    InputStream connection(String apiUrl) throws MalformedURLException, UnknownHostException;
    WeatherStatus fetchJsonData(InputStream inputStream) throws IOException;

}
