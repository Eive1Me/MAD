package com.example.three_activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
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

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void open_pic(View view){

    }

    public void download_pic(View view){

    }

    private final static String TAG = "ProfActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        open_text();
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
