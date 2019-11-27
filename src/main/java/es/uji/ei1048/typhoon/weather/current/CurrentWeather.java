package es.uji.ei1048.typhoon.weather.current;

import es.uji.ei1048.typhoon.core.City;
import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import jdk.nashorn.internal.objects.NativeJSON;

import java.util.List;

public class CurrentWeather {

    private String openWeatherUrl = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=af04e9aa5c54a3a096f2178fc79f10c2";

    public CurrentWeather() {
    }

    public WeatherStatus getCurrentWeatherAtCity(City city){//Incompleto
        //Llamada al server

        //Llamada a la funcion que gestiona el JSON
        return jsonDataGetter();
    }

    public WeatherStatus getCurrentWeatherAtCoordinates(Coordinates coord){//Incompleto
        //Llamada al server

        //Llamada a la funcion que gestiona el JSON
        return jsonDataGetter();
    }

    private WeatherStatus jsonDataGetter(NativeJSON json){
        WeatherStatus status = new WeatherStatus();

        return status;
    }
}
