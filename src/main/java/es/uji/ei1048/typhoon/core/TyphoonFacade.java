package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import es.uji.ei1048.typhoon.weather.current.CurrentWeather;
import es.uji.ei1048.typhoon.weather.current.ForecastWeather;

import java.io.IOException;
import java.util.List;

public class TyphoonFacade {

    private IRestrictionFunction restrictionFunction;
    private ForecastWeather forecastWeather = new ForecastWeather();
    private FavouritePlaces favouritePlaces = new FavouritePlaces();

   public  TyphoonFacade(){
       IServerConexion serverConexion = new CurrentWeather();
       IDataBaseOp dataBaseOp = new DatabaseOp();
       this.restrictionFunction = new RestrictionFunction(serverConexion, dataBaseOp);
   }

    public WeatherStatus currentWeatherCity(City city) throws NoCityFoundException, IOException {
        //Llamada al metodo de la clase CurrentWeather
        return restrictionFunction.getStatusCity(city);

        //throw new UnsupportedOperationException("Unimplemented");
    }

    public WeatherStatus currentWeatherCoordinates(Coordinates coordinates) throws InvalidCoordinatesException, IOException {
        //Llamada al metodo de la clase CurrentWeather
        return restrictionFunction.getStatusCoord(coordinates);

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

    public Boolean deleteFavouriteCity(City city) {
       return favouritePlaces.deleteCity(city);
    }


    public Boolean deleteFavouriteCoordinates(Coordinates coord) {
       return favouritePlaces.deleteCoord(coord);
    }
}
