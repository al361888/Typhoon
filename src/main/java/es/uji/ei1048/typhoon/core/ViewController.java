package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

public class ViewController {

    @FXML
    private TextField nameCity;

    @FXML
    private Button cityCurrentButton;

    @FXML
    private Button cityForecastButton;

    @FXML
    private Label weatherResultCity;

    @FXML
    private Label day1City;
    @FXML
    private Label day2City;
    @FXML
    private Label day3City;


    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    @FXML
    private Button coordCurrentButton;

    @FXML
    private Button coordForecastButton;

    @FXML
    private Label weatherStatusCoord;

    @FXML
    private Label day1Coord;
    @FXML
    private Label day2Coord;
    @FXML
    private Label day3Coord;


    private Main main;
    private TyphoonFacade typhoonFacade = new TyphoonFacade();

    public ViewController() {
    }

    @FXML
    private void sendCurrentWeatherStatusCity(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, FileNotFoundException, NoCityFoundException {
        WeatherStatus ws;
        ws = typhoonFacade.currentWeatherCity(new City(nameCity.getText()));
        weatherResultCity.setText(ws.toString());
        day1City.setText("");
        day2City.setText("");
        day3City.setText("");


    }

    @FXML
    private void sendCurrentWeatherStatusCoord(ActionEvent event) throws MalformedURLException, InvalidCoordinatesException {
        WeatherStatus ws;
        ws = typhoonFacade.currentWeatherCoordinates(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        weatherStatusCoord.setText(ws.toString());
        day1Coord.setText("");
        day2Coord.setText("");
        day3Coord.setText("");


    }

    @FXML
    private void sendForecastWeatherStatusCity(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, FileNotFoundException, NoCityFoundException {
        List<WeatherStatus> ws;
        ws = typhoonFacade.forecastWeatherCity(new City(nameCity.getText()));
        day1City.setText("Day 1: \n"+ws.get(1).toString());
        day2City.setText("Day 2: \n"+ws.get(2).toString());
        day3City.setText("Day 3: \n"+ws.get(3).toString());
        weatherResultCity.setText("");


    }

    @FXML
    private void sendForecastWeatherStatusCoord(ActionEvent event) throws MalformedURLException, InvalidCoordinatesException, UnsupportedEncodingException {
        List<WeatherStatus> ws;
        ws = typhoonFacade.forecastWeatherCoord(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        day1Coord.setText("Day 1: \n"+ws.get(1).toString());
        day2Coord.setText("Day 2: \n"+ws.get(2).toString());
        day3Coord.setText("Day 3: \n"+ws.get(3).toString());
        weatherStatusCoord.setText("");


    }

    public void setMain(Main main){
        this.main = main;
    }
}
