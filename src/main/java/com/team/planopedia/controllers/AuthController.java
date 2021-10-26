package com.team.planopedia.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.planopedia.dao.RatingAlgorithm;
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

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    
    
    private static String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login-page")
    public String getLoginPage(Model model) {
       /* Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(), authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);*/
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final String googleUrl = baseUrl + '/' + authorizationRequestBaseUri + "/google";
        oauth2AuthenticationUrls.put("Google", googleUrl);
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "login-page";
    }

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
    
    @GetMapping("/onboard")
    public String onboardPage() {
        
        return "onboard";
    }
    
    @PostMapping("/register")
    public String saveUser(HttpSession session) {
        
        //Save data in DB
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("tempUser");
        User user = convertUserMapToObject(userMap);
        user.setRatingAlgorithms(new ArrayList<RatingAlgorithm>());
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