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

import static org.junit.Assert.*;

public class CurrentWeatherCoordinatesTest {

    private TyphoonFacade sut;
    private WeatherStatus ws;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp(){
        sut = new TyphoonFacade();
        ws = new WeatherStatus();
    }

    @After
    public void tearDown(){
        sut = null;
        ws = null;
    }

    @Test
    public void currentWeatherCoordinates_valid() throws InvalidCoordinatesException {
        //Given
        Coordinates coordinates = new Coordinates(39.98, -0.03);
        //When
        WeatherStatus status = sut.currentWeatherCoordinates(coordinates);
        //Then
        assertNotEquals(ws, status);
    }

    @Test
    public void currentWeatherCoordinates_invalid() throws InvalidCoordinatesException {
        //Given
        Coordinates coordinates = new Coordinates(-5339.98, -9841.03);
        //When
        WeatherStatus status = sut.currentWeatherCoordinates(coordinates);
        //Then
        ex.expect(InvalidCoordinatesException.class);
    }
}