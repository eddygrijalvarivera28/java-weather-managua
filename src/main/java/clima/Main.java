package clima;

import java.io.IOException;
import clima.models.coord;

public class Main {
    public static void main(String[] args) throws IOException {
        //Inputs the city to get the coordinates API
        coord coord = API_Call.getCoords("Managua");
        if (coord == null){
            throw new IOException("Error parsing JSON FIle");
        }
        System.out.println(coord.getLat() + " " + coord.getLon());
    }
}
