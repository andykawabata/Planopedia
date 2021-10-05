/**
 * Weather API class Class used to make calls to the WeatherAPI given data from
 * the WeatherAdapter
 *
 * Last updated September 30, 2021
 * @Author Nimra Sami
 * @Author Arturo Serdan
 */

package com.team.planopedia.API.translators;
import com.team.planopedia.API.interfaces.WeatherAPIInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class OpenWeatherMapAPI implements WeatherAPIInterface {

    // In order to create a HTTP Connection.
    private final static String baseURL = "http://api.openweathermap.org";
    private final static String callAction = "/data/2.5/weather?q=";
    //Private number of parameters to be returned
    private final static int numOfParametersReturned = 2;

    /**
     * Method used to grab data from the weather API, and return the data into
     * an Array String
     *
     * @param _zipCode
     * @param _country
     * @return weatherInf
     */
    @Override
    public String[] getWeather(String _zipCode, String _country) {
        String[] weatherInf = new String[OpenWeatherMapAPI.numOfParametersReturned];
        // Build the URL 
        String urlString = baseURL + callAction + _zipCode + "," + _country + "&appid=" + API_Keys.openWeatherMapAPI();
        URL url;

        try {

            // Creating the URL and connection
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Prints the respone code (USED FOR DEBUGGING)
            int status = connection.getResponseCode();

            //Response code used for debugging
            System.out.println("Weather API Response Code: " + status + " ---> SHOWN DURING DEBUG");

            /* Grabs the response from the API and appends it to the content variable
               until it's empty                                                    */
            StringBuilder content;
            try ( BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                content = new StringBuilder();

                //Appends all of the data to the input line until there is no more data
                while ((inputLine = inputStream.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            // Closing the connection after the response has been saved
            connection.disconnect();

            // Saving the data into an object
            JSONObject obj = new JSONObject(content.toString());

            // Calls to helper method to the weather information from JSONObjer obj and return as string array
            weatherInf = OpenWeatherMapAPI.getWeatherfromJSONObj(obj, weatherInf);

        } catch (IOException | JSONException ex) {
            Logger.getLogger(OpenWeatherMapAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weatherInf;
    }

    /**
     * Method to extract components for the weather from the JSON Object and to
     * be put on a String Array
     *
     * @param _obj contains information from API to be extracted
     * @param _WeatherInf the Array String where the weather Information will be
     * returned
     * @return _weatherInf the same Array String that is passed is returned with
     * the weather information
     */
    private static String[] getWeatherfromJSONObj(JSONObject _obj, String[] _weatherInf) {
        try {
            // Extracting the main object from the response for the temperature
            JSONObject mainObj = _obj.getJSONObject("main");
            int currentTemp = mainObj.getInt("temp");

            // Passes the int to String and adds it to weatherInf Array String (Can be changed to return celcius instead)
            _weatherInf[0] = Integer.toString(OpenWeatherMapAPI.toFahrenheit(currentTemp)) + " F";

            // Calls to convert temperature into Celcius and Fahrenheit & prints out
            //Extracting the weather array for the weather description
            JSONArray jsonArray = _obj.getJSONArray("weather");
            JSONObject weatherDescription = jsonArray.getJSONObject(0);
            String weatherDesc = weatherDescription.getString("description");

            // Adds the weather description to the weatherInf array string
            _weatherInf[1] = weatherDesc;

        } catch (JSONException ex) {
            Logger.getLogger(OpenWeatherMapAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _weatherInf;
    }

    /**
     * Method used to convert Kelvin to Celsius
     *
     * @param _temp temperature
     * @return tempCelsius contains new temperature in Celsius
     */
    public static int toCelsius(int _temp) {
        int tempCelsius = (int) (Math.ceil((_temp - 273.15)));
        return tempCelsius;
    }

    /**
     * Method used to convert Kelvin to Fahrenheit
     *
     * @param _temp temperature
     * @return tempFahrenheit contains new temperature in Fahrenheit
     */
    public static int toFahrenheit(int _temp) {
        int tempFahrenheit = (int) (((_temp - 273.15) * 1.8) + 32);
        return tempFahrenheit;
    }
}
