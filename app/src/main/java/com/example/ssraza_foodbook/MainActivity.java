package com.example.ssraza_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView foodList;
    ArrayAdapter<String> foodAdapter;
    ArrayList<Food> dataList;
    Button addFoodButton;

    @Override
    public void onClick(View view) {
        if (view.getId() == addFoodButton.getId()){
            Intent intent = new Intent(this, AddShowFood.class);
            intent.putExtra("dataList", dataList);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodList = findViewById(R.id.foodListView);
        dataList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            dataList = (ArrayList<Food>) intent.getSerializableExtra("key");
            ArrayList<String> dataListString = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                dataListString.add(dataList.get(i).getDescription());
            }

            foodAdapter = new ArrayAdapter<>(this, R.layout.content, dataListString);
            foodList.setAdapter(foodAdapter);
        }

        addFoodButton = findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(this);
    }
}