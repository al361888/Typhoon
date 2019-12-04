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

    private Main main;

    public Controller() {
    }

    @FXML
    private void enviar(ActionEvent event) throws UnsupportedEncodingException, NoCityFoundException, MalformedURLException {
        TyphoonFacade typhoonFacade = new TyphoonFacade();
        WeatherStatus ws = typhoonFacade.currentWeatherCity(new City(nameCity.getText()));
        weatherResultCity.setText(ws.toString());

    }

    public void setMain(Main main){
        this.main = main;
    }
}
