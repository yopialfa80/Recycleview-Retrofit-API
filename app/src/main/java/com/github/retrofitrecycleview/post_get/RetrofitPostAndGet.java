package com.github.retrofitrecycleview.post_get;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.github.retrofitrecycleview.APIClient;
import com.github.retrofitrecycleview.get.RetrofitGet;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitPostAndGet {
    public Activity activity;
    public RetrofitPostAndGet(Activity activity){
        this.activity = activity;
    }

    public void requestLogin(final Context context, String name, String stats, String namaPicture, String picture){
        PostGetService postService = APIClient.getRetrofitInstance().create(PostGetService.class);
        postService.loginRequest(name, stats, namaPicture, picture)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            RetrofitGet retrofitGet = new RetrofitGet(activity);
                            retrofitGet.RetrofitRequest(activity);

                        }else {
                            Toast.makeText(context, "GAGAL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
