package com.naturalprogrammer.spring.socialsample.users;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/users/current/facebook-data")
@SessionAttributes("redirectAfterConnecting")
public class FetchFacebookDataController {
	
    private Facebook facebook;

    @Inject
    public FetchFacebookDataController(Facebook facebook) {
        this.facebook = facebook;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String helloFacebook(Model model, SessionStatus status) {
    	
        if (!facebook.isAuthorized()) {
        	model.addAttribute("redirectAfterConnecting", "/users/current/facebook-data");
            return "forward:/connect/facebook";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList<Post> homeFeed = facebook.feedOperations().getHomeFeed();
        model.addAttribute("feed", homeFeed);
        
        status.setComplete();

        return "facebookData";
    }
}
