package com.example.forwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LessorMainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    final Context context = this;
    private RecyclerView mRecyclerView;
    private ImageView user_profile_img;
    private TextView user_name;
    private TextView user_email;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.lessor_nav_view);
        View headerView = navigationView.getHeaderView(0);
        user_profile_img = headerView.findViewById(R.id.profile_img);
        user_name = headerView.findViewById(R.id.user_name);
        user_email = headerView.findViewById(R.id.user_email);
        mDrawerLayout = findViewById(R.id.lessor_drawer);
        navigationView.getMenu().findItem(R.id.nav_my_contract).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_create_contract).setVisible(true);
        mAuth = FirebaseAuth.getInstance();
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
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
                        switch (menuItem.getItemId()) {
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
                                mGoogleSignInClient.signOut();
                                break;
                        }
                        startActivity(intent);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getPhotoUrl() != null) {
                Glide.with(this).load(currentUser.getPhotoUrl().toString()).into(user_profile_img);
            }
            user_name.setText(currentUser.getDisplayName());
            user_email.setText(currentUser.getEmail());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addWorkSpace(View view) {
    }
}
