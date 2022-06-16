package com.gzeinnumer.b5d8.model.product;

import com.google.gson.annotations.SerializedName;

public class ProductInsertResponse{

	@SerializedName("title")
	private String title;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public String getTitle(){
		return title;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}
}