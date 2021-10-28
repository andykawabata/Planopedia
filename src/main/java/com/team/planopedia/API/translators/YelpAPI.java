/**
 * This Class is meant to call the Yelp API given data from YelpAdapter in order
 * to get different restaurants and reviews of them.
 * Last updated September 30, 2021
 *
 * @Author Nimra Sami
 * @Author Arturo Serdan
 */

package com.team.planopedia.API.translators;
import com.team.planopedia.API.interfaces.APIFoodInterface;
import com.team.planopedia.API.interfaces.APIReviewInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;



public class YelpAPI implements APIFoodInterface, APIReviewInterface {

    private static final String baseUrlYelp = "https://api.yelp.com";
    private static final String callActionYelpRestaurantSearch = "/v3/businesses/search?";
    private static final String callActionYelpReviews = "/v3/businesses/";
    public static int numberOfValuesForEachRestaurant = 5;

    /**
     * Method that makes a API call to the Yelp API for a list of restaurants in
     * the location given by the user
     *
     * @param _term breakfast, lunch, or dinner, etc. - Must not contain spaces 
     * @param _city city where user is located
     * @param _limit search limit chosen by the user
     * @return array values
     */
    @Override
    public ArrayList<Map<String, String>> getRestaurants(String _term, String _city, int _limit) {
        // Stores the restaurants data from the API 
        ArrayList<Map<String, String>> restaurantsData = new ArrayList<Map<String, String>>();

        //Build the url
        String urlString = baseUrlYelp + callActionYelpRestaurantSearch + "term=" + _term + "&location=" + _city + "&limit=" + _limit;
        URL url;

        try {

            //Creating the URL and connection
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //Set the request header for authorization to use the Yelp API
            connection.setRequestProperty("Authorization", "Bearer " + API_Keys.yelp());

            //Prints out the response code
            int status = connection.getResponseCode();

            //Response code used for debugging
            System.out.println("Yelp API Response Code: " + status + " ---> SHOWN FOR DEBUG PURPOSES");

            /* Grabs the response from the API and appends it to the content variable
               until it's empty                                                    */
            StringBuilder content;
            try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                content = new StringBuilder();

                //Appends all of the data to the inputLine until there is no more data
                while ((inputLine = inputStream.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            // Closing the connection after the response has been saved
            connection.disconnect();

            // Saves API response into an object
            JSONObject obj = new JSONObject(content.toString());
            JSONArray jsonArray = obj.getJSONArray("businesses");

            // Looping through every restaurant and adding it to the array
            for (int i = 0; i < _limit; i++) {
                JSONObject businessIndex = jsonArray.getJSONObject(i);
                JSONObject businessLocation = businessIndex.getJSONObject("location");
                restaurantsData.add(i, getRestaurantsHelper(businessIndex, businessLocation));

            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(YelpAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantsData;
    }

    /**
     * This is a helper method for getRestaurant, helps extracting information from the JSONObject and put
     * it into a map, then returns the map.
     * 
     * @param _businessIndex index of the JSONObject, index of restaurants
     * @param _businessLocation contains the locations information to get the name
     * @return tempRestaurantData A map containing the restaurant information
     */
    private Map<String, String> getRestaurantsHelper(JSONObject _businessIndex, JSONObject _businessLocation) throws IOException {
        Map<String, String> tempRestaurantData = new HashMap<String, String>();
        JSONArray categories = _businessIndex.getJSONArray("categories");

        tempRestaurantData.put("restaurantID", _businessIndex.getString("id"));
        tempRestaurantData.put("restaurantName", _businessIndex.getString("name"));
        tempRestaurantData.put("address", _businessLocation.getString("address1"));
        tempRestaurantData.put("zipCode", _businessLocation.getString("zip_code"));
        tempRestaurantData.put("rating", Integer.toString(_businessIndex.getInt("rating")));
        tempRestaurantData.put("price", _businessIndex.getString("price"));
        tempRestaurantData.put("phoneNumber", _businessIndex.getString("display_phone"));
        tempRestaurantData.put("image_url", _businessIndex.getString("image_url"));

        if (categories.length() > 0) {
            int i = 0;
            String tempCategories = "";
            String tempTitles = "";
            while (i <= categories.length() - 1) {
                //System.out.println(categories.getJSONObject(i).get("alias"));
                if (i == 0) {
                    tempCategories += (categories.getJSONObject(i).get("alias")).toString();
                    tempTitles += (categories.getJSONObject(i).get("title")).toString();
                } else {
                    tempCategories += "'categorySeparator'" + (categories.getJSONObject(i).get("alias")).toString();
                    tempTitles += "'titleSeparator'" + (categories.getJSONObject(i).get("title")).toString();
                }

                i++;
            }
            tempRestaurantData.put("categories", tempCategories);
            tempRestaurantData.put("titles", tempTitles);

        } else {
            throw new IOException("No categories assigned");
        }

        return tempRestaurantData;
    }
    
    /**
     * This method returns 3 reviews from Yelp, this reviews are not random.
     * The method only requires the restauranID to make the API call.
     * 
     * @param _restaurantID string containing the restaurant ID
     * @return returns restaurantReviewsData as an ArrayListMap  containing the 3 reviews and their fields
     */
    public ArrayList<Map<String, String>> getRestaurantReviews(String _restaurantID) {
        ArrayList<Map<String, String>> restaurantReviewsData = new ArrayList<Map<String, String>>();

        //Build the url
        String urlString = baseUrlYelp + callActionYelpReviews + _restaurantID + "/reviews";
        URL url;

        try {

            //Creating the URL and connection
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //Set the request header for authorization to use the Yelp API
            connection.setRequestProperty("Authorization", "Bearer " + API_Keys.yelp());

            //Prints out the response code
            int status = connection.getResponseCode();

            //Response code used for debugging
            System.out.println("Yelp API Response Code: " + status + " ---> SHOWN FOR DEBUG PURPOSES");

            /* Grabs the response from the API and appends it to the content variable
               until it's empty                                                    */
            StringBuilder content;
            try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                content = new StringBuilder();

                //Appends all of the data to the inputLine until there is no more data
                while ((inputLine = inputStream.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            // Closing the connection after the response has been saved
            connection.disconnect();

            // Saves API response into an object
            JSONObject obj = new JSONObject(content.toString());
            JSONArray jsonArrayReviews = obj.getJSONArray("reviews");
            
            // Yelp allows only 3 retrieves on reviews
            int _limit = 3;
            
            // Looping through every restaurant and adding it to the array
            for (int i = 0; i < _limit; i++) {
                JSONObject reviewsIndex = jsonArrayReviews.getJSONObject(i);
                restaurantReviewsData.add(i, getRestaurantReviewsHelper(reviewsIndex));
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(YelpAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantReviewsData;
    }
    
    
    /**
     * This helper method returns the reviews from Yelp, only needing the JSONOject with the index of the reviews
     * @param _reviewsIndex JSONObject containing the review information
     * @return tempReviewsData as ArrayListMap containing a single review and its respective fields
     */
    private Map<String, String> getRestaurantReviewsHelper(JSONObject _reviewsIndex) {
        Map<String, String> tempReviewsData = new HashMap<String, String>();

        tempReviewsData.put("reviewID", _reviewsIndex.getString("id"));
        tempReviewsData.put("reviewerName", _reviewsIndex.getJSONObject("user").getString("name"));
        tempReviewsData.put("rating", Integer.toString(_reviewsIndex.getInt("rating")));
        tempReviewsData.put("text", _reviewsIndex.getString("text"));
        tempReviewsData.put("time", _reviewsIndex.getString("time_created"));

        return tempReviewsData;
    }
    
        /**
     * Method that makes a API call to the Yelp API to search for a specific restaurant given
     * the name, city, and the number of results wanted
     *
     * @param _restaurantName breakfast, lunch, or dinner, etc. - MUST NOT CONTAIN SPACES 
     * @param _city city where user is located - can be zip or city name
     * @param _limit search limit chosen by the user 
     * @return array values
     */
    @Override
    public ArrayList<Map<String, String>> getRestaurantSearchByName(String _restaurantName, String _city, int _limit) {
        // Stores the restaurants data from the API 
        ArrayList<Map<String, String>> restaurantsData = new ArrayList<Map<String, String>>();

        //Build the url
        String urlString = baseUrlYelp + callActionYelpRestaurantSearch + "term=" + _restaurantName + "&location=" + _city + "&limit=" + _limit;
        URL url;

        try {

            //Creating the URL and connection
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //Set the request header for authorization to use the Yelp API
            connection.setRequestProperty("Authorization", "Bearer " + API_Keys.yelp());

            //Prints out the response code
            int status = connection.getResponseCode();

            //Response code used for debugging
            System.out.println("Yelp API Response Code: " + status + " ---> SHOWN FOR DEBUG PURPOSES");

            /* Grabs the response from the API and appends it to the content variable
               until it's empty                                                    */
            StringBuilder content;
            try ( BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                content = new StringBuilder();

                //Appends all of the data to the inputLine until there is no more data
                while ((inputLine = inputStream.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            // Closing the connection after the response has been saved
            connection.disconnect();

            // Saves API response into an object
            JSONObject obj = new JSONObject(content.toString());
            JSONArray jsonArray = obj.getJSONArray("businesses");

            // Looping through every restaurant and adding it to the array
            for (int i = 0; i < _limit; i++) {
                JSONObject businessIndex = jsonArray.getJSONObject(i);
                JSONObject businessLocation = businessIndex.getJSONObject("location");
                restaurantsData.add(i, getRestaurantSearchByNameHelper(businessIndex, businessLocation));

            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(YelpAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantsData;
    }

    /**
     * This is a helper method for getRestaurantSearchByName, helps extracting
     * information from the JSONObject and put it into a map, then returns the
     * map.
     *
     * @param _businessIndex index of the JSONObject, index of restaurants
     * @param _businessLocation contains the locations information to get the
     * name
     * @return tempRestaurantData A map containing the restaurant information
     */
    private Map<String, String> getRestaurantSearchByNameHelper(JSONObject _businessIndex, JSONObject _businessLocation) throws IOException {
        Map<String, String> tempRestaurantData = new HashMap<String, String>();
        JSONArray categories = _businessIndex.getJSONArray("categories");

        tempRestaurantData.put("restaurantID", _businessIndex.getString("id"));
        tempRestaurantData.put("restaurantName", _businessIndex.getString("name"));
        tempRestaurantData.put("address", _businessLocation.getString("address1"));
        tempRestaurantData.put("zipCode", _businessLocation.getString("zip_code"));
        tempRestaurantData.put("rating", Integer.toString(_businessIndex.getInt("rating")));
        tempRestaurantData.put("price", _businessIndex.getString("price"));
        tempRestaurantData.put("phoneNumber", _businessIndex.getString("display_phone"));
        tempRestaurantData.put("image_url", _businessIndex.getString("image_url"));

        if (categories.length() > 0) {
            int i = 0;
            String tempCategories = "";
            String tempTitles = "";
            while (i <= categories.length() - 1) {
                //System.out.println(categories.getJSONObject(i).get("alias"));
                if (i == 0) {
                    tempCategories += (categories.getJSONObject(i).get("alias")).toString();
                    tempTitles += (categories.getJSONObject(i).get("title")).toString();
                } else {
                    tempCategories += "'categorySeparator'" + (categories.getJSONObject(i).get("alias")).toString();
                    tempTitles += "'titleSeparator'" + (categories.getJSONObject(i).get("title")).toString();
                }

                i++;
            }
            tempRestaurantData.put("categories", tempCategories);
            tempRestaurantData.put("titles", tempTitles);

        } else {
            throw new IOException("No categories assigned");
        }

        return tempRestaurantData;
    }
    
}
