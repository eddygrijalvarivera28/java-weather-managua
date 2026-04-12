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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    public Text text0_cero_uno;
    public Text texto_uno_cero;
    public Text texto_uno_uno;
    public Text texto_cero_dos;
    public Text texto_uno_dos;

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/clima/ui/WeatherGUI.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Weather Application");

//            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/clima/ui/Parque-Central.jpg")));
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/clima/ui/images.png")));
//            ImageView view = new ImageView(image);
//            ((Pane) root).getChildren().add(view);
//            view.toBack();

            stage.getIcons().add(image2);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            System.out.println(coords.lat + " " + coords.lon + " " + coords.country);
            ClimateData response = API_Call.getCurrentData(coords);
            cajaDeTexto.setText(Double.toString(response.main.temp));
            text0_cero_uno.setText(new java.util.Date(response.dt*1000).toString());
            texto_uno_cero.setText(response.weather.getFirst().main);
            texto_uno_uno.setText(Double.toString(response.wind.speed));
            texto_uno_dos.setText(response.main.temp_min + "°");
            texto_cero_dos.setText(response.main.temp_max + "°");

        }
    }


}
//class GUIStarter {
//    public static void main(final String[] args) {
//        WeatherGUI.main(args);}}
