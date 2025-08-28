package com.example.e_commerce.DTO;

public class UserDTO {
	private Integer id;
	private String username;
	private String email;
	
	
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(Integer id2, String username2, String email2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.username = username2;
		this.email = email2;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + "]";
	}
	
	
	
}
