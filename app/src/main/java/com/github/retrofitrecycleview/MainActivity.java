package com.github.retrofitrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.retrofitrecycleview.get.RetrofitGet;
import com.github.retrofitrecycleview.post.PostService;
import com.github.retrofitrecycleview.post.RetrofitPost;
import com.github.retrofitrecycleview.post_get.RetrofitPostAndGet;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    RetrofitGet retrofitGet;
    RetrofitPost retrofitPost;
    RetrofitPostAndGet retrofitPostAndGet;
    EditText edName, edStats;
    String name , stats , namaPicture;
    ImageView imgUserPicture;
    private static final int PICK_IMAGE_REQUEST = 1;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);

        Button get = (Button) findViewById(R.id.get);
        get.setOnClickListener(this);

        Button post = (Button) findViewById(R.id.post);
        post.setOnClickListener(this);

        Button postandget = (Button) findViewById(R.id.postandget);
        postandget.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        retrofitGet = new RetrofitGet(this);

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    public void showBottomSheetDialog(final Context context) {
        View view = getLayoutInflater().inflate(R.layout.bottom_post, null);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);

        edName = view.findViewById(R.id.name);
        edStats = view.findViewById(R.id.stats);
        imgUserPicture = view.findViewById(R.id.userPicture);

        Button send = view.findViewById(R.id.send);

        imgUserPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                stats = edStats.getText().toString();

                retrofitPost = new RetrofitPost(MainActivity.this);
                String picture = getStringImage(bitmap);
                retrofitPost.RetrofitRequest(context, name, stats, namaPicture, picture);
                edName.setText("");
                edStats.setText("");

            }
        });

        dialog.show();
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == MainActivity.RESULT_OK && data != null && data.getData() != null) {

            Uri filePath = data.getData();
            try {
                String getImageRequestName = String.valueOf(data); // Nama Default
                String compressImage = (getImageRequestName.substring(getImageRequestName.lastIndexOf("/") + 1)); // Ambil Setelah Slash ('/')
                compressImage = (compressImage.substring(0,compressImage.lastIndexOf("."))); // Ambil Sebelum Titik ('.')
                namaPicture = compressImage;
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                imgUserPicture.setImageBitmap(bitmap);
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showBottomSheetDialogLast(final Context context) {
        View view = getLayoutInflater().inflate(R.layout.bottom_post, null);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);

        edName = view.findViewById(R.id.name);
        edStats = view.findViewById(R.id.stats);
        imgUserPicture = view.findViewById(R.id.userPicture);

        Button send = view.findViewById(R.id.send);

        imgUserPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                stats = edStats.getText().toString();

                retrofitPostAndGet = new RetrofitPostAndGet(MainActivity.this);
                String picture = getStringImage(bitmap);
                retrofitPostAndGet.requestLogin(context, name, stats, namaPicture, picture);
                edName.setText("");
                edStats.setText("");

            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.get:
                retrofitGet.RetrofitRequest(this);
                break;

            case R.id.post:
                showBottomSheetDialog(this);
                break;

            case R.id.postandget:
                showBottomSheetDialogLast(this);
                break;

            default:
                break;
        }
    }
}