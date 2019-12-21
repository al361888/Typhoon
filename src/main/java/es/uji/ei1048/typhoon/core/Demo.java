package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class Demo {

    public static void main(String[] args) throws IOException, NoCityFoundException, InvalidCoordinatesException {
        TyphoonFacade typhoonFacade = new TyphoonFacade();
        WeatherStatus ws = typhoonFacade.currentWeatherCity(new City("Valencia"));
        System.out.println(ws.toString());
        System.out.println("######Forecast#########");
        for(int i = 0; i<=3; i++)
            System.out.println("Day "+i+ ": " + typhoonFacade.forecastWeatherCity(new City("Valencia")).get(i).toString());




        //WeatherStatus ws2 = typhoonFacade.currentWeatherCoordinates(new Coordinates(10.6479, -74.2348));
        //System.out.println(ws2.toString());
    }
}
