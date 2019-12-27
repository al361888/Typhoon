package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface IServerConexion {
    WeatherStatus getCurrentWeatherAtCity(City city) throws IOException, NoCityFoundException;
    InputStream connection(String apiUrl) throws MalformedURLException;
    WeatherStatus fetchJsonData(InputStream inputStream) throws IOException;

}
