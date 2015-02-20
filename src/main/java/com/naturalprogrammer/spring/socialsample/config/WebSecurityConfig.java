package com.naturalprogrammer.spring.socialsample.config;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
    private final Log log = LogFactory.getLog(WebSecurityConfig.class);

	@Value("${spring.profiles.active}")
	private String env;

	@Value("${rememberMe.privateKey}")
	private String rememberMeKey;
    
	@Resource
	private UserDetailsService userService;
    
    @Bean
    public RememberMeServices rememberMeServices() {
    	
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeKey, userService);
        
        // See http://stackoverflow.com/questions/25565809/implementing-a-remember-me-for-spring-social
        rememberMeServices.setAlwaysRemember(true);
        
        return rememberMeServices;
        
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      log.info("Creating password encoder bean");
      return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
            .authorizeRequests()
                .antMatchers("/",
                		"/signup",
                		"/public/**",
                		"/users/*",
                		"/connect/**",
                		"/social/**",
                		"/auth/**").permitAll()
                .anyRequest().authenticated();
        
        http
            .formLogin()
                .loginPage("/signin")
                .permitAll().and()
            .rememberMe().key(rememberMeKey).rememberMeServices(rememberMeServices()).and()
            .logout()
                .deleteCookies("JSESSIONID")
                .permitAll();
        
	    if (!env.equals("dev"))
	    	http.requiresChannel().anyRequest().requiresSecure();

    }
    
	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}