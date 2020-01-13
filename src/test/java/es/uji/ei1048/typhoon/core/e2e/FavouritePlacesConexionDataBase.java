package es.uji.ei1048.typhoon.core.e2e;

import es.uji.ei1048.typhoon.core.conexion.IDataBaseOp;
import es.uji.ei1048.typhoon.core.model.City;
import es.uji.ei1048.typhoon.core.model.Coordinates;
import es.uji.ei1048.typhoon.core.model.FavouritePlaces;
import es.uji.ei1048.typhoon.core.model.Place;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class FavouritePlacesConexionDataBase {

    private IDataBaseOp dataBase;
    private FavouritePlaces favouritePlaces;

    @Before
    public void setUp() throws Exception {
        dataBase = mock(IDataBaseOp.class);
        favouritePlaces = new FavouritePlaces(dataBase);
    }

    @After
    public void tearDown() throws Exception {
        dataBase = null;

    }

    @Test
    public void addCity(){
        City city = new City("x");
        favouritePlaces.addFavouriteCity(city);
        verify(dataBase, times(1)).updateFavouriteCity(city);

        List<Place> places = favouritePlaces.getFavourites();
        assertTrue(places.contains(city));
    }

    @Test
    public void addCoord(){
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        favouritePlaces.addFavouriteCoordinates(coordinates);
        verify(dataBase, times(1)).updateFavouriteCoord(coordinates);

        List<Place> places = favouritePlaces.getFavourites();
        assertTrue(places.contains(coordinates));

    }

    @Test
    public void deleteCity(){
        City city = new City("x");
        favouritePlaces.deleteCity(city);
        verify(dataBase, times(1)).deleteFavouriteCity(city);

        List<Place> places = favouritePlaces.getFavourites();
        assertFalse(places.contains(city));

    }

    @Test
    public void deleteCoord(){
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        favouritePlaces.deleteCoord(coordinates);
        verify(dataBase, times(1)).deleteFavouriteCoord(coordinates);

        List<Place> places = favouritePlaces.getFavourites();
        assertFalse(places.contains(coordinates));
    }




}
