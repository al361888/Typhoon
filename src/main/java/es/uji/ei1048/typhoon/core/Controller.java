package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class Controller {

    @FXML
    private TextField nameCity;

    @FXML
    private Button cityButton;

    @FXML
    private Label weatherResultCity;

    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    @FXML
    private Button coordButton;

    @FXML
    private Label weatherStatusCoord;


    private Main main;

    public Controller() {
    }

    @FXML
    private void sendCurrentWeatherStatusCity(ActionEvent event) throws UnsupportedEncodingException, NoCityFoundException, MalformedURLException {
        TyphoonFacade typhoonFacade = new TyphoonFacade();
        WeatherStatus ws = typhoonFacade.currentWeatherCity(new City(nameCity.getText()));
        weatherResultCity.setText(ws.toString());

    }

    @FXML
    private void sendCurrentWeatherStatusCoord(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, InvalidCoordinatesException {
        TyphoonFacade typhoonFacade = new TyphoonFacade();
        WeatherStatus ws = typhoonFacade.currentWeatherCoordinates(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
        weatherStatusCoord.setText(ws.toString());

    }

    public void setMain(Main main){
        this.main = main;
    }
}
