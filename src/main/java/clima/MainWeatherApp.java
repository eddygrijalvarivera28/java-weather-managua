package clima;

import java.io.IOException;

import clima.models.ClimateData;
import clima.models.ClimateDataResponse;
import clima.models.Coord;

public class MainWeatherApp {
    public static void main(String[] args) throws IOException {
        //Inputs the city to get the coordinates API
        Coord coord = API_Call.getCoords("Managua");
        if (coord == null){
            throw new IOException("Error parsing JSON FIle");
        }
        System.out.println(coord.lat + " " + coord.lon);

        ClimateData single_test = API_Call.getCurrentData(coord);
        ClimateDataResponse test = API_Call.getData(coord);

        System.out.println("Current");
        System.out.println(single_test.main.temp + "°  " + new java.util.Date(single_test.dt *1000));
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        for (ClimateData data : test.list) {
            java.util.Date time = new java.util.Date(data.dt *1000);
            System.out.println(data.main);
            System.out.println(data.main.temp + "°  " + time);
            System.out.println("=========================");
        }
    }
}
