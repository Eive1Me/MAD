package com.example.three_activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Prof extends AppCompatActivity {

    public void goMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void open_text(){
        FileInputStream fin = null;
        try {
            EditText nameTV = findViewById(R.id.nameTV);
            fin = openFileInput("content.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            nameTV.setText(text);
        }
        catch(IOException ex) {
            Toast.makeText(this, "Пожалуйста, установите имя.", Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, "Пожалуйста, установите имя.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void save_text(View view){
        FileOutputStream fos = null;
        try {
            EditText nameTV = findViewById(R.id.nameTV);
            String text = nameTV.getText().toString();
            fos = openFileOutput("content.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void open_pic(){
        System.out.println("AHAHAHAHAHAHHAHAH");
        FileInputStream fin = null;
        try {
            ImageView iv = findViewById(R.id.imageView2);
            fin = openFileInput("image.png");
            Drawable drawable = Drawable.createFromStream(fin,"image.png");
            iv.setImageDrawable(drawable);
        }
        catch(IOException ex) {
            Toast.makeText(this, "Пожалуйста, установите имя.", Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, "Пожалуйста, установите имя.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void save_pic(){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("image.png", MODE_PRIVATE);
            ImageView iv = findViewById(R.id.imageView2);
            BitmapDrawable draw = (BitmapDrawable) iv.getDrawable();
            Bitmap bitmap = draw.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG,100, fos);
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void download_pic(View view){
        System.out.println("HAHAHAHH");
        save_pic();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ImageView selectedImagePreview = findViewById(R.id.imageView2);
                Uri selectedImageUri = data.getData();
                selectedImagePreview.setImageURI(selectedImageUri);
                save_pic();
            }
        }
    }

    private final static String TAG = "ProfActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        open_text();
        open_pic();
        findViewById(R.id.upload_pic).setOnClickListener(view -> {
            // in onCreate or any event where your want the user to
            // select a file
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
}
