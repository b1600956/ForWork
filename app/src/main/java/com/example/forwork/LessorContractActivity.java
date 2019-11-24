package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


import java.math.BigInteger;


public class LessorContractActivity extends AppCompatActivity {
    private TextInputLayout contract_min_duration;
    private TextInputLayout contract_fee;
    private TextView duration_quantifier;
    private String workspaceId;
    private int minDuration;
    private int contractFee;
    private String rateOfCharge;
    private ProgressBar progressBar;
    private Button btn;
    private String privateKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_contract);
        /**
        chargeRate = getResources().getStringArray(R.array.chargeRateList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, chargeRate);
        spinner = findViewById(R.id.filled_exposed_dropdown);
         spinner.setAdapter(adapter);**/
        Intent intent = getIntent();
        workspaceId = intent.getStringExtra(AddWorkspaceActivity.MESSAGE);
        contract_min_duration = findViewById(R.id.contract_duration);
        contract_fee = findViewById(R.id.contract_fee);
        duration_quantifier = findViewById(R.id.min_duration_quantifier);
        progressBar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.create_contract);
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
            getPrivateKey();
        }
    }

    private void getPrivateKey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setTitle("Enter your private key");
// Set up the buttons
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                privateKey = input.getText().toString();
                if (privateKey != null && !privateKey.isEmpty())
                    createAnotherContract();
            }
        }).setNegativeButton(R.string.amenities_fragment_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    private void createAnotherContract() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d("TAG", privateKey);
            if (privateKey != null) {
                new CreateContract(progressBar, Snackbar.make(findViewById(R.id.lessor_contract_layout), "", Snackbar.LENGTH_LONG), this)
                        .execute(new Contract(rateOfCharge, workspaceId, BigInteger.valueOf(minDuration), BigInteger.valueOf(contractFee), privateKey));
                Log.d("TAG", "what");
                progressBar.setVisibility(View.VISIBLE);
                btn.setVisibility(View.GONE);
            }
        } else {
            Snackbar.make(findViewById(R.id.lessor_contract_layout), "Failed to connect Internet! Please check your Internet connection", Snackbar.LENGTH_LONG);
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
