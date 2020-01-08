package es.uji.ei1048.typhoon.core;

import java.util.ArrayList;
import java.util.List;

public class FavouritePlaces {

    private List<Place> favourites;
    private IDataBaseOp database;

    public FavouritePlaces() {
        favourites = new ArrayList<>();
        database = new DatabaseOp();
    }

    public List<Place> getFavourites() {
        favourites.addAll(database.getFavouriteCity());
        return favourites;
    }

    public boolean addFavouriteCity(City city){
        favourites.add(city);
        //Anyadir a bbdd
        database.updateFavouriteCity(city);
        return favourites.contains(city);
    }

    public boolean addFavouriteCoordinates(Coordinates coordinates){
        favourites.add(coordinates);
        //Cambiar bbdd
        database.updateFavouriteCoord(coordinates);
        return favourites.contains(coordinates);
    }


    public Boolean deleteCity(City city) {
        favourites.remove(city);
        //Cambiar bbdd
        database.deleteFavouriteCity(city);
        return !favourites.contains(city);
    }

    public Boolean deleteCoord(Coordinates coord) {
        favourites.remove(coord);
        //Cambiar bbdd
        return !favourites.contains(coord);
    }
}