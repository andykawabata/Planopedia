package com.team.planopedia.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.planopedia.dao.User;
import com.team.planopedia.repository.UserRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller handling all authentication endpoints
 * @author andrewkawabata
 */
@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    
    //base url for any Oauth2 request (endpoint handled by spring)
    private static String authorizationRequestBaseUri = "oauth2/authorization";
    
    //map for storing the auth urls for specific Oauth services
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    
    //ORM object for user
    @Autowired
    private UserRepository userRepository;

    // returns login page with google url
    @GetMapping("/login-page")
    public String getLoginPage(Model model) {
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final String googleUrl = baseUrl + '/' + authorizationRequestBaseUri + "/google";
        oauth2AuthenticationUrls.put("Google", googleUrl);
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "login-page";
    }

    // runs on successful authentication
    @GetMapping("/success")
    public String loginSucess(@AuthenticationPrincipal OAuth2User oauthUser, HttpSession session) {
        
        // get info from oauth object
        String email = (String) oauthUser.getAttributes().get("email");
        String name = (String) oauthUser.getAttributes().get("name");
        String picture = (String) oauthUser.getAttributes().get("picture");
        
        
        //check if user already exists
        User user = userRepository.findByGoogleEmail(email);
        boolean isNewUser = user == null;
        
        if(isNewUser){
            // build temp user object to store as session, this is needed so the info persists during onboarding
            User tempUser = new User();
            tempUser.setUserName(name);
            tempUser.setGoogleEmail(email);
            addUserSession(session, tempUser, "tempUser");
            return "redirect:/auth/onboard";
            
        }else{
            addUserSession(session, user, "user");
        }
        
        return "redirect:/";
    }
    
    private void addUserSession(HttpSession session, User user, String key){
        //User object is converted to map because setting the object as session var wouldn't work
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(user, Map.class);
        session.setAttribute(key, map);
    }
    
    // MUST BE CHANGED IF USER OBJECT CHANGES
    static User convertUserMapToObject(Map<String, Object> userMap){
        User user = new User();
        user.setUserName((String) userMap.get("userName"));
        user.setGoogleEmail((String) userMap.get("googleEmail"));
        return user;
    }
    
    // onboarding page, assumes user info saved in session
    @GetMapping("/onboard")
    public String onboardPage() {
        
        return "onboard";
    }
    
    // endpoint for after onboarding, actually saves the user and redirects to home
    @PostMapping("/register")
    public String saveUser(HttpSession session) {
        
        //Save data in DB
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("tempUser");
        User user = convertUserMapToObject(userMap);
        userRepository.save(user);
        
        //start session
        addUserSession(session,  user, "user");
        
        return "redirect:/";
    }
    
    //
    
    //@GetMapping("/logout")
    //public String logout() {  
    //   return "index";
    //} 
    
    

}