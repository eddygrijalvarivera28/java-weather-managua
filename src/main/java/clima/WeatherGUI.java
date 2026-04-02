package clima;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WeatherGUI extends Application {

    @Override
    public void start(Stage stage) {
        Button btn = new Button("Hello JavaFX");

        Scene scene = new Scene(btn, 300, 200);
        stage.setTitle("My First GUI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
class GUIStarter {
    public static void main(final String[] args) {
        WeatherGUI.main(args);}}
