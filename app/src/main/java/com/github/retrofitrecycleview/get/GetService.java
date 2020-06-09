package com.github.retrofitrecycleview.get;

import com.github.retrofitrecycleview.User;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    @GET("/GitHub/Retrofit/get.php/")
    Call<ArrayList<User>> getList();
}
