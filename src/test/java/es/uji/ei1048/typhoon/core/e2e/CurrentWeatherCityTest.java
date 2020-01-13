package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.model.City;
import es.uji.ei1048.typhoon.core.exception.NoCityFoundException;
import es.uji.ei1048.typhoon.core.TyphoonFacade;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;

public class CurrentWeatherCityTest {

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
    public void currentWeatherCity_valid() throws NoCityFoundException, IOException {
        //Given
        City city = new City("Valencia");
        //When
        WeatherStatus status = sut.currentWeatherCity(city);
        //Then
        assertNotEquals(ws, status);
    }

    @Test(expected = NoCityFoundException.class)
    public void currentWeatherCity_invalid() throws NoCityFoundException, IOException {
        //Given
        City city = new City("Minas Tirith");
        //When
        WeatherStatus status = sut.currentWeatherCity(city);
        //Then Salta NoCityFoundException
    }

}