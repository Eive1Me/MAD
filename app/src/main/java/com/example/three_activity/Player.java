package com.example.three_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Player extends AppCompatActivity {

    private DatabaseAdapter adapter;
    private long userId=0;

    private EditText nameBox;
    private EditText cityBox;
    private RadioGroup genderBox;
    private Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        nameBox = findViewById(R.id.name);
        cityBox = findViewById(R.id.city);
        genderBox = findViewById(R.id.radioGroup);
        delButton = findViewById(R.id.deleteButton);

        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            User user = adapter.getUser(userId);
            nameBox.setText(user.getName());
            cityBox.setText(String.valueOf(user.getCity()));
            if (user.getPicResource() == R.drawable.mal)
            genderBox.check(R.id.radioButton); else
            genderBox.check(R.id.radioButton2);
            adapter.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        String name = nameBox.getText().toString();
        String city = cityBox.getText().toString();
        int pic = R.drawable.mal;
        RadioButton female = findViewById(R.id.radioButton2);
        if (female.isChecked()) pic = R.drawable.fem;
        User user = new User(userId, name, city, pic,0);

        adapter.open();
        if (userId > 0) {
            adapter.update(user);
        } else {
            adapter.insert(user);
        }
        adapter.close();
        goHome();
    }

    public void delete(View view){
        adapter.open();
        adapter.delete(userId);
        adapter.close();
        goHome();
    }

    private void goHome(){
        // переход к главной activity
        Intent intent = new Intent(this, End.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}