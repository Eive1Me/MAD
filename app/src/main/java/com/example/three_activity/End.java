package com.example.three_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class End extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();
    ListView usersList;
    UserAdapter userAdapter;

    public void goMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
    public void add(View view){
        Intent intent = new Intent(this, Player.class);
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
        userAdapter = new UserAdapter(this,R.layout.list_item,users);
        usersList.setAdapter(userAdapter);
        AdapterView.OnItemClickListener itemListener = (parent, v, position, id) -> {
            // получаем выбранный пункт
            User selectedUser = (User) parent.getItemAtPosition(position);
            if(selectedUser!=null) {
                Intent intent = new Intent(getApplicationContext(), Player.class);
                intent.putExtra("id", selectedUser.getId());
                startActivity(intent);
            }
            Toast.makeText(getApplicationContext(), "Имя данного пользователя " + selectedUser.getName(),
                    Toast.LENGTH_SHORT).show();
        };
        usersList.setOnItemClickListener(itemListener);
    }

    private void setInitialData(){
//        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS users (id TEXT,name TEXT, city TEXT, gender INTEGER, score INTEGER, UNIQUE(name))");
//        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Том','',1,2), ('Джон', '',1,3), ('Донна','',0,4),('Марта','',0,4);");
//
//        query = db.rawQuery("SELECT * FROM users;",null);
//        while(query.moveToNext()){
//            String name = query.getString(0);
//            String city = query.getString(1);
//            int gender = query.getInt(2);
//            int score = query.getInt(3);
//            int pic = R.drawable.mal;
//            if (gender==0) pic = R.drawable.fem;
//            users.add(new User(name,pic,city,score));
//        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<User> users = adapter.getUsers();

        userAdapter = new UserAdapter(this,R.layout.list_item,users);
        usersList.setAdapter(userAdapter);
        adapter.close();
        Log.d(TAG, "onResume");
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
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
}