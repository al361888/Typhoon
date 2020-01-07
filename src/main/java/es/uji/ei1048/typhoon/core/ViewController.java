package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
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

    @FXML
    private void sendCurrentWeatherStatusCity(ActionEvent event) throws IOException {
        WeatherStatus ws = new WeatherStatus();
        try {
            ws = typhoonFacade.currentWeatherCity(new City(nameCity.getText()));
        } catch (NoCityFoundException e) {
            weatherResultCity.setText("ERROR: City not found");
        }
        weatherResultCity.setText(ws.toString());
        day1City.setText("");
        day2City.setText("");
        day3City.setText("");


    }

    @FXML
    private void sendCurrentWeatherStatusCoord(ActionEvent event) throws IOException {
        WeatherStatus ws = new WeatherStatus();
        try {
            ws = typhoonFacade.currentWeatherCoordinates(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        } catch (InvalidCoordinatesException e) {
            weatherStatusCoord.setText("ERROR: Invalid coordinates");
        }
        weatherStatusCoord.setText(ws.toString());
        day1Coord.setText("");
        day2Coord.setText("");
        day3Coord.setText("");


    }

    @FXML
    private void sendForecastWeatherStatusCity(ActionEvent event) throws IOException{
        List<WeatherStatus> ws = new ArrayList<>();
        try {
            ws = typhoonFacade.forecastWeatherCity(new City(nameCity.getText()));
        } catch (NoCityFoundException e) {
            weatherResultCity.setText("ERROR: City not found");
        }
        day1City.setText("Day 1: \n"+ws.get(1).toString());
        day2City.setText("Day 2: \n"+ws.get(2).toString());
        day3City.setText("Day 3: \n"+ws.get(3).toString());
        weatherResultCity.setText("");


    }

    @FXML
    private void sendForecastWeatherStatusCoord(ActionEvent event) throws IOException {
        List<WeatherStatus> ws = new ArrayList<>();
        try {
            ws = typhoonFacade.forecastWeatherCoord(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        } catch (InvalidCoordinatesException e) {
            weatherStatusCoord.setText("ERROR: Invalid coordinates");
        }
        day1Coord.setText("Day 1: \n"+ws.get(1).toString());
        day2Coord.setText("Day 2: \n"+ws.get(2).toString());
        day3Coord.setText("Day 3: \n"+ws.get(3).toString());
        weatherStatusCoord.setText("");
    }

    public void setMain(Main main){
        this.main = main;
    }
}
