package com.example.ssraza_foodbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

// Custom array adapter class to show multiple textviews in each listview cell
public class CustomArrayAdapter extends ArrayAdapter{
    private ArrayList<Food> foods;
    private Context context;

    public CustomArrayAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
        this.foods = foods;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.content_multiple, parent, false);
        }

        Food food = foods.get(position);

        TextView foodName = view.findViewById(R.id.foodText);
        TextView count = view.findViewById(R.id.countText);
        TextView unitCost = view.findViewById(R.id.unitCostText);

        foodName.setText(food.getDescription());
        count.setText("Count: " + String.valueOf(food.getCount()));
        unitCost.setText("Unit Cost: $" + String.valueOf(food.getUnitCost()) + "/unit");

        return view;
    }
}
