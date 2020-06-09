package com.github.retrofitrecycleview.post;

import android.graphics.Bitmap;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostService {
    @POST("GitHub/Retrofit/post.php")
    @FormUrlEncoded
    Call<ArrayList<ResponseBody>> savePost(@Field("name") String name,
                                           @Field("stats") String stats,
                                           @Field("namaPicture") String namaPicture,
                                           @Field("picture") String picture);
}
