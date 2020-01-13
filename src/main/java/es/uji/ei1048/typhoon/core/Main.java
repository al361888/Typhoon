package es.uji.ei1048.typhoon.core;

import es.uji.ei1048.typhoon.core.model.Place;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private TyphoonFacade typhoonFacade = new TyphoonFacade();
    private ObservableList<String> places = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Typhoon");
        this.primaryStage.getIcons().add(new Image("view/images/logo.jfif"));

        initFavourites();

        initRootLayout();

        showCurrentWeather();


    }

    private void initFavourites() {
        List<String> cities = new ArrayList<>();
        List<Place> placesFavourites = typhoonFacade.getFavouritePlaces();
        for (Place placesFavourite : placesFavourites) cities.add(placesFavourite.getName());

        places.addAll(cities);
    }

    ObservableList<String> getPlaces(){
        return places;
    }

    /**
     * Initializes the root layout.
     */

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the weather overview inside the root layout.
     */
    public void showCurrentWeather() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ForecastWeather.fxml"));
            AnchorPane currentWeatherOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(currentWeatherOverview);

            ViewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
