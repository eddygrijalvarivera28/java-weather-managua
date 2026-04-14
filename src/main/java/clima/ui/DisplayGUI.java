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

import java.util.Objects;
import java.util.regex.Pattern;

public class DisplayGUI {
    //link
    public final String iconLink = "https://openweathermap.org/img/wn/";

    @FXML public Text text_main_temperature;
    @FXML public Text text_weather_description;
    @FXML public ImageView imagen_0_2;
    @FXML public Text texto_1_0;
    @FXML public Text texto_1_1;
    @FXML public Text text_time;

    @FXML public Text cityText;
    @FXML public Button buttonBack;

    /// //////////////////////////////////
    public void updateData(ClimateData response, String city){
        //WEATHER DESCRIPTION
        String weather_description = capitalizeAll(response.weather.getFirst().description.toLowerCase());
        text_weather_description.setText(weather_description);
        //CURRENT TIME
        text_time.setText(new java.util.Date(response.dt*1000).toString());
        //WEATHER ICON
        imagen_0_2.setImage(new Image(iconLink + response.weather.getFirst().icon + "@2x.png",true));
        //CITY NAME
        city = city.toLowerCase().trim();
        cityText.setText(capitalizeAll(city.substring(0,1).toUpperCase() + city.substring(1)));
        //MAIN TEMPERATURE
        String temperature = Double.toString(response.main.temp);
        text_main_temperature.setText(temperature  + "°");
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

//TO CAPITALIZE
public static String capitalizeAll(String str) {
    if (str == null || str.isEmpty()) {
        return str;
    }

    return Pattern.compile("\\b(.)(.*?)\\b")
            .matcher(str)
            .replaceAll(match -> match.group(1).toUpperCase() + match.group(2));
}
}

