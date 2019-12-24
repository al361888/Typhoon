package es.uji.ei1048.typhoon.core;

import java.util.List;

public class FavouritePlaces {

    private List<City> favoritesCity;
    private List<Coordinates> favoritesCoordinates;

    public FavouritePlaces() {
    }

    public FavouritePlaces(List<City> favoritesCity, List<Coordinates> favoritesCoordinates) {
        this.favoritesCity = favoritesCity;
        this.favoritesCoordinates = favoritesCoordinates;
    }

    public List<City> getFavoritesCity() {
        return favoritesCity;
    }

    public void setFavoritesCity(List<City> favoritesCity) {
        this.favoritesCity = favoritesCity;
    }

    public List<Coordinates> getFavoritesCoordinates() {
        return favoritesCoordinates;
    }

    public void setFavoritesCoordinates(List<Coordinates> favoritesCoordinates) {
        this.favoritesCoordinates = favoritesCoordinates;
    }
}
