package com.example.ssraza_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddShowFood extends AppCompatActivity implements View.OnClickListener{
    TextView descriptionView;
    TextView countView;
    TextView unitCostView;
    Spinner locationView;
    DatePicker bestBeforeDayView;
    Button finishButton;
    ArrayList dataList;


    @Override
    public void onClick(View view) {
        if (view.getId() == finishButton.getId()){

            Food food = new Food(descriptionView.getText().toString(), Integer.parseInt(countView.getText().toString()));
            Intent intent = new Intent(AddShowFood.this, MainActivity.class);
            dataList.add(food);
            Log.d("Food", food.getDescription());
            intent.putExtra("key", dataList);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_show_food);

        descriptionView = findViewById(R.id.editFoodDescription);
        countView = findViewById(R.id.editFoodCount);
        unitCostView = findViewById(R.id.editFoodUnitCost);
        locationView = findViewById(R.id.editFoodLocation);
        bestBeforeDayView = findViewById(R.id.editFoodDay);
        finishButton = findViewById(R.id.finishFoodButton);
        finishButton.setOnClickListener(this);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Pantry");
        spinnerArray.add("Freezer");
        spinnerArray.add("Fridge");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.editFoodLocation);
        sItems.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            dataList = intent.getParcelableArrayListExtra("dataList");
        }
    }
}