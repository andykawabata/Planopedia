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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class OpenWeatherMapAPI implements WeatherAPIInterface {

    // In order to create a HTTP Connection.
    private final static String baseURL = "http://api.openweathermap.org";
    private final static String callAction = "/data/2.5/weather?q=";
    //Private number of parameters to be returned
    private final static int numOfParametersReturned = 3;

    /**
     * Method used to grab data from the weather API, and return the data into
     * an HashMap<String, String>
     *
     * @param _zipCode zipCode for the area, can accept city as zipCode
     * @param _country the country code, for the United States the code is 'us'
     * @return weatherInf as a HashMap containing the weather, weather 
     * description and an icon.
     */
    @Override
    public Map<String, String> getWeather(String _zipCode, String _country) {
        // Checks if any of the passed Strings contains spaces in between
        String_Handler str_handler = new String_Handler();
        if(str_handler.checkIfSpaces(_zipCode) == true){
            _zipCode = str_handler.removeSpaces(_zipCode);
        }
        
        if(str_handler.checkIfSpaces(_country) == true){
            _country = str_handler.removeSpaces(_country);
        }
        
        Map<String, String> weatherInf = new HashMap<String, String>();
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

            // Extracting the main object from the response for the temperature
            JSONObject mainObj = obj.getJSONObject("main");
            int currentTemp = mainObj.getInt("temp");

            // Passes the int currentTemp to String and puts it to weatherInf (Can be changed to return celcius instead)
            weatherInf.put("temperatureInFahrenheit", Integer.toString(OpenWeatherMapAPI.toFahrenheit(currentTemp)) + " F");

            //Extracting the weather array for the weather description
            JSONArray jsonArray = obj.getJSONArray("weather");
            JSONObject weatherObejct = jsonArray.getJSONObject(0);
            String weatherDesc = weatherObejct.getString("description");

            String weatherImageURL = "http://openweathermap.org/img/wn/" + weatherObejct.getString("icon") + ".png";
            // Adds the weather description to weatherInf
            weatherInf.put("weatherDescription", weatherDesc);

            // Adds the weather description to weatherInf
            weatherInf.put("weatherImageURL", weatherImageURL);

        } catch (IOException | JSONException ex) {
            Logger.getLogger(OpenWeatherMapAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weatherInf;
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
