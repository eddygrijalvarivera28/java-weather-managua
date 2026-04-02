package clima;

import java.io.IOException;
import clima.models.Coord;

public class MainWeatherApp {
    public static void main(String[] args) throws IOException {
        //Inputs the city to get the coordinates API
        Coord coord = API_Call.getCoords("Colombia");
        if (coord == null){
            throw new IOException("Error parsing JSON FIle");
        }
        System.out.println(coord.getLat() + " " + coord.getLon());
    }
}
