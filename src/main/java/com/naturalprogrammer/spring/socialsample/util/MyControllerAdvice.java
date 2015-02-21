package com.naturalprogrammer.spring.socialsample.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {
	
	public static final String FACEBOOK_SCOPE = "read_stream,email,user_friends,user_about_me,user_actions.books";

	@ModelAttribute("facebookScope")
	public String getFacebookScope() {
		return FACEBOOK_SCOPE;
	}

}
