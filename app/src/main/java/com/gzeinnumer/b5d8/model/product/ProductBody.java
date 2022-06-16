package com.gzeinnumer.b5d8.model.product;

import com.google.gson.annotations.SerializedName;

public class ProductBody{

	@SerializedName("volume")
	private String volume;

	@SerializedName("trans_date")
	private String transDate;

	@SerializedName("name")
	private String name;

	public String getVolume(){
		return volume;
	}

	public String getTransDate(){
		return transDate;
	}

	public String getName(){
		return name;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public void setName(String name) {
		this.name = name;
	}
}