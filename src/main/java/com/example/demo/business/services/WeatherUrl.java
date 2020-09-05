package com.example.demo.business.services;

public class WeatherUrl {

    private String url;
    private String apiKey;

    public WeatherUrl() {
    }

    public WeatherUrl(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
