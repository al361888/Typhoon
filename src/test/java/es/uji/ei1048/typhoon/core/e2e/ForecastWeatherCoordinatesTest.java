package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.*;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class ForecastWeatherCoordinatesTest {
    private TyphoonFacade sut;
    private List<WeatherStatus> ws;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        sut = new TyphoonFacade();
        ws = new ArrayList<WeatherStatus>();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        ws = null;
    }

    @Test
    public void forecastWeatherCoord_valid() throws UnsupportedEncodingException, MalformedURLException, InvalidCoordinatesException {
        //Given
        Coordinates coord = new Coordinates(10, -10);
        //When
        List<WeatherStatus> status = sut.forecastWeatherCoord(coord);
        //Then
        assertNotEquals(ws, status);
    }

    @Test(expected = NullPointerException.class)
    public void forecastWeatherCoord_invalid() throws UnsupportedEncodingException, MalformedURLException, InvalidCoordinatesException {
        //Given
        Coordinates coord = new Coordinates(100000, -1000000);
        //When
        List<WeatherStatus> status = sut.forecastWeatherCoord(coord);
        //Then Salta NoCityFoundException
    }
}
