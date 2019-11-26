package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewWorkSpaceActivity extends AppCompatActivity {
    private WorkSpace workspace;
    private TextView workspace_name;
    private TextView workspace_description;
    private TextView workspace_address;
    private TextView workspace_capacity;
    private TextView workspace_openingHour;
    private TextView workspace_period;
    private ImageView workspace_img;
    private LinearLayout workspace_amenities;
    private RecyclerView mRecyclerView;
    private WorkspaceImageAdapter adapter;
    private Intent intent;
    public static final String MESSAGE5 = "com.example.android.forwork.MESSAGE5";
    public static final String MESSAGE6 = "com.example.android.forwork.MESSAGE6";
    public static final String MESSAGE7 = "com.example.android.forwork.MESSAGE7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work_space);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        intent = getIntent();
        workspace = intent.getParcelableExtra(WorkSpaceAdapter.ViewHolder.MESSAGE4);
        workspace_name = findViewById(R.id.view_workspace_name);
        workspace_description = findViewById(R.id.view_workspace_description);
        workspace_address = findViewById(R.id.view_address);
        workspace_capacity = findViewById(R.id.view_capacity);
        workspace_openingHour = findViewById(R.id.view_opening_hour);
        workspace_img = findViewById(R.id.view_workspace_img);
        workspace_amenities = findViewById(R.id.view_amenities_list);
        workspace_period = findViewById(R.id.view_period);
        mRecyclerView = findViewById(R.id.view_img_list);
        if (savedInstanceState != null) {
            workspace = savedInstanceState.getParcelable("reply_visible");
            Log.d("TAG", "ok");
        } else {
            Log.d("TAG", "haha");
        }
        if (workspace != null) {
            adapter = new WorkspaceImageAdapter(this, workspace.getImageList());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            workspace_name.setText(workspace.getName());
            workspace_description.setText(workspace.getDescription());
            workspace_address.setText(workspace.getAddress());
            workspace_capacity.setText(workspace.getCapacity() + " people");
            workspace_openingHour.setText(workspace.getOpening_hour());
            workspace_period.setText(workspace.getPeriod());
            Glide.with(this).load(workspace.getImageList().get(0)).into(workspace_img);
            for (String amenity : workspace.getAmenities()) {
                TextView textview = new TextView(this);
                textview.setTextAppearance(R.style.TextAppearance_AppCompat_Medium);
                textview.setText(amenity);
                workspace_amenities.addView(textview);
            }
        }
        Log.d("TAG", "oktt");
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
        if (workspace == null)
            Log.d("TAG", "Empty");
        Log.d("TAG", workspace_name.getText().toString());
        intent.putExtra(MESSAGE5, workspace.getContractAddress());
        intent.putExtra(MESSAGE6, workspace.getName());
        intent.putExtra(MESSAGE7, workspace.getId());
        Log.d("TAG", workspace.getContractAddress() + " " + workspace.getName() + " " + workspace.getId());
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("reply_visible", workspace);
        Log.d("TAG", "what");
    }
}
