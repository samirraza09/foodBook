package com.example.ssraza_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView foodList;
    ArrayAdapter<String> foodAdapter;
    ArrayList<String> dataList;
    Button addFoodButton;



    @Override
    public void onClick(View view) {
        if (view.getId() == addFoodButton.getId()){
            Intent intent = new Intent(this, AddShowFood.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sessionId = getIntent().getStringExtra("key");
        foodList = findViewById(R.id.foodListView);
        dataList = new ArrayList<>();
        dataList.add(sessionId);
        foodAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        // foodList.setAdapter(foodAdapter);

        addFoodButton = findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(this);
    }
}