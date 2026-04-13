package clima;

import java.io.IOException;
import java.util.Objects;

import clima.models.ClimateData;
import clima.models.ClimateDataResponse;
import clima.models.Coord;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWeatherApp extends Application {
    public static void main(String[] args) throws IOException {launch();}

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/clima/ui/WeatherGUI.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

