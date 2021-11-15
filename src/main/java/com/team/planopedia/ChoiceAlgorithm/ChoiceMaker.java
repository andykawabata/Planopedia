package com.team.planopedia.ChoiceAlgorithm;

import com.team.planopedia.API.adapters.RestaurantApiAdapter;
import com.team.planopedia.dao.Category;
import com.team.planopedia.dao.Plan;
import com.team.planopedia.dao.User;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfoService;
import com.team.planopedia.modelsAndServices.restaurant.Restaurant;

import java.util.*;

public class ChoiceMaker {

    /**
     * This method will user ratings given by the user to predict which restaurant would best match their tastes.
     * This is accomplished by scoring each potential choice and then selecting the highest scored restaurant. (More information in SRD)
     * @param currentUser The model representing the user that is currently logged into the web service.
     * @param potentialList This is the list of restaurants matching the user specified cuisine and location which will be analyzed
     *                      to find the best single choice for the user.
     * @return A single Map representing the chosen restaurant.
     */
    public Map<String, String> makeDecision(User currentUser, ArrayList<Map<String, String>> potentialList) {
        Map<String, Integer> categoryScores = new HashMap<>(); //Get this from user table
        Map<Map<String, String>, Double> planScores = new HashMap<>(); //This will hold the final scores associated with each restaurant's Map
        List<Integer> restaurantScores = new ArrayList<>();  //This List will be filled by one restaurant's score at a time and then used to calculate the average before clearing
                                                                    //and then repeating the same for each other restaurant
        Double restaurantAvg;                                       //Temporarily holds the current res


        List<Plan> thisUserPlans = currentUser.getPlans();

        for(Plan thisPlan: thisUserPlans){      //Save each rated category from the user's previous plans into the categoryScores map
            for(Category thisCategory: thisPlan.getRestaurantInfo().getCategories()){
                if(thisPlan.getRating() != 0)  //0 means the plan is unrated, so skip its categories
                    categoryScores.put(thisCategory.getCategoryName(), thisPlan.getRating());
            }
        }

        //Error check to make sure a valid list of potential restaurants was passed
        if(potentialList == null)
            return null;

        //If the user has not rated any plans and the potential list has restaurants then pick a random restaurant from the list
        if(categoryScores.size() == 0 && potentialList.size() != 0)
            return BasicInfoService.pickRandomRestaurantFromList(potentialList);

        for(Map<String, String> map : potentialList) {                                 //For each restaurant in the plan list
            ArrayList<String> currentMapCategories = categoryParser(map);           //Parse the categories from the API map into an ArrayList
            for(String category : currentMapCategories){                            //For each category on this restaurant
                for (Map.Entry<String, Integer> entry : categoryScores.entrySet()) { //For Each category in the user's table
                    if (entry.getKey().compareTo(category) == 0){ //Check for same categories that have been rated
                        restaurantScores.add(entry.getValue());
                    }
                }
            }

            //Calculate average, add it to the Map and then clear the ArrayList for new values
            planScores.put(map, restaurantScores.stream().mapToInt(val -> val).average().orElse(0.0)); //Stores the current restaurant's final score
            restaurantScores.clear();
        }

        return pickBestRestaurant(planScores);

    }

    /**
     * This method is used in conjunction with the User.generateDummyUserWithPlans() to test the choice making algorithm
     * without needing a connection to the database.
     * @return The choice made for the dummy user.
     */
    public Map<String, String> makeDecisionTester() {
        Map<String, Integer> categoryScores = new HashMap<>(); //Get this from user table
        Map<Map<String, String>, Double> planScores = new HashMap<>(); //This will hold the final scores associated with each restaurant's Map
        List<Integer> restaurantScores = new ArrayList<>();  //This List will be filled by one restaurant's score at a time and then used to calculate the average before clearing
                                                                    //and then repeating the same for each other restaurant
        Double restaurantAvg;                                       //Temporarily holds the current res
        ArrayList<Restaurant> potentialList = new ArrayList<>();

        RestaurantApiAdapter rAdpt  = new RestaurantApiAdapter();
        ArrayList<Map<String, String>> apiResults = rAdpt.getRestaurants("Italian", "Greensboro", 5);

        User thisUser = User.generateDummyUserWithPlans();
        List<Plan> thisUserPlans = thisUser.getPlans();

        for(Plan thisPlan: thisUserPlans){      //Save each rated category from the user's previous plans into the categoryScores map
            for(Category thisCategory: thisPlan.getRestaurantInfo().getCategories()){
                if(thisPlan.getRating() != 0)  //0 means the plan is unrated, so skip its categories
                    categoryScores.put(thisCategory.getCategoryName(), thisPlan.getRating());
            }
        }

        if(apiResults == null)
            return null;

        for(Map<String, String> map : apiResults) {                                 //For each restaurant in the plan list
            ArrayList<String> currentMapCategories = categoryParser(map);           //Parse the categories from the API map into an ArrayList
            for(String category : currentMapCategories){                            //For each category on this restaurant
                for (Map.Entry<String, Integer> entry : categoryScores.entrySet()) { //For Each category in the user's table
                    if (entry.getKey().compareTo(category) == 0){ //Check for same categories that have been rated
                        restaurantScores.add(entry.getValue());
                    }
                }
            }

            //Calculate average, add it to the Map and then clear the ArrayList for new values
            planScores.put(map, restaurantScores.stream().mapToInt(val -> val).average().orElse(0.0)); //Stores the current restaurant's final score
            restaurantScores.clear();
        }

        Map<String, String> bestRestaurant  = pickBestRestaurant(planScores);

        System.out.println("\nBest: " + bestRestaurant.get("restaurantName"));

        return bestRestaurant;
    }

    /**
     * This method is used for pulling the unparsed categories out of the restaurant Map and into an ArrayList of Strings.
     * @param apiResults The Map representing a single restaurant.
     * @return An ArrayList consisting of the categories as separate Strings.
     */
    private ArrayList<String> categoryParser(Map<String, String> apiResults){

        return new ArrayList<>(Arrays.asList(apiResults.get("categories").split("'categorySeparator'")));

    }

    /**
     * This method is used to pull the highest rated potential restaurant from the list generated by the choice making algorithm.
     * When there is a tie for first place this method will choose randomly among the highest ranked.
     * @param ratedRestaurants List of ranked restaurants given by makeDecision()
     * @return The single best restaurant chosen from the list given.
     */
    private Map<String, String> pickBestRestaurant(Map<Map<String, String>, Double> ratedRestaurants){
        List<Map<String, String>> tieBreakList = new ArrayList<>();

        Double bestScore = Double.MIN_VALUE;
        Map<String, String> bestRestaurant = null;

        for (Map.Entry<Map<String, String>, Double> entry : ratedRestaurants.entrySet()) {
            //System.out.println(entry.getKey() + " " + entry.getValue()); //DEBUG purposes
            if(entry.getValue() > bestScore) {
                bestScore = entry.getValue();
                bestRestaurant  = entry.getKey();
                tieBreakList.clear();
                tieBreakList.add(bestRestaurant);
            }
            else if(entry.getValue().equals(bestScore)) {
                tieBreakList.add(entry.getKey());
            }
        }

        //When there are multiple restaurants tied for first place then a random restaurant from the tied list is chosen
        if(tieBreakList.size() > 1)
            bestRestaurant = tieBreakList.get((int)(Math.random() * tieBreakList.size()));

        return bestRestaurant;
    }


}
