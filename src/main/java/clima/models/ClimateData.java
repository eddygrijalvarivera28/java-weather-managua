package clima.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimateData {
    public long dt;
    public Main main;
    public List<Weather> weather;
    public Wind wind;
    public int pop;
//    public String dt_text;
}
