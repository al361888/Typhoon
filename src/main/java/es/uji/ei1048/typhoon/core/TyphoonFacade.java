package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import es.uji.ei1048.typhoon.weather.current.CurrentWeather;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class TyphoonFacade {

    private CurrentWeather currentWeather;

    public WeatherStatus currentWeatherCity(City city) throws NoCityFoundException, UnsupportedEncodingException, MalformedURLException {
        //Llamada al metodo de la clase CurrentWeather
        currentWeather = new CurrentWeather();
        return currentWeather.getCurrentWeatherAtCity(city);

        //throw new UnsupportedOperationException("Unimplemented");
    }

    public WeatherStatus currentWeatherCoordinates(Coordinates coordinates) throws InvalidCoordinatesException{
        //Llamada al metodo de la clase CurrentWeather
        currentWeather = new CurrentWeather();
        return currentWeather.getCurrentWeatherAtCoordinates(coordinates);


        // throw new UnsupportedOperationException("Unimplemented");
    }

}
