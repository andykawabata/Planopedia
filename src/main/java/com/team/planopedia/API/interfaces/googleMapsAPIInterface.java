/**
 * Will return information from google API
 *
 * @author Nimra Sami
 * @author Arturo Serdan
 * Last updated October 19, 2021
 */
package com.team.planopedia.API.interfaces;

public interface googleMapsAPIInterface {
     /**
     * Generates the necessary link to make the API call to the google Map API
     * @param _zipCode
     * @param _place
     * @return Returns a String with the link to do the API call
     */
    String getMapString(String _zipCode, String _place);
}
