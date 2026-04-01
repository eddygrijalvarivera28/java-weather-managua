package clima;

import clima.models.coord;
import io.github.cdimascio.dotenv.Dotenv;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API_Call  {
    //Load the api key
    final private static String API_KEY = Dotenv.load().get("API_KEY");
    //Creating the mapper object
       static ObjectMapper mapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();
    //Creating the HTTP Client
    public static final HttpClient client = HttpClient.newHttpClient();

    //Gets the coords and returns the Json
    public static String getJson(clima.models.coord coordinates){

        return null;
    }

    //Gets the city and returns the coords
    public static clima.models.coord getCoords(String city){
        //Cleaning the city input
        city = city.trim().replace(" ", "+");


        //Creating the URL for the API
        StringBuilder url =new StringBuilder("https://api.openweathermap.org/geo/1.0/direct?q=");
        url.append(city).append("&limit=1").append("&appid=").append(API_KEY);

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .build();

            coord[] coords = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenApply(jsonString -> mapper.readValue(jsonString, coord[].class))
                        .get();
            return coords[0];
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;


    }
}
