package com.naturalprogrammer.spring.socialsample.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
