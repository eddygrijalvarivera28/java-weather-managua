package clima.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimateDataResponse {
    public int cod;
    public String message;
    public int cnt;
    public List<ClimateData> list;
}
