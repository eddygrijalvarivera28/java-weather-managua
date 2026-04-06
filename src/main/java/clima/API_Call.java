package clima;

import clima.models.ClimateData;
import clima.models.ClimateDataResponse;
import clima.models.Coord;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.FXML;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API_Call {
    //Load the api key
    final private static String API_KEY = Dotenv.load().get("API_KEY");
    //Creating the mapper object
    static ObjectMapper mapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();
    //Creating the HTTP Client
    public static final HttpClient client = HttpClient.newHttpClient();

    //Current Weather API
    @FXML
    public static ClimateData getCurrentData(Coord coordinates) {
        //Establishing latitude and longitude of location
        final double lat = coordinates.lat;
        final double lon = coordinates.lon;
        //Creating current weather API URL
        StringBuilder current_Url = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?lat=")
                .append(lat)
                .append("&lon=")
                .append(lon)
                .append("&appid=")
                .append(API_KEY)
                .append("&units=metric");

        //Request
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(current_Url.toString()))
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(jsonString -> mapper.readValue(jsonString, ClimateData.class))
                    .get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        //Gets the coords and returns the Json

        public static ClimateDataResponse getData (Coord coordinates){
            //Establishing latitude and longitude of location
            final double lat = coordinates.lat;
            final double lon = coordinates.lon;

            //Creating the Forecast_API URL
            StringBuilder url = new StringBuilder("https://api.openweathermap.org/data/2.5/forecast?lat=")
                    .append(lat)
                    .append("&lon=")
                    .append(lon)
                    .append("&appid=")
                    .append(API_KEY)
                    .append("&units=metric");

            //Creating the request

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url.toString()))
                        .build();

                return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenApply(jsonString -> mapper.readValue(jsonString, ClimateDataResponse.class))
                        .get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //Gets the city and returns the coords
        public static Coord getCoords (String city){
            //Cleaning the city input
            city = city.trim().replace(" ", "+");


            //Creating the URL for the API
            StringBuilder url = new StringBuilder("https://api.openweathermap.org/geo/1.0/direct?q=");
            url.append(city).append("&limit=1").append("&appid=").append(API_KEY);

            try {
                //Request for the connection
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url.toString()))
                        .build();
                //Returns the coords object
                Coord[] coords = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenApply(jsonString -> mapper.readValue(jsonString, Coord[].class))
                        .get();
                return coords[0];
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;


        }
    }
