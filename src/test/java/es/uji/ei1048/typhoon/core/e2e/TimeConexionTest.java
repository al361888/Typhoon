package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.*;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import static org.mockito.Mockito.*;

public class TimeConexionTest {

    private IServerConexion server;
    private IDataBaseConexion dataBase;


    @Before
    public void setUp() throws Exception {
        server = mock(IServerConexion.class);
        dataBase = mock(IDataBaseConexion.class);
    }

    @After
    public void tearDown() throws Exception {
        server = null;

    }

    @Test
    public void CityUnknownCallServer() throws IOException, NoCityFoundException {


    }

    @Test
    public void CoordUnkownCallServer(){

    }

    @Test
    public void CityKnownCallServer() throws IOException, NoCityFoundException {
        LocalTime date = LocalTime.now();
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        status.setTime(date);
        when(dataBase.getCityStatus("x")).thenReturn(new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalTime.now().plusHours(2)));
        when(server.getCurrentWeatherAtCity(new City("x"))).thenReturn(status);
    }

    @Test
    public void CoordKownCallServer(){

    }



}
