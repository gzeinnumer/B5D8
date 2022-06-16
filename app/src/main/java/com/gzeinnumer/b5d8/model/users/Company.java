package com.gzeinnumer.b5d8.model.users;

import com.google.gson.annotations.SerializedName;

public class Company{

	@SerializedName("bs")
	private String bs;

	@SerializedName("catchPhrase")
	private String catchPhrase;

	@SerializedName("name")
	private String name;

	public String getBs(){
		return bs;
	}

	public String getCatchPhrase(){
		return catchPhrase;
	}

	public String getName(){
		return name;
	}
}