package com.team.planopedia.API.translators;

/** This class is meant to help different methods with common string checks for
 * the API classes, and others who might need it.
 *
 * Last updated 11/02/2021
 * 
 * @author Nimra Sami
 * @author Arturo Serdan
 */
public class String_Handler {
    
    /**
     * This method is meant to remove spaces from a String
     * @param _originalString Accepts any string
     * @return returns the same string without Spaces
     */
    public String removeSpaces(String _originalString){
        // String to store the new Sting without spaces
        String withoutSpaces = "";
        // Loops through the original String to check for spaces,
        for(int i =0; i<_originalString.length(); i++){
            char temp_char = _originalString.charAt(i);
            if (temp_char != ' '){
                withoutSpaces += temp_char;                     // Assigns the charater to the new String if it's not a space
                // System.out.println(temp_char);
            }
        }
        // Returns the new String
        return _originalString.replaceAll(" ", "_").toLowerCase();
    }
    
    
    /**
     * This method checks if the String contains any spaces
     * @param _originalString Accepts any String
     * @return boolean checked - false if there are not any spaces, true if it contains spaces
     */
    public boolean checkIfSpaces(String _originalString){
        // boolean variable to be returned, set to false
        boolean checked = false;
        char spaces = ' ';
        
        // loops through the string to check for a space character
        for(int i =0; i< _originalString.length(); i++){
            // if a space is found checkded it's changed to true, and it breaks the loop
            if(_originalString.charAt(i) == spaces){
                checked = true;
                break;
            }
        }
        return checked;
    }
    
}
