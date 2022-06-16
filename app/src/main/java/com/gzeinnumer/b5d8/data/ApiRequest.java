package com.gzeinnumer.b5d8.data;

import com.gzeinnumer.b5d8.model.users.UsersResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    //https://jsonplaceholder.typicode.com/users
    @GET("users")
    Call<List<UsersResponseItem>> getUsers();
}
