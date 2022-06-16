package com.gzeinnumer.b5d8.data;

import com.gzeinnumer.b5d8.model.product.ProductBody;
import com.gzeinnumer.b5d8.model.product.ProductInsertResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequestDemo {

    @POST("product/insert")
    Call<ProductInsertResponse> sentProduct(@Body ProductBody body);
}
