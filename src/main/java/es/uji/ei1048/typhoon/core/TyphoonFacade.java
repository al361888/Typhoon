package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

public class TyphoonFacade {

    private String openWeatherUrl = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=af04e9aa5c54a3a096f2178fc79f10c2";

    public WeatherStatus currentWeatherCity(City city) throws NoCityFoundException{
        throw new UnsupportedOperationException("Unimplemented");
    }

    public WeatherStatus currentWeatherCoordinates(Coordinates coordinates) throws InvalidCoordinatesException{
        throw new UnsupportedOperationException("Unimplemented");
    }

}
