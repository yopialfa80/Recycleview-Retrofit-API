package com.github.retrofitrecycleview.get;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.github.retrofitrecycleview.APIClient;
import com.github.retrofitrecycleview.R;
import com.github.retrofitrecycleview.RecycleAdapter;
import com.github.retrofitrecycleview.User;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitGet {
    public Activity activity;
    public RecyclerView recyclerView;
    public RecycleAdapter adapter;
    public ArrayList<User> listUser;

    public RetrofitGet(Activity activity){
        this.activity = activity;
        recyclerView = this.activity.findViewById(R.id.recycleview);
    }

    public void RetrofitRequest(final Context context){
        listUser = new ArrayList<>();
        adapter = new RecycleAdapter(context, listUser);

        GetService service = APIClient.getRetrofitInstance().create(GetService.class);
        Call<ArrayList<User>> call = service.getList();
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                    if (response.isSuccessful()) {

                        adapter.updateAnswers(response.body());
                        Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                } else {
                        int statusCode  = response.code();
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
