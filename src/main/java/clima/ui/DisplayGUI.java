package clima.ui;

import clima.models.ClimateData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DisplayGUI {
    //link
    public final String iconLink = "https://openweathermap.org/img/wn/";

    @FXML
    public Text texto_0_0;
    public Text texto_0_1;
    public ImageView imagen_0_2;
    public Text texto_1_0;
    public Text texto_1_1;
    public Text texto_1_2;

    @FXML public Text cityText;
    @FXML public Button buttonBack;

    public void updateData(ClimateData response, String city){
    texto_0_0.setText(Double.toString(response.main.temp));
    texto_0_1.setText(new java.util.Date(response.dt*1000).toString());
    texto_1_0.setText(response.weather.getFirst().main);
    texto_1_1.setText(Double.toString(response.wind.speed));
    texto_1_2.setText(response.main.temp_min + "°");
    imagen_0_2.setImage(new Image(iconLink + response.weather.getFirst().icon + "@2x.png",true));
    cityText.setText(city.substring(0,1).toUpperCase() + city.substring(1));
    }
@FXML
    public void PressButtonBack(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/clima/ui/WeatherGUI.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
