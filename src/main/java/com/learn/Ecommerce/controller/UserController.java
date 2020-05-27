package com.learn.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.Ecommerce.dto.ResponseDto;
import com.learn.Ecommerce.dto.UserDto;
import com.learn.Ecommerce.exceptions.UserNotFoundException;
import com.learn.Ecommerce.model.User;
import com.learn.Ecommerce.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<ResponseDto> userLogin(@RequestBody UserDto userDto) throws UserNotFoundException{
		User user = new User();
		ResponseDto loginResponseDto = new ResponseDto();
		if (userDto != null) {
			user = userService.getUser(userDto);
			user.setEnabled(true);
			userService.UpdateUser(user);

			loginResponseDto.setMessage("logged in successfully");
			return new ResponseEntity<ResponseDto>(loginResponseDto, HttpStatus.OK);

		} else {
			throw new UserNotFoundException("email or password is empty");
		}
	}

}
