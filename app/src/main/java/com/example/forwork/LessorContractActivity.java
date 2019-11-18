package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class LessorContractActivity extends AppCompatActivity {
    private String[] chargeRate;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView spinner;
    private TextInputLayout contract_min_duration;
    private TextInputLayout contract_fee;
    private TextView duration_quantifier;
    private String workspaceId;
    private static Web3j web3;
    private Credentials credentials;
    private int minDuration;
    private int contractFee;
    private String rateOfCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_contract);
        Log.d("Q", "dun");/**
        chargeRate = getResources().getStringArray(R.array.chargeRateList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, chargeRate);
        spinner = findViewById(R.id.filled_exposed_dropdown);
         spinner.setAdapter(adapter);**/
        Intent intent = getIntent();
        workspaceId = intent.getStringExtra(AddWorkspaceActivity.MESSAGE);
        contract_min_duration = findViewById(R.id.contract_duration);
        contract_fee = findViewById(R.id.contract_fee);
        duration_quantifier = findViewById(R.id.min_duration_quantifier);
        web3 = Web3j.build(new HttpService());
        BigInteger privkey = new BigInteger("5F5766F42F4CC164D13AEE4A88EC447A3833A963DBA36FE46CF43593858D9AF2", 16);
        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
        credentials = Credentials.create(ecKeyPair);
        rateOfCharge = getString(R.string.workspace_daily);
    }

    private boolean validateDuration() {
        String durationStr = contract_min_duration.getEditText().getText().toString().trim();
        if (durationStr.isEmpty()) {
            contract_min_duration.setError("Field can't be empty!");
            return false;
        } else {
            if (Integer.parseInt(durationStr) == 0) {
                contract_min_duration.setError("Minimum duration can't be zero!");
                return false;
            }
            contract_min_duration.setError(null);
            contract_min_duration.setErrorEnabled(false);
            minDuration = Integer.parseInt(durationStr);
        }
        return true;
    }

    private boolean validateFee() {
        String feeStr = contract_fee.getEditText().getText().toString().trim();
        if (feeStr.isEmpty()) {
            contract_fee.setError("Field can't be empty!");
            return false;
        } else {
            contract_fee.setError(null);
            contract_fee.setErrorEnabled(false);
            contractFee = Integer.parseInt(feeStr);
        }
        return true;
    }

    private void clearField() {
        contract_min_duration.getEditText().setText("");
        contract_fee.getEditText().setText("");
    }

    public void createContract(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (validateDuration() && validateFee()) {
            clearField();
            RemoteCall<LeaseContract> request = LeaseContract.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,
                    DefaultGasProvider.GAS_LIMIT, BigInteger.valueOf(minDuration), BigInteger.valueOf(contractFee), rateOfCharge, workspaceId);
            LeaseContract contract;
            try {
                contract = request.send();
                String contractAddress = contract.getDeployedAddress("3");
                Snackbar.make(findViewById(R.id.lessor_contract_layout), contractAddress, Snackbar.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void selectPeriod(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.contract_daily:
                if (checked) {
                    //implementation code
                    duration_quantifier.setText(getResources().getString(R.string.min_duration_day));
                    rateOfCharge = getString(R.string.workspace_daily);
                }
                break;
            case R.id.contract_weekly:
                if (checked) {
                    duration_quantifier.setText(getResources().getString(R.string.min_duration_week));
                    rateOfCharge = getString(R.string.workspace_weekly);
                }
                break;
            case R.id.contract_monthly:
                if (checked) {
                    duration_quantifier.setText(getResources().getString(R.string.min_duration_month));
                    rateOfCharge = getString(R.string.work_space_monthly);
                }
                break;
            default:
                break;
        }
    }
}
