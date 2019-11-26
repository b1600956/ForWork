package com.example.forwork;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

public class LessorMainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    final Context context = this;
    private RecyclerView mRecyclerView;
    private ImageView user_profile_img;
    private TextView user_name;
    private TextView user_email;
    private TextView noWorkspace;
    private Button addWorkSpaceBtn;
    private RelativeLayout userWorkspace;
    private TextView workSpaceName;
    private TextView workSpaceAddress;
    private TextView workSpaceStatus;
    private ImageView workSpaceImg;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private String contractAddress;
    private static Web3j web3;
    private DatabaseReference database;
    private FirebaseUser user;
    public static final String MESSAGE3 = "com.example.android.forwork.MESSAGE3";
    private WorkSpace workspace;

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
        noWorkspace = findViewById(R.id.noWorkSpaceTxt);
        addWorkSpaceBtn = findViewById(R.id.addCoworkspaceBtn);
        workSpaceName = findViewById(R.id.workSpace_name);
        workSpaceAddress = findViewById(R.id.workSpace_address);
        workSpaceStatus = findViewById(R.id.workSpace_status);
        workSpaceImg = findViewById(R.id.workSpace_img);
        userWorkspace = findViewById(R.id.user_WorkSpace);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
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
                        switch (menuItem.getItemId()) {
                            case R.id.nav_create_contract:
                                intent = new Intent(context, AddWorkspaceActivity.class);
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

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String coworkspaceId = dataSnapshot.child("user/" + user.getUid() + "/coworkspace").getValue(String.class);
                if (coworkspaceId == null) {
                    userWorkspace.setVisibility(View.INVISIBLE);
                    noWorkspace.setVisibility(View.VISIBLE);
                    noWorkspace.setText("Currently, you do not have any co-workspace yet. Please add one to view your workspace");
                    addWorkSpaceBtn.setVisibility(View.VISIBLE);
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
        startActivity(new Intent(this, AddWorkspaceActivity.class));
        finish();
    }

    public void viewWorkspaceDetail(View view) {
        startActivity(new Intent(this, LessorWorkspaceDetailActivity.class).putExtra(MESSAGE3, (Parcelable) workspace));
    }
}
