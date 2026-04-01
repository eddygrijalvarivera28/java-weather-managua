import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class test_api {
    public static void main(String[] args) {

        ObjectMapper mapperTest = new ObjectMapper();
        String jsonString = """
                {
                  "coord": {
                    "lon": 10.99,
                    "lat": 44.34
                  },
                  "weather": [
                    {
                      "id": 501,
                      "main": "Rain",
                      "description": "moderate rain",
                      "icon": "10d"
                    }
                  ],
                  "base": "stations",
                  "main": {
                    "temp": 298.48,
                    "feels_like": 298.74,
                    "temp_min": 297.56,
                    "temp_max": 300.05,
                    "pressure": 1015,
                    "humidity": 64,
                    "sea_level": 1015,
                    "grnd_level": 933
                  },
                  "visibility": 10000,
                  "wind": {
                    "speed": 0.62,
                    "deg": 349,
                    "gust": 1.18
                  },
                  "rain": {
                    "1h": 3.16
                  },
                  "clouds": {
                    "all": 100
                  },
                  "dt": 1661870592,
                  "sys": {
                    "type": 2,
                    "id": 2075663,
                    "country": "IT",
                    "sunrise": 1661834187,
                    "sunset": 1661882248
                  },
                  "timezone": 7200,
                  "id": 3163858,
                  "name": "Zocca",
                  "cod": 200
                }
                """;
        try {
            test testClass = mapperTest.readValue(jsonString, test.class);
            System.out.println(testClass.getCoord().getLon());
            System.out.println(testClass.getCoord().getLat());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class test{
    private coord coord;

    public coord getCoord() {
        return coord;
    }

    public void setCoord(coord coord) {
        this.coord = coord;
    }
}
class coord{
    private long lat;
    private double lon;

    public double getLat(){return lat;}
    public void setLat(long lat){this.lat = lat;}

    public double getLon() {return lon;}
    public void setLon(double lon) {this.lon = lon;}
}
