package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<WorkSpace> workSpaceData;
    private ResultAdapter resultAdapter;
    private DatabaseReference workSpaceDatabase;
    private TextView noResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        workSpaceDatabase = FirebaseDatabase.getInstance().getReference("Co-Workspace");
        mRecyclerView = findViewById(R.id.search_result);
        noResult = findViewById(R.id.noResult);
        workSpaceData = new ArrayList<>();
        resultAdapter = new ResultAdapter(this, workSpaceData);
        mRecyclerView.setAdapter(resultAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String location = intent.getStringExtra(LocationListAdapter.LocationViewHolder.MESSAGE8);
        String workspaceName = intent.getStringExtra(MainActivity.MESSAGE9);
        workSpaceDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    WorkSpace workspace = postSnapshot.getValue(WorkSpace.class);
                    if (workspace != null && workspace.getStatus().equalsIgnoreCase("Available")) {
                        if (location != null && workspace.getLocation().equalsIgnoreCase(location)) {
                            workSpaceData.add(workspace);
                        } else if (workspaceName != null && workspace.getName().toLowerCase().contains(workspaceName.toLowerCase())) {
                            workSpaceData.add(workspace);
                        }
                    }
                }
                resultAdapter.notifyDataSetChanged();
                if (workSpaceData.size() == 0) {
                    noResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
