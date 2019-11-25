package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class LessorWorkspaceDetailActivity extends AppCompatActivity {
    private TextView workspace_name;
    private TextView workspace_description;
    private TextView workspace_address;
    private TextView workspace_capacity;
    private TextView workspace_openingHour;
    private TextView workspace_contract_address;
    private TextView workspace_contract_minDuration;
    private TextView workspace_contract_fee;
    private TextView contract_address_label;
    private TextView contract_minDuration_label;
    private TextView contract_fee_label;
    private ImageView workspace_img;
    private LinearLayout workspace_amenities;
    private RecyclerView mRecyclerView;
    private WorkspaceImageAdapter adapter;
    private WorkSpace workspace;
    private ProgressBar workspace_contract_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_workspace_detail);
        workspace_name = findViewById(R.id.lessor_workspace_detail_name);
        workspace_description = findViewById(R.id.lessor_workspace_detail_description);
        workspace_address = findViewById(R.id.lessor_workspace_detail_address);
        workspace_capacity = findViewById(R.id.lessor_workspace_detail_capacity);
        workspace_openingHour = findViewById(R.id.lessor_workspace_detail_openingHour);
        workspace_contract_address = findViewById(R.id.lessor_contract_address);
        workspace_contract_minDuration = findViewById(R.id.lessor_contract_minDuration);
        workspace_contract_fee = findViewById(R.id.lessor_contract_fee);
        workspace_contract_progressBar = findViewById(R.id.lessor_contract_progressBar);
        contract_address_label = findViewById(R.id.contract_address_label);
        contract_minDuration_label = findViewById(R.id.minDuration_label);
        contract_fee_label = findViewById(R.id.contract_fee_label);
        workspace_img = findViewById(R.id.lessor_workspace_detail_img);
        workspace_amenities = findViewById(R.id.lessor_workspace_detail_amenities);
        mRecyclerView = findViewById(R.id.workspaceImg_list);
        Intent intent = getIntent();
        workspace = intent.getParcelableExtra(LessorMainActivity.MESSAGE3);
        if (workspace != null) {
            adapter = new WorkspaceImageAdapter(this, workspace.getImageList());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            workspace_name.setText(workspace.getName());
            workspace_description.setText(workspace.getDescription());
            workspace_address.setText(workspace.getAddress());
            workspace_capacity.setText(workspace.getCapacity() + " people");
            workspace_openingHour.setText(workspace.getOpening_hour());
            workspace_contract_address.setText(workspace.getContractAddress());
            Glide.with(this).load(workspace.getImageList().get(0)).into(workspace_img);
            for (String amenity : workspace.getAmenities()) {
                TextView textview = new TextView(this);
                textview.setTextAppearance(R.style.TextAppearance_AppCompat_Medium);
                textview.setText(amenity);
                workspace_amenities.addView(textview);
            }
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.isConnected()) {
                new ViewContract(workspace_contract_progressBar, workspace_contract_address, workspace_contract_minDuration, workspace_contract_fee
                        , contract_address_label, contract_minDuration_label, contract_fee_label)
                        .execute(workspace.getContractAddress());
            } else {
                Snackbar.make(findViewById(R.id.lessor_contract_layout), "Failed to connect Internet! Please check your Internet connection", Snackbar.LENGTH_LONG);
            }
        }
    }
}
