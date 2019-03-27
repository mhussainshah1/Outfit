package com.example.demo.business.services;

/**
 * [Source: (String)
 * <p>
 * "{
 * <p>
 * "coord":{"lon":-73.99,"lat":40.73},
 * <p>
 * "weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],
 * <p>
 * "base":"stations",
 * <p>
 * "main":{"temp":275.41,"pressure":1020,"humidity":29,"temp_min":273.15,"temp_max":277.15},
 * <p>
 * "visibility":16093,
 * <p>
 * "wind":{"speed":3.6,"deg":360},
 * <p>
 * "clouds":{"all":1},
 * <p>
 * "dt":1553583986,
 * <p>
 * "sys":{"type":1,"id":6015,"message":0.0093,"country":"US","sunrise":1553597383,"sunset":1553642009},
 * <p>
 * "id":5128581,
 * <p>
 * "name":"New York",
 * <p>
 * "cod":200
 * <p>
 * }"
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

    private static final long serialVersionUID = 7406210628182440902L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("temp_min")
    private double tempMin;

    @JsonProperty("temp_max")
    private double tempMax;

    @JsonPropertyOrder("description")
    private String weatherDescription;

    public Weather() {

    }

    @Bean
    public Weather weather() {
        return new Weather();
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getTempMin() {
        return getCelcius(tempMin);
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return getCelcius(tempMax);
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTemp() {
        return getCelcius(temp);
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @JsonProperty("coord")
    public void setCoord(Map<String, Object> coord) {
        setLon((double) coord.get("lon"));
        setLat((double) coord.get("lat"));
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        for (Map<String, Object> weatherEntry : weatherEntries) {
            for (String key : weatherEntry.keySet()) {
                System.out.println(key + "  " + weatherEntry.get(key));
            }
        }
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherDescription((String) weather.get("description"));
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemp((double) main.get("temp"));
        setTempMin((double) main.get("temp_min"));
        setTempMax((double) main.get("temp_max"));
    }

    public double getCelcius(double kelvin) {
        return kelvin - 273;
    }
}
