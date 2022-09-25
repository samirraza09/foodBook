package com.example.ssraza_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView foodList;
    ArrayAdapter<Food> foodAdapter;
    ArrayList<Food> dataList;
    Button addFoodButton;
    TextView totalCostText;
    int totalCost;

    @Override
    public void onClick(View view) {

        // Passing current dataList to the class to AddShowFood
        if (view.getId() == addFoodButton.getId()){
            Intent intent = new Intent(this, AddShowFoodActivity.class);
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

        // Checking if there are any existing items that have been created
        // If so, popularize the arraylist with foods
        if (intent.getExtras() != null) {
            dataList = (ArrayList<Food>) intent.getSerializableExtra("dataList");
            foodAdapter = new CustomArrayAdapter(this, dataList);
            foodList.setAdapter(foodAdapter);
        }

        addFoodButton = findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(this);

        totalCostText = findViewById(R.id.totalCostText);

        // If there are items in the food list then we can calculate the
        // total food cost and display it
        if (!dataList.isEmpty()) {
            for (Food food: dataList) {
                totalCost += food.getCount() * food.getUnitCost();
            }

            totalCostText.setText("Total Food Cost: $" + String.valueOf(totalCost));
        } else {
            totalCostText.setVisibility(TextView.GONE);
        }

        // When an item is selected, send the food and the current dataList to
        // the addShowFoodClass
        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food selectedFood = (Food) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, AddShowFoodActivity.class);
                intent.putExtra("dataList", dataList);
                intent.putExtra("selectedFood", selectedFood);
                startActivity(intent);
            }
        });
    }
}