package clima.ui;

import clima.API.API_Call;
import clima.models.ClimateData;
import clima.models.Coord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchGUI {
    //SCENE 1
    private Scene scene1;
    @FXML public TextField caja_Buscar;
    @FXML public Button boton_Buscar;
    @FXML public Text error_text;


@FXML
public void getCoords(ActionEvent event){

        Thread thread = new Thread(() -> {
            try {
                Coord coords = API_Call.getCoords(caja_Buscar.getText());
                if (coords==null){
                    error_text.setText("Please write a valid city");
                }
                if (coords != null) {
                    System.out.println("Exito!");
                    System.out.println(coords.lat + " " + coords.lon + " " + coords.country);
                    ClimateData response = API_Call.getCurrentData(coords);

                    if (response != null) {
                        javafx.application.Platform.runLater(() -> {
                            System.out.println("Exito");
                            try {
                                setScene2(event, response, caja_Buscar.getText());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }




@FXML
private void setScene2(ActionEvent event, ClimateData response, String city) throws IOException {
    FXMLLoader loader = new FXMLLoader((getClass().getResource("/clima/ui/DisplayData.fxml")));
    Parent root = loader.load();

    DisplayGUI displayGUI = loader.getController();
    displayGUI.updateData(response,city);

    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
    }
}
