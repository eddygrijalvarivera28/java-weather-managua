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

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class DisplayGUI {
    //link
    public final String iconLink = "https://openweathermap.org/img/wn/";

    @FXML public Text text_main_temperature;
    @FXML public Text text_weather_description;
    @FXML public ImageView imagen_0_2;
    @FXML public Text text_pop;
    @FXML public Text text_time;

    @FXML public Text cityText;
    @FXML public Button buttonBack;

    @FXML public ImageView image_day1;
    @FXML public ImageView image_day2;
    @FXML public ImageView image_day3;
    @FXML public ImageView image_day4;
    @FXML public ImageView image_day5;
    @FXML public Text date_day1;
    @FXML public Text date_day2;
    @FXML public Text date_day3;
    @FXML public Text date_day4;
    @FXML public Text date_day5;
    @FXML public Text temperature_day1;
    @FXML public Text temperature_day2;
    @FXML public Text temperature_day3;
    @FXML public Text temperature_day4;
    @FXML public Text temperature_day5;

    /// //////////////////////////////////
    public void updateData(ClimateData response, String city, List<ClimateData> list){
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
        //PRECIPITATION
        String precipitation = Integer.toString(response.pop) + "%";
        text_pop.setText(precipitation);
        // /////////////////////////////////////////////////////////
        // FUTURE DAYS
        // ////////////////////////////////////////////////////////
        // ICONS
        image_day1.setImage(new Image(iconLink + list.getFirst().weather.getFirst().icon + "@2x.png",true));
        image_day2.setImage(new Image(iconLink + list.get(1).weather.getFirst().icon + "@2x.png",true));
        image_day3.setImage(new Image(iconLink + list.get(2).weather.getFirst().icon + "@2x.png",true));
        image_day4.setImage(new Image(iconLink + list.get(3).weather.getFirst().icon + "@2x.png",true));
        image_day5.setImage(new Image(iconLink + list.get(4).weather.getFirst().icon + "@2x.png",true));
        // DATE
        date_day1.setText(new java.util.Date(list.getFirst().dt*1000).toString().substring(0,10));
        date_day2.setText(new java.util.Date(list.get(1).dt*1000).toString().substring(0,10));
        date_day3.setText(new java.util.Date(list.get(2).dt*1000).toString().substring(0,10));
        date_day4.setText(new java.util.Date(list.get(3).dt*1000).toString().substring(0,10));
        date_day5.setText(new java.util.Date(list.get(4).dt*1000).toString().substring(0,10));
        //TEMPERATURE
        temperature_day1.setText(Double.toString(list.getFirst().main.temp) + "°");
        temperature_day2.setText(Double.toString(list.get(1).main.temp) + "°");
        temperature_day3.setText(Double.toString(list.get(2).main.temp) + "°");
        temperature_day4.setText(Double.toString(list.get(3).main.temp) + "°");
        temperature_day5.setText(Double.toString(list.get(4).main.temp) + "°");
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

