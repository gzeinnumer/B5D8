package com.gzeinnumer.b5d8.model.users;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class UsersResponseItem{

	@SerializedName("website")
	private String website;

	@SerializedName("address")
	private Address address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("company")
	private Company company;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public String getWebsite(){
		return website;
	}

	public Address getAddress(){
		return address;
	}

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public Company getCompany(){
		return company;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	@Override
	public String toString() {
	    return new GsonBuilder().setPrettyPrinting().create().toJson(this, UsersResponseItem.class);
	}
}