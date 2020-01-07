package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.*;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.*;

public class TimeConexionTest {

    private IServerConexion server;
    private IDataBaseOp dataBase;
    private RestrictionFunction re;


    @Before
    public void setUp() throws Exception {
        server = mock(IServerConexion.class);
        dataBase = mock(IDataBaseOp.class);
        re = new RestrictionFunction(server, dataBase);
    }

    @After
    public void tearDown() throws Exception {
        server = null;
        dataBase = null;
        re = null;

    }

    @Test
    public void CityUnknown() throws IOException, NoCityFoundException, StatusNotFound {
        City city = new City("x");
        when(dataBase.getStatusCity(city)).thenThrow(StatusNotFound.class);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(server.getCurrentWeatherAtCity(city)).thenReturn(status);

        WeatherStatus result = re.getStatusCity(city);
        verify(server, times(1)).getCurrentWeatherAtCity(city);
        verify(dataBase, times(1)).insertCity(city, status);
        assertEquals(result, status);

    }

    @Test
    public void CoordUnkown() throws StatusNotFound, IOException, InvalidCoordinatesException {
       Coordinates coord = new Coordinates(0.0, 0.0);
        when(dataBase.getStatusCoord(coord)).thenThrow(StatusNotFound.class);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(server.getCurrentWeatherAtCoordinates(coord)).thenReturn(status);

        WeatherStatus result = re.getStatusCoord(coord);
        verify(server, times(1)).getCurrentWeatherAtCoordinates(coord);
        verify(dataBase, times(1)).insertCoord(coord, status);
        assertEquals(result, status);

    }

    @Test
    public void CityKnownCallDataBase() throws IOException, NoCityFoundException, StatusNotFound {
        City city = new City("x");
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(dataBase.getStatusCity(city)).thenReturn(status);

        WeatherStatus result = re.getStatusCity(city);
        verify(server, never()).getCurrentWeatherAtCity(city);
        verify(dataBase, never()).insertCity(city, status);
        verify(dataBase, times(1)).getStatusCity(city);
        assertEquals(result, status);



    }

    @Test
    public void CoordKnownCallDataBase() throws IOException, NoCityFoundException, StatusNotFound, InvalidCoordinatesException {
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(dataBase.getStatusCoord(coordinates)).thenReturn(status);

        WeatherStatus result = re.getStatusCoord(coordinates);
        verify(server, never()).getCurrentWeatherAtCoordinates(coordinates);
        verify(dataBase, never()).insertCoord(coordinates, status);
        verify(dataBase, times(1)).getStatusCoord(coordinates);
        assertEquals(result, status);


    }



    @Test
    public void CityKnownCallServer() throws IOException, NoCityFoundException, StatusNotFound {
        City city = new City("x");
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(dataBase.getStatusCity(city)).thenReturn(new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now().minusHours(2)));
        when(server.getCurrentWeatherAtCity(city)).thenReturn(status);

        WeatherStatus result = re.getStatusCity(city);
        verify(server, times(1)).getCurrentWeatherAtCity(city);
        verify(dataBase, times(1)).insertCity(city, status);
        verify(dataBase, times(1)).getStatusCity(city);
        assertEquals(result, status);


    }

    @Test
    public void CoordKownCallServer() throws IOException, InvalidCoordinatesException, StatusNotFound {
        Coordinates coord = new Coordinates(0.0, 0.0);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(dataBase.getStatusCoord(coord)).thenReturn(new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now().minusHours(2)));
        when(server.getCurrentWeatherAtCoordinates(coord)).thenReturn(status);

        WeatherStatus result = re.getStatusCoord(coord);
        verify(server, times(1)).getCurrentWeatherAtCoordinates(coord);
        verify(dataBase, times(1)).insertCoord(coord, status);
        verify(dataBase, times(1)).getStatusCoord(coord);
        assertEquals(result, status);
    }



}
