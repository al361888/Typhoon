package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RestrictionFunction implements IRestrictionFunction {

    private IServerConexion server;
    private IDataBaseOp dataBase;

    public RestrictionFunction(IServerConexion server, IDataBaseOp dataBase) {
        this.server = server;
        this.dataBase = dataBase;
    }



    @Override
    public WeatherStatus getStatusCity(City city) throws IOException, NoCityFoundException {
        try {
            return dataBase.getStatusCity(city);
        }catch(NullPointerException ex){
            WeatherStatus status = server.getCurrentWeatherAtCity(city);
            dataBase.insertCity(city, status);
            return status;
        }
    }

    @Override
    public WeatherStatus getStatusCoord(Coordinates coordinates) throws IOException, InvalidCoordinatesException {
        try {
            return dataBase.getStatusCoord(coordinates);
        }catch(NullPointerException ex){
            WeatherStatus status = server.getCurrentWeatherAtCoordinates(coordinates);
            dataBase.insertCoord(coordinates, status);
            return status;
        }
    }


}
