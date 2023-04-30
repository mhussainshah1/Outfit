package com.outfit.business.services;

/*
  [Source: (String)
  "{
      "coord":{"lon":-73.99,"lat":40.73},
      "weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],
      "base":"stations",
      "main":{"temp":275.41,"pressure":1020,"humidity":29,"temp_min":273.15,"temp_max":277.15},
      "visibility":16093,
      "wind":{"speed":3.6,"deg":360},
      "clouds":{"all":1},
      "dt":1553583986,
      "sys":{"type":1,"id":6015,"message":0.0093,"country":"US","sunrise":1553597383,"sunset":1553642009},
      "id":5128581,
      "name":"New York",
      "cod":200
  }"
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.context.annotation.Bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

    @Serial
    private static final long serialVersionUID = 7406210628182440902L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lat")
    private Number lat;

    @JsonProperty("lon")
    private Number lon;

    @JsonPropertyOrder("main")
    private String weatherMain;

    @JsonPropertyOrder("description")
    private String weatherDescription;

    @JsonProperty("temp")
    private Number temp;

    @JsonProperty("temp_min")
    private Number tempMin;

    @JsonProperty("temp_max")
    private Number tempMax;

    @JsonProperty("speed")
    private Number windSpeed;

    @JsonProperty("deg")
    private Number windDirection;

    public Weather() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getLat() {
        return lat;
    }

    public void setLat(Number lat) {
        this.lat = lat;
    }

    public Number getLon() {
        return lon;
    }

    public void setLon(Number lon) {
        this.lon = lon;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Double getTemp() {
        return (Double) temp;
    }

    public void setTemp(Number temp) {
        this.temp = temp;
    }

    public Number getTempMin() {
        return tempMin;
    }

    public void setTempMin(Number tempMin) {
        this.tempMin = tempMin;
    }

    public Number getTempMax() {
        return tempMax;
    }

    public void setTempMax(Number tempMax) {
        this.tempMax = tempMax;
    }

    public Number getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Number windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Number getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Number windDirection) {
        this.windDirection = windDirection;
    }

    @Bean
    public Weather weather() {
        return new Weather();
    }

    @JsonProperty("coord")
    public void setCoord(Map<String, Object> coord) {
        setLon((Number) coord.get("lon"));
        setLat((Number) coord.get("lat"));
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        /*for (var weatherEntry : weatherEntries) {//Map<String, Object>
            for (var key : weatherEntry.keySet()) {//String
                System.out.println(key + "  " + weatherEntry.get(key));
            }
        }*/
        var weather = weatherEntries.get(0);
        setWeatherDescription((String) weather.get("description"));
        setWeatherMain((String) weather.get("main"));
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemp((Number) main.get("temp"));
        setTempMin((Number) main.get("temp_min"));
        setTempMax((Number) main.get("temp_max"));
    }

    @JsonProperty("wind")
    public void setWind(Map<String, Object> wind) {
        setWindSpeed((Number) wind.get("speed"));
        setWindDirection((Number) wind.get("deg"));
    }

    public String getFahrenheitTemperature(double kelvin) {
        var fahrenheitTemp = (kelvin * 1.8) - 459.67;
        return String.format("%4.1f", fahrenheitTemp);
    }

    public String getCelsiusTemperature(double kelvin) {
        var celsiusTemp = kelvin - 273.15;
        return String.format("%4.1f", celsiusTemp);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", weatherMain='" + weatherMain + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", temp=" + temp +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                '}';
    }
}
