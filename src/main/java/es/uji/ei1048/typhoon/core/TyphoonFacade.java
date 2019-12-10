package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import es.uji.ei1048.typhoon.weather.current.CurrentWeather;
import es.uji.ei1048.typhoon.weather.current.ForecastWeather;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

public class TyphoonFacade {

    private CurrentWeather currentWeather;
    private ForecastWeather forecastWeather;

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

    public List<WeatherStatus> forecastWeatherCity(City city) throws UnsupportedEncodingException, NoCityFoundException {
        forecastWeather = new ForecastWeather();
        return forecastWeather.getForecastWeatherAtCity(city);
        //throw new UnsupportedOperationException("Unimplemented");
    }
}
