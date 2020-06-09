package com.github.retrofitrecycleview.post;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.Toast;
import com.github.retrofitrecycleview.APIClient;
import com.github.retrofitrecycleview.R;
import com.github.retrofitrecycleview.User;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitPost {
    Activity activity;
    public RetrofitPost(Activity activity) {
        this.activity = activity;
    }

    public void RetrofitRequest(final Context context, String name, String stats, String namaPicture, String picture) {
        PostService postService = APIClient.getRetrofitInstance().create(PostService.class);
        postService.savePost(name, stats, namaPicture, picture).enqueue(new Callback<ArrayList<ResponseBody>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseBody>> call, Response<ArrayList<ResponseBody>> response) {
                if (response.isSuccessful()) {

                    showResponse(response.body().toString());
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseBody>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showResponse(String response) {
        Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();

    }
}

