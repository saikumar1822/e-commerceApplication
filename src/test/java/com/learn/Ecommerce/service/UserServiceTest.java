package com.learn.Ecommerce.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.learn.Ecommerce.dto.UserDto;
import com.learn.Ecommerce.exceptions.UserNotFoundException;
import com.learn.Ecommerce.model.User;
import com.learn.Ecommerce.repository.UserRepository;

import junit.framework.Assert;
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {
	
	
	
		@InjectMocks
		UserService userService;

		@Mock
		UserRepository userRepository;
		
		@Test
		public void testUpdateUserForNegitive() throws UserNotFoundException   {
			User user=new User();
			user.setUserId(1l);
			user.setPassword("kumar");
			user.setUsername("saikumar");
			Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn((user));
			User resUser = userService.UpdateUser(user);
			Assert.assertNotNull(resUser);
			Assert.assertEquals(user.getUsername(), resUser.getUsername());
		}
		
		@Test
		public void testUpdateUserForPositive() throws UserNotFoundException  {
			User user=new User();
			user.setUserId(1l);
			user.setPassword("kumar");
			user.setUsername("saikumar");
			Mockito.when(userRepository.save(user)).thenReturn((user));
			User resUser = userService.UpdateUser(user);
			Assert.assertNotNull(resUser);
			Assert.assertEquals(user.getUsername(), resUser.getUsername());
		}

		@Test(expected = NullPointerException.class)
		public void testSaveForNegitive() throws UserNotFoundException {
			User user=new User();
			Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(NullPointerException.class);
			User resUser = userService.UpdateUser(user);
		}
		@Test
		public void testGetUserForPositive() throws UserNotFoundException  {
			UserDto userDto = new UserDto();
			userDto.setEmail("sai@gmail.com");
			userDto.setPassword("sai");
			User user=new User();
			user.setUserId(1l);
			user.setPassword("kumar");
			user.setUsername("saikumar");
			user.setEmail("sai@gmail.com");
			Mockito.when(userRepository.findByEmailAndPassword(userDto.getEmail(),userDto.getPassword())).thenReturn(user);
			user = userService.getUser(userDto);
			Assert.assertNotNull(user);
		}
		@Test
		public void testgetUserForNegitive() throws UserNotFoundException  {
			UserDto userDto = new UserDto();
			userDto.setEmail("sai@gmail.com");
			userDto.setPassword("sai");
			User user=new User();
			user.setUserId(1l);
			user.setPassword("kumar");
			user.setUsername("saikumar");
			user.setEmail("sai@gmail.com");
			Mockito.when(userRepository.findByEmailAndPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

			user= userService.getUser(userDto);
			Assert.assertNotNull(user);
			Assert.assertEquals(user.getEmail(),userDto.getEmail());
		}
		
		@Test(expected = Exception.class)
		public void testgetUserForExce(){
			UserDto userDto = new UserDto();
			User user=new User();
			Mockito.when(userRepository.findByEmailAndPassword(userDto.getEmail(),userDto.getPassword())).thenThrow(Exception.class);
		}
}
