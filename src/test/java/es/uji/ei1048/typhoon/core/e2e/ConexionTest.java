package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.*;
import es.uji.ei1048.typhoon.core.exception.InvalidCoordinatesException;
import es.uji.ei1048.typhoon.core.exception.NoCityFoundException;
import es.uji.ei1048.typhoon.core.exception.StatusNotFoundException;
import es.uji.ei1048.typhoon.core.model.City;
import es.uji.ei1048.typhoon.core.model.Coordinates;
import es.uji.ei1048.typhoon.core.conexion.IDataBaseOp;
import es.uji.ei1048.typhoon.core.conexion.IServerConexion;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ConexionTest {
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

    @Test(expected = NoCityFoundException.class)
    public void CityUnknown() throws IOException, NoCityFoundException, StatusNotFoundException {
        City city = new City("x");
        when(dataBase.getStatusCity(city)).thenThrow(StatusNotFoundException.class);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(server.getCurrentWeatherAtCity(city)).thenThrow(NoCityFoundException.class);

        WeatherStatus result = re.getStatusCity(city);
        verify(server, never()).getCurrentWeatherAtCity(city);
        verify(dataBase, never()).insertCity(city, status);
        assertNull(result);


    }

    @Test(expected = InvalidCoordinatesException.class)
    public void CoordUnkown() throws StatusNotFoundException, IOException, InvalidCoordinatesException {
        Coordinates coord = new Coordinates(0.0, 0.0);
        when(dataBase.getStatusCoord(coord)).thenThrow(StatusNotFoundException.class);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(server.getCurrentWeatherAtCoordinates(coord)).thenThrow(InvalidCoordinatesException.class);

        WeatherStatus result = re.getStatusCoord(coord);
        verify(server, times(1)).getCurrentWeatherAtCoordinates(coord);
        verify(dataBase, times(1)).insertCoord(coord, status);
        assertNull(result);

    }

    @Test
    public void CityKnown() throws IOException, NoCityFoundException, StatusNotFoundException {
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
    public void CoordKnown() throws IOException, NoCityFoundException, StatusNotFoundException, InvalidCoordinatesException {
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        WeatherStatus status = new WeatherStatus("x", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, LocalDateTime.now());
        when(dataBase.getStatusCoord(coordinates)).thenReturn(status);

        WeatherStatus result = re.getStatusCoord(coordinates);
        verify(server, never()).getCurrentWeatherAtCoordinates(coordinates);
        verify(dataBase, never()).insertCoord(coordinates, status);
        verify(dataBase, times(1)).getStatusCoord(coordinates);
        assertEquals(result, status);


    }

}
