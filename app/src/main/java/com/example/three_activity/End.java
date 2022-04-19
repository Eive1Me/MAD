package com.example.three_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class End extends AppCompatActivity {

    public void goMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    private final static String TAG = "WinActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Bundle arguments = getIntent().getExtras();
        String result = arguments.get("outcome").toString();

        TextView outcomeTV = findViewById(R.id.outcomeTV);
        outcomeTV.setText(result);

        ImageView imageView = findViewById(R.id.imageView);
        if (result.equals("Победа"))
            { imageView.setImageResource(R.drawable.hehe); }
        else
            { imageView.setImageResource(R.drawable.nothehe);
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);  //0 means grayscale
                ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
                imageView.setColorFilter(cf);
                }
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