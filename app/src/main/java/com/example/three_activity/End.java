package com.example.three_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class End extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();
    ListView usersList;

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

        setInitialData();

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

        usersList = findViewById(R.id.usersList);
        UserAdapter userAdapter = new UserAdapter(this,R.layout.list_item,users);
        usersList.setAdapter(userAdapter);
        AdapterView.OnItemClickListener itemListener = (parent, v, position, id) -> {

            // получаем выбранный пункт
            User selectedUser = (User) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), "Имя данного пользователя " + selectedUser.getName(),
                    Toast.LENGTH_SHORT).show();
        };
        usersList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
        users.add(new User ("Данил", R.drawable.mal));
        users.add(new User ("Илья", R.drawable.mal));
        users.add(new User ("Алёна", R.drawable.fem));
        users.add(new User ("Денис", R.drawable.mal));
        users.add(new User ("Настя", R.drawable.fem));
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