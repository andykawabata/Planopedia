package com.team.planopedia.controllers;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    
    private static String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(), authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "login-page";
    }
    
//    @GetMapping("/")
//    public String helloWorld() {
//        return "index";
//    }
    
    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "this page can only be accessed if logged in";
    }
    
    @GetMapping("/loginSucess")
    public String loginSucess(@AuthenticationPrincipal OAuth2User user, Model model) {
        // IF USER NOT REGISTERED, ADD TO DB
        // IF USER ID NOT IN SESSION, ADD USER ID TO SESSION
        // GET USER BY ID
        // ADD USER TO MODEL
        
        //temporarilly just use oauth info
        model.addAttribute("user", user);
        return "index";
    }   

}