package com.example.forwork;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    final Context context = this;
    private RecyclerView mRecyclerView;
    private LocationListAdapter mAdapter;
    private ImageView user_profile_img;
    private TextView user_name;
    private TextView user_email;
    private final LinkedList<String> locationList = new LinkedList<>();
    private RecyclerView workspace_list_daily;
    private RecyclerView workspace_list_weekly;
    private RecyclerView workspace_list_monthly;
    private ArrayList<WorkSpace> workSpaceData;
    private WorkSpaceAdapter workSpaceAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        user_profile_img = headerView.findViewById(R.id.profile_img);
        user_name = headerView.findViewById(R.id.user_name);
        user_email = headerView.findViewById(R.id.user_email);
        mDrawerLayout = findViewById(R.id.drawer);
        mAuth = FirebaseAuth.getInstance();
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Intent intent = null;
                        // Set item in checked state
                        menuItem.setChecked(true);
                        switch(menuItem.getItemId()){
                            case R.id.nav_my_contract:
                                intent = new Intent(context, MyContractsActivity.class);
                                break;

                            case R.id.nav_create_contract:
                                intent = new Intent(context, AddWorkspaceActivity.class);
                                break;

                            case R.id.nav_setting:
                                intent = new Intent(context, LessorContractActivity.class);
                                break;

                            case R.id.nav_feedback:
                                intent = new Intent(context, FeedbackActivity.class);
                                break;

                            case R.id.nav_logout:
                                intent = new Intent(context, SignInActivity.class);
                                FirebaseAuth.getInstance().signOut();
                                break;
                        }
                        startActivity(intent);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        String locationListStr = getString(R.string.locationlist_string);
        for (String locationItem : locationListStr.split(",")) {
            locationList.addLast(locationItem);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        workspace_list_daily = findViewById(R.id.workspace_list_daily);
        workspace_list_monthly = findViewById(R.id.workspace_list_monthly);
        workspace_list_weekly = findViewById(R.id.workspace_list_weekly);
        mAdapter = new LocationListAdapter(this, locationList);
        workSpaceData = new ArrayList<>();
        workSpaceAdapter = new WorkSpaceAdapter(this, workSpaceData);
        mRecyclerView.setAdapter(mAdapter);
        workspace_list_daily.setAdapter(workSpaceAdapter);
        workspace_list_weekly.setAdapter(workSpaceAdapter);
        workspace_list_monthly.setAdapter(workSpaceAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        workspace_list_daily.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        workspace_list_weekly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        workspace_list_monthly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        initializeWorkspaceData();
    }

    public void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        } else {
            if (currentUser.getPhotoUrl() != null) {
                Glide.with(this).load(currentUser.getPhotoUrl().toString()).into(user_profile_img);
            }
            user_name.setText(currentUser.getDisplayName());
            user_email.setText(currentUser.getEmail());
        }
    }

    private void initializeWorkspaceData() {
        String[] workspaceList = getResources().getStringArray(R.array.workspace_names);
        String[] workspaceAddress = getResources().getStringArray(R.array.workspace_address);
        TypedArray workspaceImg = getResources().obtainTypedArray(R.array.workspace_images);
        workSpaceData.clear();
        for (int i = 0; i < workspaceList.length; i++) {
            workSpaceData.add(new WorkSpace(workspaceList[i], workspaceAddress[i], workspaceImg.getResourceId(i, 0)));
        }
        workspaceImg.recycle();
        workSpaceAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            item.collapseActionView();
            return true;
        } else if(id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}
