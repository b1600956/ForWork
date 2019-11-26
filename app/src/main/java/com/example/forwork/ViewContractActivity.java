package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.math.BigInteger;

public class ViewContractActivity extends AppCompatActivity {
    private TextView contract_address;
    private TextView contract_minDuration;
    private TextView contract_fee;
    private TextView contract_address_label;
    private TextView contract_minDuration_label;
    private TextView contract_fee_label;
    private TextView workspaceName;
    private ProgressBar contract_progressBar;
    private ProgressBar signContract_progressBar;
    private Button signContract_btn;
    private WorkSpace workspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contract);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        workspace = intent.getParcelableExtra(ViewWorkSpaceActivity.MESSAGE5);
        this.workspaceName = findViewById(R.id.view_workspaceName);
        contract_address = findViewById(R.id.view_contract_address);
        contract_minDuration = findViewById(R.id.view_contract_minDuration);
        contract_fee = findViewById(R.id.view_contract_fee);
        contract_address_label = findViewById(R.id.view_contract_address_label);
        contract_minDuration_label = findViewById(R.id.view_minDuration_label);
        contract_fee_label = findViewById(R.id.view_fee_label);
        contract_progressBar = findViewById(R.id.contract_progressBar);
        signContract_progressBar = findViewById(R.id.signContract_progressBar);
        signContract_btn = findViewById(R.id.signContract_btn);
        contract_address.setText(workspace.getContractAddress());
        this.workspaceName.setText(workspace.getName());
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            new ViewContract(contract_progressBar, contract_address, contract_minDuration, contract_fee
                    , contract_address_label, contract_minDuration_label, contract_fee_label)
                    .execute(workspace.getContractAddress());
        } else {
            Snackbar.make(findViewById(R.id.lessor_contract_layout), "Failed to connect Internet! Please check your Internet connection", Snackbar.LENGTH_LONG);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, ViewWorkSpaceActivity.class).putExtra(WorkSpaceAdapter.ViewHolder.MESSAGE4, workspace));
            Log.d("TAG","wwwww");
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void signContract(View view) {
        EnterPrivateKeyFragment enterPrivateKeyFragment = new EnterPrivateKeyFragment();
        enterPrivateKeyFragment.show(getSupportFragmentManager(), getString(R.string.enter_private_key));
    }

    public void getPrivateKey(String privateKey) {
        if (privateKey != null && !(privateKey.isEmpty())) {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.isConnected()) {
                Log.d("TAG", privateKey);
                new SignContract(signContract_progressBar, Snackbar.make(findViewById(R.id.viewContractLayout), "", Snackbar.LENGTH_LONG), signContract_btn)
                        .execute(privateKey, workspace.getContractAddress(), contract_fee.getText().toString(), workspace.getId());
                Log.d("TAG", "what");
                signContract_progressBar.setVisibility(View.VISIBLE);
                signContract_btn.setVisibility(View.GONE);
            } else {
                Snackbar.make(findViewById(R.id.lessor_contract_layout), "Failed to connect Internet! Please check your Internet connection", Snackbar.LENGTH_LONG);
            }
        }
    }
}
