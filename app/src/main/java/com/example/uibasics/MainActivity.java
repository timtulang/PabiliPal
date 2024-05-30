package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuRecyclerInterface{
    ArrayList <MenuItemModel> menuItemModels = new ArrayList<>();
    int[] menuItemImages = {R.drawable.bag_shopping_solid_1, R.drawable.truck_moving_solid_1, R.drawable.usercreate, R.drawable.admincreate};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "MainActivity Launched", Toast.LENGTH_SHORT).show();
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

        MI_RecyclerViewAdapter adapter = new MI_RecyclerViewAdapter(this, menuItemModels, this);

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

    @Override
    public void onMenuItemClick(int position) {
        changeActivity(position);
    }

    private void changeActivity(int position){
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, ProductCart.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, Inventory.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, SignUpActivityUser.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, SignUpActivityAdmin.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}

// TODO: Navigation Buttons (Back, and X[Close] ) For Chris and Alexi
// TODO: Implement and Fix Cart System For Pierre and JM