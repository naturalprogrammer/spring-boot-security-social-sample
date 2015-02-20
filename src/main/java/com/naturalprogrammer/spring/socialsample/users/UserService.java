package com.naturalprogrammer.spring.socialsample.users;

public interface UserService {
	
	public abstract User signup(SignupForm signupForm);

	public abstract User findOne(long userId);

	public abstract void update(long userId, UserEditForm userEditForm);

}
