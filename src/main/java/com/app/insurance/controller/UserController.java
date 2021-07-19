package com.app.insurance.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.insurance.model.User;
import com.app.insurance.repository.UserRepository;
import com.app.insurance.service.UserService;

import javassist.NotFoundException;

/**
 * @author jpaste
 *
 */
@CrossOrigin()
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * This method return {@link List} of {@link User}
	 * 
	 * @return {@link List} of {@link User}
	 * @throws Exception
	 */
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() throws Exception {
		logger.info("List of All Users Retreived Sucessfully");
		return userService.getAllUsers();
	}

	/**
	 * 
	 * This method return {@link User} that is added
	 * 
	 * @param user
	 * @return {@link User}
	 */
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		logger.info("User Added Successfully");
		return userService.addUser(user);
	}

	/**
	 * This method return {@link User} that is updated
	 * 
	 * @param user
	 * @return {@link User}
	 */
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		logger.info("User Updated Successfully");
		return userService.updateUser(user);
	}

	/**
	 * @param userId
	 * @throws Exception
	 */
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") int userId) throws NotFoundException {
		logger.info("User deleted Sucessfully");
		userService.deleteUser(userId);
	}

	/**
	 * This method return {@link Optional} of {@link User} based on the passed
	 * userId
	 * 
	 * @param userId
	 * @return {@link Optional} of {@link User}
	 * @throws Exception
	 */
	@GetMapping("/viewUser/{id}")
	public Optional<User> viewUser(@PathVariable("id") int userId) throws NotFoundException {
		logger.info("User With the given user id is retrieved");
		return userService.viewUser(userId);
	}

	/**
	 * This method return {@link User} based on logged in User
	 * 
	 * @param users
	 * @return {@link User}
	 * @throws NotFoundException
	 */
	@PostMapping("/login")
	public User login(@RequestBody User users) throws NotFoundException {
		logger.info("User Login");
		return userService.userLogin(users);
	}

	@PostMapping(value = "/userLogin")
	public ResponseEntity<?> userLogin(@RequestBody User loginRequest, HttpServletRequest request,
			HttpServletResponse response) throws NotFoundException {
		String generatedToken = userService.userLogin(loginRequest, response);
		if (generatedToken != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>("{Invalid user}", HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param email
	 * @param password
	 * @return
	 */
	@CrossOrigin()
	@GetMapping("/customerlogin/{email}/{password}")
	public ResponseEntity<?> customerLogin(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		User customerUser = userRepository.findByEmailAndPassword(email, password);
		try {
			if (customerUser != null
					&& (customerUser.getRole().equals("ADMIN") || customerUser.getRole().equals("USER")
							|| customerUser.getRole().equals("SUPERADMIN"))
					&& customerUser.getStatus().equals("ACTIVE")) {
				return new ResponseEntity<User>(customerUser, HttpStatus.OK);
			} else
				throw new NotFoundException("Failed");
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method return {@link Optional} of {@link User} based on the passed
	 * emailId of the User
	 * 
	 * @param email
	 * @return {@link Optional} of {@link User}
	 * @throws Exception
	 */
	@GetMapping("/viewUserbyEmail/{email}")
	public Optional<User> viewUserbyEmail(@PathVariable("email") String email) throws NotFoundException {
		logger.info("User With the given email id is retrieved");
		return userService.viewUserbyEmail(email);
	}
}
