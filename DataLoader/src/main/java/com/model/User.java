package com.model;

public class User {
	private Integer sso;
	private String firstName;
	private String lastName;
	private String role;
	
	public User() {
		super();
	}
	
	public User(Integer sso, String firstName, String lastName, String role) {
		super();
		this.sso = sso;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	public Integer getSso() {
		return sso;
	}
	public void setSso(Integer sso) {
		this.sso = sso;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [sso=" + sso + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}
	
	
}
