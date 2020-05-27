package com.learn.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.Ecommerce.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmailAndPassword(String email,String password);

}
