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
    private TextField lat;

    @FXML
    private TextField lon;

    @FXML
    private Button coordCurrentButton;

    @FXML
    private Button coordForecastButton;

    @FXML
    private Label weatherStatusCoord;


    private Main main;
    private TyphoonFacade typhoonFacade = new TyphoonFacade();

    public ViewController() {
    }

    @FXML
    private void sendCurrentWeatherStatusCity(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, FileNotFoundException, NoCityFoundException {
        WeatherStatus ws;
        ws = typhoonFacade.currentWeatherCity(new City(nameCity.getText()));
        weatherResultCity.setText(ws.toString());


    }

    @FXML
    private void sendCurrentWeatherStatusCoord(ActionEvent event) throws MalformedURLException, InvalidCoordinatesException {
        WeatherStatus ws;
        ws = typhoonFacade.currentWeatherCoordinates(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        weatherStatusCoord.setText(ws.toString());


    }

    @FXML
    private void sendForecastWeatherStatusCity(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, FileNotFoundException, NoCityFoundException {
        List<WeatherStatus> ws;
        ws = typhoonFacade.forecastWeatherCity(new City(nameCity.getText()));
        for(int i=0; i<ws.size();i++)
            weatherResultCity.setText(ws.toString());


    }

    @FXML
    private void sendForecastWeatherStatusCoord(ActionEvent event) throws MalformedURLException, InvalidCoordinatesException, UnsupportedEncodingException {
        List<WeatherStatus> ws;
        ws = typhoonFacade.forecastWeatherCoord(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        for(int i=0; i<ws.size();i++)
            weatherResultCity.setText(ws.toString());



    }

    public void setMain(Main main){
        this.main = main;
    }
}
