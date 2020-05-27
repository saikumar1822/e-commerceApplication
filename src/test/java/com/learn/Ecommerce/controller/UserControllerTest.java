package com.learn.Ecommerce.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.learn.Ecommerce.dto.ResponseDto;
import com.learn.Ecommerce.dto.UserDto;
import com.learn.Ecommerce.exceptions.UserNotFoundException;
import com.learn.Ecommerce.model.User;
import com.learn.Ecommerce.service.UserService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;
	
	
	@Test
	public void testGetUserForPositive() throws UserNotFoundException {
		UserDto userDto = new UserDto();
		userDto.setEmail("sai@gmail.com");
		userDto.setPassword("sai");
		User user=new User();
		user.setUserId(1l);
		user.setPassword("sai");
		user.setUsername("saikumar");
		user.setEmail("sai@gmail.com");
		Mockito.when(userService.getUser(userDto)).thenReturn(user);
		ResponseEntity<ResponseDto> user1 = userController.userLogin(userDto);
	}
	@Test
	public void testgetUserForNegitive() throws UserNotFoundException {
		UserDto userDto = new UserDto();
		userDto.setEmail("sai@gmail.com");
		userDto.setPassword("sai");
		User user=new User();
		user.setUserId(1l);
		user.setPassword("sai");
		user.setUsername("saikumar");
		user.setEmail("sai@gmail.com");
		Mockito.when(userService.getUser(Mockito.any(UserDto.class))).thenReturn(user);

		ResponseEntity<ResponseDto> user1 = userController.userLogin(userDto);
		
	}
	
	@Test(expected = Exception.class)
	public void testgetUserForExce() throws UserNotFoundException{
		UserDto userDto = new UserDto();
		User user=new User();
		Mockito.when(userService.getUser(userDto)).thenThrow(Exception.class);
	}

	

}
