package com.github.retrofitrecycleview.post_get;

import com.github.retrofitrecycleview.User;

import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostGetService {

    @FormUrlEncoded
    @POST("GitHub/Retrofit/post.php")
    Call<ResponseBody> loginRequest(@Field("name") String name,
                                    @Field("stats") String stats,
                                    @Field("namaPicture") String namaPicture,
                                    @Field("picture") String picture);
}
