package com.app.insurance.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.insurance.model.Role;
import com.app.insurance.model.Roles;
import com.app.insurance.model.User;
import com.app.insurance.repository.UserRepository;
import com.app.insurance.util.Encryption;
import com.app.insurance.util.JwtUtil;

import javassist.NotFoundException;

/**
 * @author jpaste This class contains all the implemented methods and its
 *         business logic of its interface
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final String SUPERADMIN = "SUPERADMIN";

	private static final String USER_STATUS = "INACTIVE";

	private static final String USER_ROLE = "USER";

	Set<Role> role = new HashSet<>();

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * This method return {@link List} of {@link User}
	 * 
	 * @return {@link List} of {@link User}
	 * @throws NotFoundException
	 * @throws Exception
	 */
	@Override
	public List<User> getAllUsers() throws NotFoundException {
//		List<User> allUsers = userRepository.findAll();
//		if (allUsers == null) {
//			logger.info("List of All Users is Empty");
//			throw new NotFoundException("NO USERS");
//	}
//		for (int i = 0; i < allUsers.size(); i++) {
//		User userObject = allUsers.get(i);
//		if (userObject.getRole().equals(SUPERADMIN)) {
//				allUsers.remove(i);
//		}
//		}
//		logger.info("List of All Users Retreived Sucessfully");
//		return allUsers;

		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty()) {
			throw new NotFoundException("User list is empty");
		}
		logger.info("All Users returned from User Service");
		return allUsers;

	}

	/**
	 * 
	 * This method return {@link User} that is added
	 * 
	 * @param user
	 * @return {@link User}
	 */
	@Override
	@Transactional
	public User addUser(User user) {
		user.setPassword(Encryption.encryptedPassword(user.getPassword()));
		user.setRole(USER_ROLE);
		user.setStatus(USER_STATUS);
		logger.info("User Added Successfully");
		return userRepository.save(user);
	}

	@Override
	public String login(User loginReq) {
		Optional<User> maybeUser = Optional.of(userRepository.findByEmailAndPassword(loginReq.getEmail(),
				Encryption.encryptedPassword(loginReq.getPassword())));
		System.out.println(maybeUser);

		if (maybeUser.isPresent()) {
			System.out.println("Sucessful login");

			return JwtUtil.jwtTokenGenerator(Encryption.encryptedPassword(loginReq.getPassword()),
					maybeUser.get().getUserId());
		} else
			return "Invalid User";
	}

	// This method is used for logging into the user account
	@Override
	public String userLogin(User loginReq, HttpServletResponse response) throws NotFoundException {
//			Optional<User> maybeUser = Optional.of(userRepository.findByEmailAndPassword(loginReq.getEmail(),
//					Encryption.encryptedPassword(loginReq.getPassword())));
		Optional<User> maybeUser = Optional
				.of(userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword()));
		System.out.println(maybeUser);

		if (maybeUser.isPresent()) {
			System.out.println("Sucessful login");

			String token = JwtUtil.jwtTokenGenerator(Encryption.encryptedPassword(loginReq.getPassword()),
					maybeUser.get().getUserId());
			if (token != null) {
				response.addHeader("token", token);
				response.addHeader("Access-control-Allow-Headers", "*");
				response.addHeader("Access-control-Expose-Headers", "*");
				return token;
			}
//				} else if(token==null){
//					System.out.println("Token not generated");
//					return null;
//				}
			else {
				return null;
			}
		} else
			return "Invalid User";
	}

	/**
	 * This method return {@link User} that is updated
	 * 
	 * @param user
	 * @return {@link User}
	 */
	@Override
	@Transactional
	public User updateUser(User user) {
		logger.info("User Updated Successfully");
		return userRepository.save(user);
	}

	/**
	 * @param userId
	 * @throws NotFoundException
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void deleteUser(int userId) throws NotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (user==null) {
			logger.info("User With the given user id not found");
			throw new NotFoundException("NO USER TO DELETE");
		} else {
			logger.info("User With the given user id is deleted");
			userRepository.deleteById(userId);
		}
	}

	/**
	 * This method return {@link Optional} of {@link User} based on the passed
	 * userId
	 * 
	 * @param userId
	 * @return {@link Optional} of {@link User}
	 * @throws NotFoundException
	 */
	@Override
	public Optional<User> viewUser(int userId) throws NotFoundException {
		Optional<User> user = userRepository.findById(userId);
		if (user == null) {
			logger.info("User With the given user id not found");
			throw new NotFoundException("NOT FOUND");
		} else {
			logger.info("User With the given user id is retrieved");
			return userRepository.findById(userId);
		}

	}

	/**
	 * This method return {@link User} based on the passed userId
	 * 
	 * @param userId
	 * @return {@link User}
	 */
	@Override
	public User getUser(int userId) {
		logger.info("User With the given user id is retrieved");
		return userRepository.findById(userId).orElse(null);
	}

	/**
	 * This method return {@link User} based on the passed email and password
	 * 
	 * @param email
	 * @param password
	 * @return {@link User}
	 */
	@Override
	public User fetchUser(String email, String password) {
		logger.info("User With the given email & password is retrieved");
		return userRepository.findByEmailAndPassword(email, password);
	}

	/**
	 * This method return {@link Optional} of {@link User} based on the passed
	 * emailId of the User
	 * 
	 * @param email
	 * @return {@link Optional} of {@link User}
	 * @throws NotFoundException
	 * @throws Exception
	 */
	@Override
	public Optional<User> viewUserbyEmail(String email) throws NotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if (user == null) {
			logger.info("User With the given email id not found");
			throw new NotFoundException("NOT FOUND");
		} else {
			logger.info("User With the given email id is retrieved");
			return user;
		}
	}

	@Override
	@Transactional
	public User userLogin(User users) throws NotFoundException {
		String email = users.getEmail();
		String password = users.getPassword();
		User user = null;
		if (email != null && password != null) {
			logger.info("User With the given email & password is retrieved");
			user = fetchUser(email, password);
		}
		if (user == null) {
			logger.info("User With the given email & password not found");
			throw new NotFoundException("Bad Credentials");
		}
		return user;
	}

}
