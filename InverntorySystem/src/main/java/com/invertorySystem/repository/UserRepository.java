package com.invertorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invertorySystem.bo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findById(long userId);

	public User findByUserName(String userName);
	
}
