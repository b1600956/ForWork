package com.example.forwork;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;

import java.lang.ref.WeakReference;
import java.math.BigInteger;

public class CreateContract extends AsyncTask<Contract, Void, String> {
    private WeakReference<ProgressBar> progBar;
    private WeakReference<Snackbar> snackbar;
    private Activity activity;
    private static Web3j web3;
    private Credentials credentials;
    private DatabaseReference workspaceDB;
    private DatabaseReference userDB;
    private FirebaseUser user;


    public CreateContract(ProgressBar progBar, Snackbar snackbar, Activity activity) {
        this.progBar = new WeakReference<>(progBar);
        this.snackbar = new WeakReference<>(snackbar);
        this.activity = activity;
        web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/45897e33eb4148ff8fa7c4af4eed5a16"));
        workspaceDB = FirebaseDatabase.getInstance().getReference("Co-Workspace");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDB = FirebaseDatabase.getInstance().getReference("user").child(user.getUid());
    }

    @Override
    protected String doInBackground(Contract... contracts) {
        BigInteger privkey = new BigInteger(contracts[0].getOwnerKey(), 16);
        Log.d("TAG", contracts[0].getOwnerKey() + "lk");
        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
        credentials = Credentials.create(ecKeyPair);
        //TransactionManager txManager = new FastRawTransactionManager(web3, credentials, new NoOpProcessor(web3));
        RemoteCall<LeaseContract> request = LeaseContract.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT, contracts[0].getMin_duration(), contracts[0].getFee(), contracts[0].getRateOfCharge(), contracts[0].getCoworkspaceId());
        /**
         LeaseContract contract = LeaseContract.load("0xA4Ce2B0815f8780A3fA44F36b53b339219668bE1", web3, txManager, DefaultGasProvider.GAS_PRICE,
         DefaultGasProvider.GAS_LIMIT);*/
        try {
            LeaseContract contract = request.send();
            String contractAddress = contract.getContractAddress();
            Log.d("TAG", contractAddress + " nonob");
            if (contractAddress != null) {
                Log.d("TAG", contracts[0].getCoworkspaceId() + " nonoa");
                workspaceDB.child(contracts[0].getCoworkspaceId() + "/contractAddress").setValue(contractAddress);
                userDB.child("coworkspace").setValue(contracts[0].getCoworkspaceId());
            }
            Log.d("TAG", contractAddress + " nono");
            return contractAddress;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "Let's get it");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("TAG", "Lala");
        try {
            if (s != null && !s.isEmpty()) {
                progBar.get().setVisibility(View.GONE);
                activity.startActivity(new Intent(activity, LessorMainActivity.class));
                activity.finish();
                Log.d("TAG", "gg");
            } else {
                snackbar.get().setText("Failed to create contract. Please Try Again!").show();
                Log.d("TAG", "babaLala");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "tatababaLala");
        }
    }
}
