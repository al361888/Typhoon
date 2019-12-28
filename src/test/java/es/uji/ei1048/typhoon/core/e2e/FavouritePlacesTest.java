package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.*;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FavouritePlacesTest {

    private TyphoonFacade sut;
    private FavouritePlaces favs;

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        sut = new TyphoonFacade();
        favs = new FavouritePlaces();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
        favs = null;
    }

    @Test
    public void addFavouriteCity() throws NoCityFoundException, IOException {
        //Given
        City city = new City("Valencia");
        //When
        Boolean added = sut.addFavouriteCity(city);
        //Then
        assertEquals(true, added);
    }

    @Test
    public void addFavouriteCoordinates() throws IOException, InvalidCoordinatesException {
        //Given
        Coordinates coord = new Coordinates(10, -10);
        //When
        Boolean added = sut.addFavouriteCoordinates(coord);
        //Then
        assertEquals(true, added);
    }
}
