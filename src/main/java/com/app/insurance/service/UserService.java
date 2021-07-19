package com.app.insurance.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.app.insurance.model.User;

import javassist.NotFoundException;
/**
 * @author jpaste
 * This interface contains abstract methods of {@link User}
 *
 */
public interface UserService {

	public List<User> getAllUsers() throws NotFoundException;

	public User addUser(User user);

	public User updateUser(User user);

	public void deleteUser(int userId) throws NotFoundException;

	public Optional<User> viewUser(int userId) throws NotFoundException;

	public User getUser(int userId);

	public User fetchUser(String username, String password);

	public Optional<User> viewUserbyEmail(String email) throws NotFoundException;

	public User userLogin(User users) throws NotFoundException;

	public String login(User loginRequest);

	public String userLogin(User loginRequest, HttpServletResponse response) throws NotFoundException;

	

}
