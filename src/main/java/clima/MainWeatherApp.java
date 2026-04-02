package clima;

import java.io.IOException;

import clima.models.ClimateData;
import clima.models.ClimateDataResponse;
import clima.models.Coord;

public class MainWeatherApp {
    public static void main(String[] args) throws IOException {
        //Inputs the city to get the coordinates API
        Coord coord = API_Call.getCoords("Colombia");
        if (coord == null){
            throw new IOException("Error parsing JSON FIle");
        }
        System.out.println(coord.lat + " " + coord.lon);

        ClimateDataResponse test = API_Call.getData(coord);

        for (ClimateData data : test.list) {
            int i = 0;
            java.util.Date time = new java.util.Date((long)data.dt*1000);
            System.out.println(data.main.temp + " " + time);
            i++;
        }
    }
}
