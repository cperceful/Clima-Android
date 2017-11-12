package com.londonappbrewery.climapm;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataModel {


    private String temperature;
    private int condition;
    private String city;
    private String iconName;

    public static WeatherDataModel fromJson(JSONObject jsonObject){
        try {
            WeatherDataModel weatherData = new WeatherDataModel();

            weatherData.city = jsonObject.getString("name");
            //get array named weather, index at [0], integer property named "id"
            weatherData.condition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherData.iconName = updateWeatherIcon(weatherData.condition);

            weatherData.temperature = convertTempKToFString(jsonObject.getJSONObject("main").getDouble("temp"));

            return weatherData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertTempKToFString(double kTemp){
    //takes Kelvin temperature from JSON, calculates temperature in F, rounds, and returns result converted to string
        double fTemp = 9.0/5.0 * (kTemp - 273.15) + 32.0;
        int roundedTemp = (int) Math.rint(fTemp);

        return Integer.toString(roundedTemp);
    }

    private static String updateWeatherIcon(int condition) {

        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }

    public String getTemperature() {
        return temperature + "°";
    }

    public String getCity() {
        return city;
    }

    public String getIconName() {
        return iconName;
    }
}
