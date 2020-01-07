package es.uji.ei1048.typhoon.core;

import java.util.ArrayList;
import java.util.List;

public class FavouritePlaces {

    private List<Place> favourites;

    public FavouritePlaces() {
        favourites = new ArrayList<>();
    }

    public List<Place> getFavourites() {
        return favourites;
    }

    public boolean addFavouriteCity(City city){
        favourites.add(city);
        //Anyadir a bbdd
        return favourites.contains(city);
    }

    public boolean addFavouriteCoordinates(Coordinates coordinates){
        favourites.add(coordinates);
        //Cambiar bbdd
        return favourites.contains(coordinates);
    }


    public Boolean deleteCity(City city) {
        favourites.remove(city);
        //Cambiar bbdd
        return !favourites.contains(city);
    }

    public Boolean deleteCoord(Coordinates coord) {
        favourites.remove(coord);
        //Cambiar bbdd
        return !favourites.contains(coord);
    }
}