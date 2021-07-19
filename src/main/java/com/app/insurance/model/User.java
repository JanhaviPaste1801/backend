package com.app.insurance.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @author jpaste This class includes declaration of parameters of user class,
 *         default constructor, parameterized constructors, getter and setter
 *         methods of parameters and toString method to display.
 */
@Entity
@Table(name = "Users", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "phone_number"), @UniqueConstraint(columnNames = "password") })
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNo;

	@Column(name = "city")
	private String city;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles", 
//				joinColumns = @JoinColumn(name = "userId"), 
//				inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> role = new HashSet<>();
	private String role;

	@Column(name = "status")
	private String status;

	public User() {
	}

	public User(int userId, String firstName, String lastName, String email, String phoneNo, String city,
			String password, String role, String status) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.city = city;
		this.password = password;
		this.role = role;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
