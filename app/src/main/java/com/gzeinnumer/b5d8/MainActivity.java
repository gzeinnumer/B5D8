package com.gzeinnumer.b5d8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.gzeinnumer.b5d8.data.RetroServer;
import com.gzeinnumer.b5d8.data.RetroServerDemo;
import com.gzeinnumer.b5d8.databinding.ActivityMainBinding;
import com.gzeinnumer.b5d8.model.product.ProductBody;
import com.gzeinnumer.b5d8.model.product.ProductInsertResponse;
import com.gzeinnumer.b5d8.model.users.UsersResponseItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<UsersResponseItem> list;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        retroV1();
        retroV2();
    }

    private void retroV1() {
        RetroServer.getInstance().getUsers().enqueue(new Callback<List<UsersResponseItem>>() {
            @Override
            public void onResponse(Call<List<UsersResponseItem>> call, Response<List<UsersResponseItem>> response) {
//                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                list = response.body();
                Collections.sort(list, new Comparator<UsersResponseItem>() {
                    @Override
                    public int compare(UsersResponseItem o1, UsersResponseItem o2) {
                        return o1.getEmail().toLowerCase().compareTo(o2.getEmail().toLowerCase());
                    }
                });
                if (list!=null){
                    //recyclerview stuff
                    //rv code
                    initRV();
                }
            }

            @Override
            public void onFailure(Call<List<UsersResponseItem>> call, Throwable t) {

            }
        });
    }

    private void retroV2() {
        ProductBody body =new ProductBody();
        body.setName("Data dari android");
        body.setTransDate("2022-04-01 22:25:00");
        body.setVolume("1111");

        RetroServerDemo.getInstance(getApplicationContext()).sentProduct(body).enqueue(new Callback<ProductInsertResponse>() {
            @Override
            public void onResponse(Call<ProductInsertResponse> call, Response<ProductInsertResponse> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response.body().getStatus()==1){
                    //pindahkan ke home
                    //simpan session
                } else {
                    Toast.makeText(getApplicationContext(), "User name password salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductInsertResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRV() {
        RvAdater adapter = new RvAdater();
        adapter.setList(list);
        binding.rv.setAdapter(adapter);
        binding.rv.hasFixedSize();
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}