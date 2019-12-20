package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import es.uji.ei1048.typhoon.weather.current.CurrentWeather;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class TyphoonFacade {

    private ICurrentWeather currentWeather;

    public TyphoonFacade(ICurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public TyphoonFacade(){
        this.currentWeather = new CurrentWeather();
    }

    public WeatherStatus currentWeatherCity(City city) throws NoCityFoundException, IOException {
        //Llamada al metodo de la clase CurrentWeather
        return currentWeather.getCurrentWeatherAtCity(city);

        //throw new UnsupportedOperationException("Unimplemented");
    }

    public WeatherStatus currentWeatherCoordinates(Coordinates coordinates) throws InvalidCoordinatesException, IOException {
        //Llamada al metodo de la clase CurrentWeather
        return currentWeather.getCurrentWeatherAtCoordinates(coordinates);

        // throw new UnsupportedOperationException("Unimplemented");
    }

}
