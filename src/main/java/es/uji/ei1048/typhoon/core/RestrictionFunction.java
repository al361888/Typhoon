package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import jdk.vm.ci.meta.Local;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            WeatherStatus data = dataBase.getStatusCity(city);
            checkLastCall(data);
            return data;
        }catch(StatusNotFound ex){
            WeatherStatus status = server.getCurrentWeatherAtCity(city);
            dataBase.deleteStatus(city);
            dataBase.insertCity(city, status);
            return status;
        }
    }

    @Override
    public WeatherStatus getStatusCoord(Coordinates coordinates) throws IOException, InvalidCoordinatesException {
        try {
            WeatherStatus data = dataBase.getStatusCoord(coordinates);
            checkLastCall(data);
            return data;
        }catch(StatusNotFound ex){
            WeatherStatus status = server.getCurrentWeatherAtCoordinates(coordinates);
            dataBase.insertCoord(coordinates, status);
            return status;
        }
    }

    private void checkLastCall(WeatherStatus status) throws StatusNotFound {
        LocalDateTime now = LocalDateTime.now();


        int minutesNow = now.getHour()*60 + now.getMinute() + now.getSecond()/60;
        int minutes = status.getTime().getHour()*60 + status.getTime().getMinute() + status.getTime().getSecond()/60;

        if(minutesNow - minutes > 30 || now.getDayOfYear() != status.getTime().getDayOfYear())
            throw new StatusNotFound();

    }



}
