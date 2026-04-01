import io.github.cdimascio.dotenv.Dotenv;
import tools.jackson.databind.ObjectMapper;

static Dotenv dotenv = Dotenv.load();

static final String API_KEY = dotenv.get("API_KEY");
static final String BASE_URL_CITY_API = "https://api.openweathermap.org/geo/1.0/direct?q=";

public static void main(String[] args) {
    StringBuilder urlApi = new StringBuilder();
    ObjectMapper mapper = new ObjectMapper();
    String city = "New+York";
    urlApi.append(BASE_URL_CITY_API).append(city).append("&limit=1").append("&appid=").append(API_KEY);
    System.out.println(urlApi);
    try {
        URL url = new URI(urlApi.toString()).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        System.out.println(conn.getResponseCode());

        StringBuilder Json = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            Json.append(line);
        }
        Object mapped = mapper.readValue(Json.toString(), Object.class);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapped));
    } catch (Exception e) {
        e.printStackTrace();
    }


}
