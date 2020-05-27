package com.learn.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.Ecommerce.dto.UserDto;
import com.learn.Ecommerce.exceptions.UserNotFoundException;
import com.learn.Ecommerce.model.Order;
import com.learn.Ecommerce.model.User;
import com.learn.Ecommerce.repository.OrderRepository;
import com.learn.Ecommerce.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;

	public User getUser(UserDto userDto) throws UserNotFoundException {

		User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("user is not found with this email or password");
		}

	}

	public User UpdateUser(User user) throws UserNotFoundException {
		if (user != null) {
			return userRepository.save(user);
		} else {
			throw new UserNotFoundException("user object should not be empty");
		}
	}
	
}
