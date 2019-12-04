package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.weather.WeatherStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class ViewController {

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
    private TyphoonFacade typhoonFacade = new TyphoonFacade();

    public ViewController() {
    }

    @FXML
    private void sendCurrentWeatherStatusCity(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException{
        WeatherStatus ws;
        try{
            ws = typhoonFacade.currentWeatherCity(new City(nameCity.getText()));
            weatherResultCity.setText(ws.toString());
        }catch (NoCityFoundException e){
            weatherResultCity.setText("ERROR: Name city incorrect");
        }


    }

    @FXML
    private void sendCurrentWeatherStatusCoord(ActionEvent event) throws MalformedURLException{
        WeatherStatus ws;
        try{
            ws = typhoonFacade.currentWeatherCoordinates(new Coordinates(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText())));
            weatherStatusCoord.setText(ws.toString());
        }catch (InvalidCoordinatesException e){
            weatherStatusCoord.setText("ERROR: Invalid coordinates");
        }


    }

    public void setMain(Main main){
        this.main = main;
    }
}
