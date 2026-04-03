package clima.ui;

import clima.API_Call;
import clima.models.ClimateData;
import clima.models.ClimateDataResponse;
import clima.models.Coord;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WeatherGUI extends Application{
    @FXML
    public TextField texto;
    @FXML
    public Button boton_Buscar;
    @FXML
    public Text cajaDeTexto;

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/clima/ui/WeatherGUI.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Weather Application");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            API_Call.client.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
    @FXML
    public void getCoords(ActionEvent event) {
        Coord coords = API_Call.getCoords(texto.getText());
        if (coords != null) {
            System.out.println("Exito!");
            System.out.println(coords.lat + " " + coords.lon);
            ClimateData response = API_Call.getCurrentData(coords);
            cajaDeTexto.setText(Double.toString(response.main.temp));
        }
    }


}
//class GUIStarter {
//    public static void main(final String[] args) {
//        WeatherGUI.main(args);}}
