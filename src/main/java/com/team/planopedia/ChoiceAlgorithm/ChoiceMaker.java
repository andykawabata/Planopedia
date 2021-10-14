package com.team.planopedia.ChoiceAlgorithm;

import com.team.planopedia.dao.User;
import com.team.planopedia.modelsAndServices.restaurant.Restaurant;

import java.util.*;

public class ChoiceMaker {

    public Restaurant makeDecision(User currentUser, ArrayList<Restaurant> potentialList) {
        Map<String, Integer> categoryScores = new HashMap<>(); //Get this from user table
        Map<Restaurant, Double> planScores = new HashMap<>();
        List<Integer> restaurantScores = new ArrayList<Integer>();
        Double restaurantAvg;


        for (Restaurant r : potentialList) {         //For each restaurant in the plan list
            for(/*String category : r.categories*/){ //For each category on this restaurant
                for (Map.Entry<String, Integer> entry : categoryScores.entrySet()) { //For Each category in the user's table
                    if (entry.getKey() == ""/*category*/){ //Check for same categories that have been rated
                        restaurantScores.add(entry.getValue());
                    }
                }
            }

            //Calculate average, add it to the Map and then clear the ArrayList for new values
            restaurantAvg = restaurantScores.stream().mapToInt(val -> val).average().orElse(0.0);
            planScores.put(r, restaurantAvg);
            restaurantScores.clear();
        }

        //Get highest scored restaurant from the list
        Double bestScore = Double.MIN_VALUE;
        Restaurant bestRestaurant;
        for (Map.Entry<Restaurant, Double> entry : planScores.entrySet()) {
            if(entry.getValue() > bestScore) {
                bestScore = entry.getValue();
                bestRestaurant  = entry.getKey();
            }
        }

        return bestRestaurant;
    }


}
