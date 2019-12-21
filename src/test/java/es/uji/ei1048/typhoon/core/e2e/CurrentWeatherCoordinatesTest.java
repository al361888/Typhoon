package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.core.InvalidCoordinatesException;
import es.uji.ei1048.typhoon.core.TyphoonFacade;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.charset.CoderResult;

import static org.junit.Assert.*;

public class CurrentWeatherCoordinatesTest {

    private TyphoonFacade sut;
    private WeatherStatus ws;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        sut = new TyphoonFacade();
        ws = new WeatherStatus();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        ws = null;
    }

    @Test
    public void currentWeatherCoordinates_valid() throws InvalidCoordinatesException, IOException {
        //Given
        Coordinates coord= new Coordinates(10, -10);
        //When
        WeatherStatus status = sut.currentWeatherCoordinates(coord);
        //Then
        assertNotEquals(ws, status);
    }

    @Test(expected = InvalidCoordinatesException.class)
    public void currentWeatherCoordinates_invalid() throws InvalidCoordinatesException, IOException {
        //Given
        Coordinates coord= new Coordinates(10000, -10000);
        //When
        WeatherStatus status = sut.currentWeatherCoordinates(coord);
        //Then
    }
}