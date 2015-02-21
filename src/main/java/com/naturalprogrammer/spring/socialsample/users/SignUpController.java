package com.naturalprogrammer.spring.socialsample.users;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naturalprogrammer.spring.socialsample.util.MyUtil;

@Controller
public class SignUpController {
	
	private final Log log = LogFactory.getLog(SignUpController.class);
	
	private SignupFormValidator signupFormValidator;
	private UserService userService;
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	public SignUpController(UserService userService,
			SignupFormValidator signupFormValidator,
			ProviderSignInUtils providerSignInUtils) {
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
		this.providerSignInUtils = providerSignInUtils;
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model, WebRequest request) {
		
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		model.addAttribute(SignupForm.fromConnection(connection));
		return "signup";
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, WebRequest request, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return "signup";

		User user = userService.signup(signupForm);
		providerSignInUtils.doPostSignUp(user.getEmail(), request);

		MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		
		return "redirect:/";

	}

}
