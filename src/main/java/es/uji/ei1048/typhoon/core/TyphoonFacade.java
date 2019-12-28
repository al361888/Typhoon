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

   private CurrentWeather currentWeather;
   private  ForecastWeather forecastWeather;
   private FavouritePlaces favouritePlaces;

   public  TyphoonFacade(){
       currentWeather = new CurrentWeather();
       forecastWeather = new ForecastWeather();
       favouritePlaces = new FavouritePlaces();
   }

    public WeatherStatus currentWeatherCity(City city) throws NoCityFoundException, IOException {
        //Llamada al metodo de la clase CurrentWeather
        return currentWeather.getCurrentWeatherAtCity(city);
    }

    public WeatherStatus currentWeatherCoordinates(Coordinates coordinates) throws InvalidCoordinatesException, IOException {
        //Llamada al metodo de la clase CurrentWeather
        return currentWeather.getCurrentWeatherAtCoordinates(coordinates);
    }

    public List<WeatherStatus> forecastWeatherCity(City city) throws IOException, NoCityFoundException {
        return forecastWeather.getForecastWeatherAtCity(city);

    }

    public List<WeatherStatus> forecastWeatherCoord(Coordinates coordinates) throws IOException, InvalidCoordinatesException{
        return forecastWeather.getForecastWeatherAtCoordinates(coordinates);

    }

    public boolean addFavouriteCity(City city) throws NoCityFoundException, IOException{
        //Llamada al metodo de la clase FavouritePlaces
        return favouritePlaces.addFavouriteCity(city);
    }

    public boolean addFavouriteCoordinates(Coordinates coordinates) throws InvalidCoordinatesException, IOException{
        //Llamada al metodo de la clase FavouritePlaces
        return favouritePlaces.addFavouriteCoordinates(coordinates);
    }

    public List<Place> getFavouritePlaces(){
       return favouritePlaces.getFavourites();
    }
}
