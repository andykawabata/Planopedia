package com.team.planopedia.controllers;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
    public String loginSucess(@AuthenticationPrincipal OAuth2User user, Model model) {
        // IF USER NOT REGISTERED, ADD TO DB
        // IF USER ID NOT IN SESSION, ADD USER ID TO SESSION
        // GET USER BY ID
        // ADD USER TO MODEL
        
        //temporarilly just use oauth info
        model.addAttribute("user", user);
        return "redirect:/";
    }   
    
    //@GetMapping("/logout")
    //public String logout() {  
    //   return "index";
    //} 
    
    

}