package com.example.ssraza_foodbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Class is for handling the activity to view and edit foods, it also handles deleting foods
public class AddShowFoodActivity extends AppCompatActivity implements View.OnClickListener{
    TextView descriptionView;
    TextView countView;
    TextView unitCostView;
    TextView errorText;
    Spinner locationView;
    DatePicker bestBeforeDayView;
    Button finishButton;
    Button deleteButton;
    ArrayList<Food> dataList;
    Food selectedFood;
    int selectedFoodPosition;
    Map<String, Integer> bestBeforeDate;


    @Override
    public void onClick(View view) {
        if (view.getId() == finishButton.getId()){

            // Using integers to represent bestBeforeDate as that is the format for the
            // built in date picker for android
            int day = bestBeforeDayView.getDayOfMonth();
            int month = bestBeforeDayView.getMonth();
            int year =  bestBeforeDayView.getYear();

            // Storing date integers in a HashMap for easy access
            bestBeforeDate = new HashMap<>();
            bestBeforeDate.put("day", day);
            bestBeforeDate.put("month", month);
            bestBeforeDate.put("year", year);

            // If a food cannot be created then throw an error and display an error message
            // Otherwise, we create a food object, add it to dataList, and pass it back into
            // the main activity so that the array of dataList foods can be displayed.
            try {
                Food food = new Food(
                        descriptionView.getText().toString(),
                        bestBeforeDate,
                        locationView.getSelectedItem().toString(),
                        Integer.parseInt(countView.getText().toString()),
                        Integer.parseInt(unitCostView.getText().toString())
                );
                Intent intent = new Intent(AddShowFoodActivity.this, MainActivity.class);

                // If the food being created is not an existing food we simply add it to the list,
                // If it is existing, we add it in the appropriate position.
                if (selectedFood == null) {
                    dataList.add(food);
                } else {
                    dataList.set(selectedFoodPosition, food);
                }
                intent.putExtra("dataList", dataList);
                startActivity(intent);
            } catch (Exception e) {
                errorText.setVisibility(View.VISIBLE);
                errorText.setText("Please fill in all fields");
            }
        }

        if (view.getId() == deleteButton.getId()){

            // We don't need to delete an item if there are no existing items
            if (dataList.isEmpty()) {
                errorText.setVisibility(View.VISIBLE);
                errorText.setText("Cannot delete non-existing element");
            } else {

                // If the user is deleting an item that is not existing, we display a message
                try {
                    Intent intent = new Intent(AddShowFoodActivity.this, MainActivity.class);
                    for (Food food: dataList) {
                        String selectedFoodDescription = selectedFood.getDescription();
                        String foodDescription = food.getDescription();
                        if (selectedFoodDescription.toString().equals(foodDescription.toString())) {
                            dataList.remove(food);
                            break;
                        }
                    }
                    intent.putExtra("dataList", dataList);
                    startActivity(intent);
                } catch (Exception e) {
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Cannot delete non-existing element");
                }
            }
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
        deleteButton = findViewById(R.id.deleteFoodButton);
        finishButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        errorText = findViewById(R.id.errorText);

        errorText.setVisibility(View.GONE);

        // Populating the spinner to select foodLocation
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Pantry");
        spinnerArray.add("Freezer");
        spinnerArray.add("Fridge");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerItems = (Spinner) findViewById(R.id.editFoodLocation);
        spinnerItems.setAdapter(adapter);

        // Receiving the dataList and selectedFood from the main activity.
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            dataList = (ArrayList<Food>) intent.getSerializableExtra("dataList");
            selectedFood = (Food) intent.getSerializableExtra("selectedFood");
        } else {
            selectedFoodPosition = 0;
        }

        if (selectedFood != null) {
            descriptionView.setText(selectedFood.getDescription());
            countView.setText(String.valueOf(selectedFood.getCount()));
            bestBeforeDayView.updateDate(
                    selectedFood.getBestBeforeDate().get("year"),
                    selectedFood.getBestBeforeDate().get("month"),
                    selectedFood.getBestBeforeDate().get("day")
            );
            locationView.setSelection(1);
            unitCostView.setText(String.valueOf(selectedFood.getUnitCost()));

            // Determining which location in the location spinner needs to be shown
            int location = -1;
            String selectedFoodString = selectedFood.getLocation();
            if (selectedFoodString.toString().equals("Pantry")) {
                location = 0;
            } else if (selectedFoodString.toString().equals("Freezer")) {
                location = 1;
            } else if (selectedFoodString.toString().equals("Fridge")) {
                location = 2;
            }
            locationView.setSelection(location);

            // If a food is selected then we need to know where the edited food will need to be
            // re-added into the dataList, so we cal`cu`late selectedFoodPosition
            for (Food food: dataList) {
                String selectedFoodDescription = selectedFood.getDescription();
                String foodDescription = food.getDescription();
                if (selectedFoodDescription.toString().equals(foodDescription.toString())) {
                    break;
                }
                selectedFoodPosition += 1;
            }
        }
    }
}