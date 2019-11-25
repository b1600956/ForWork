package com.example.forwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyContractsActivity extends AppCompatActivity {
    private TextView noWorkspace;
    private RelativeLayout userWorkspace;
    private TextView workSpaceName;
    private TextView workSpaceAddress;
    private TextView workSpaceStatus;
    private ImageView workSpaceImg;
    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseUser user;
    private WorkSpace workspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contracts);
        //ActionBar supportActionBar = getSupportActionBar();
        noWorkspace = findViewById(R.id.noWorkSpace);
        workSpaceName = findViewById(R.id.myWorkSpace_name);
        workSpaceAddress = findViewById(R.id.myWorkSpace_address);
        workSpaceStatus = findViewById(R.id.myWorkSpace_status);
        workSpaceImg = findViewById(R.id.myWorkSpace_img);
        userWorkspace = findViewById(R.id.my_WorkSpace);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String coworkspaceId = dataSnapshot.child("user/" + user.getUid() + "/coworkspace").getValue(String.class);
                if (coworkspaceId == null) {
                    userWorkspace.setVisibility(View.INVISIBLE);
                    noWorkspace.setVisibility(View.VISIBLE);
                } else {
                    workspace = dataSnapshot.child("Co-Workspace/" + coworkspaceId).getValue(WorkSpace.class);
                    workSpaceName.setText(workspace.getName());
                    workSpaceAddress.setText(workspace.getAddress());
                    workSpaceStatus.setText(workspace.getStatus());
                    Glide.with(getApplicationContext()).load(workspace.getImageList().get(0)).into(workSpaceImg);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void viewWorkspace(View view) {
        startActivity(new Intent(this, ViewWorkSpaceActivity.class).putExtra(WorkSpaceAdapter.ViewHolder.MESSAGE4, workspace));
    }
/**
 @Override public boolean onCreateOptionsMenu(Menu menu) {
 // Inflate the menu; this adds items to the action bar if it is present.
 getMenuInflater().inflate(R.menu.menu_main, menu);
 MenuItem searchItem = menu.findItem(R.id.action_search);
 // Get the SearchView and set the searchable configuration
 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
 SearchView menu_searchView = (SearchView) searchItem.getActionView();
 // Assumes current activity is the searchable activity
 menu_searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
 return true;
 }
 */
}
