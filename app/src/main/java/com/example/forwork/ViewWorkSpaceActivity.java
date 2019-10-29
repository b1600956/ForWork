package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewWorkSpaceActivity extends AppCompatActivity {
    private ListView amenitiesListView;
    private String[] amenitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work_space);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        amenitiesListView = findViewById(R.id.view_amenities_list);
        amenitiesList = getResources().getStringArray(R.array.amenitiesList);
        amenitiesListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, amenitiesList));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewContract(View view) {
        Intent intent = new Intent(this, ViewContractActivity.class);
        startActivity(intent);
    }
}
