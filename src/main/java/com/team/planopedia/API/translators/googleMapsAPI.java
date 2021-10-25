/**
 * Google API class used to make calls to the google API given data from
 * the google adapter
 * Last updated October 19, 2021
 *
 * @Author Nimra Sami
 * @Author Arturo Serdan
 */
package com.team.planopedia.API.translators;

import com.team.planopedia.API.interfaces.googleMapsAPIInterface;

public class googleMapsAPI implements googleMapsAPIInterface {

    // In order to create a HTTP Connection.
    private final static String baseURL = "https://www.google.com/";
    private final static String callAction = "maps/embed/v1/place?";

    /**
     * Generates the necessary link to make the API call to the google Map API
     *
     * @param _zipCode it is the zipCode or the City along with the state; ex:
     * "27407", OR "Greensboro,NC"
     * @param _place
     * @return Returns a String with the link to do the API call
     */
    @Override
    public String getMapString(String _zipCode, String _place) {
        String mapSrting = "";
        // Build the URL 
        String urlString = baseURL + callAction + "key=" + API_Keys.googleAPI() + "&q=" + _place + "," + _zipCode;
        mapSrting = urlString;
        return mapSrting;
    }

}
