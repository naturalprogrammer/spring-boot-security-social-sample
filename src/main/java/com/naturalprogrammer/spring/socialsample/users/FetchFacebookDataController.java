package com.naturalprogrammer.spring.socialsample.users;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users/current/facebook-data")
public class FetchFacebookDataController {
	
    private Facebook facebook;

    @Inject
    public FetchFacebookDataController(Facebook facebook) {
        this.facebook = facebook;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String helloFacebook(Model model) {
    	
        if (!facebook.isAuthorized()) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList<Post> homeFeed = facebook.feedOperations().getHomeFeed();
        model.addAttribute("feed", homeFeed);

        return "facebookData";
    }
}
