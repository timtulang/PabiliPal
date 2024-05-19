package com.example.uibasics;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <MenuItemModel> menuItemModels = new ArrayList<>();
    int[] menuItemImages = {R.drawable.bag_shopping_solid_1, R.drawable.truck_moving_solid_1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dbRecyclerView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.MenuView);


        setUpMenuItemModels();

        MI_RecyclerViewAdapter adapter = new MI_RecyclerViewAdapter(this, menuItemModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
    private void setUpMenuItemModels() {
        String[] menuItemNames = getResources().getStringArray(R.array.dashboard_text);
        String[] menuItemDesc = getResources().getStringArray(R.array.dahboard_description);

        for(int i = 0; i < menuItemNames.length; i++){
            menuItemModels.add(new MenuItemModel(menuItemNames[i], menuItemDesc[i], menuItemImages[i]));
        }
    }
}