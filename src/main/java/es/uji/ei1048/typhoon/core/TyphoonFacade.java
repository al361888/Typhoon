package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import es.uji.ei1048.typhoon.weather.current.CurrentWeather;
import es.uji.ei1048.typhoon.weather.current.ForecastWeather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

public class TyphoonFacade {

    private ICurrentWeather currentWeather;
    private ForecastWeather forecastWeather;

    public TyphoonFacade(ICurrentWeather c) {
        this.currentWeather = c;
    }

    public  TyphoonFacade(){
        currentWeather = new CurrentWeather();
        forecastWeather = new ForecastWeather();
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

    public List<WeatherStatus> forecastWeatherCity(City city) throws IOException, NoCityFoundException {
        return forecastWeather.getForecastWeatherAtCity(city);
        //throw new UnsupportedOperationException("Unimplemented");
    }

    public List<WeatherStatus> forecastWeatherCoord(Coordinates coordinates) throws IOException, InvalidCoordinatesException{
        return forecastWeather.getForecastWeatherAtCoordinates(coordinates);
        //throw new UnsupportedOperationException("Unimplemented");
    }
}
