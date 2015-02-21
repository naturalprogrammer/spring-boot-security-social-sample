package com.naturalprogrammer.spring.socialsample.users;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.naturalprogrammer.spring.socialsample.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService, SocialUserDetailsService {
	
    private final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		
	}

	@Override
	public SocialUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new UserDetailsImpl(user);
	}

	@Override
	public User findOne(long userId) {
		
		User user = userRepository.findOne(userId);
		return user;

	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public User signup(SignupForm signupForm) {
		final User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		userRepository.save(user);
        MyUtil.logInUser(user);
		return user;		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(long userId, UserEditForm userEditForm) {
		
		User user = userRepository.findOne(userId);
		user.setName(userEditForm.getName());
		userRepository.save(user);
		
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId)
			throws UsernameNotFoundException, DataAccessException {
		return loadUserByUsername(userId);
	}

}
