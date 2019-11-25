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
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;

import java.lang.ref.WeakReference;
import java.math.BigInteger;

public class SignContract extends AsyncTask<String, Void, Boolean> {
    private WeakReference<ProgressBar> progBar;
    private WeakReference<Snackbar> snackbar;
    private WeakReference<Button> signBtn;
    private static Web3j web3;
    private Credentials credentials;
    private DatabaseReference userDB;
    private DatabaseReference workspaceDB;
    private FirebaseUser user;


    public SignContract(ProgressBar progBar, Snackbar snackbar, Button signBtn) {
        this.progBar = new WeakReference<>(progBar);
        this.snackbar = new WeakReference<>(snackbar);
        this.signBtn = new WeakReference<>(signBtn);
        web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/45897e33eb4148ff8fa7c4af4eed5a16"));
        user = FirebaseAuth.getInstance().getCurrentUser();
        userDB = FirebaseDatabase.getInstance().getReference("user").child(user.getUid());
        workspaceDB = FirebaseDatabase.getInstance().getReference("Co-Workspace");
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        BigInteger privkey = new BigInteger(strings[0], 16);
        Log.d("TAG", strings[0] + "lk");
        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
        credentials = Credentials.create(ecKeyPair);
        TransactionManager txManager = new FastRawTransactionManager(web3, credentials, new NoOpProcessor(web3));
        LeaseContract contract = LeaseContract.load(strings[1], web3, txManager, DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT);
        Log.d("TAG", strings[1] + "kk");
        try {
            Log.d("TAG", " nonob" + contract.getContractAddress() + strings[2].split(" ")[0]);
            if (contract != null) {
                TransactionReceipt receipt = contract.signContract(Integer.parseInt(strings[2].split(" ")[0])).send();
                Log.d("TAG", strings[2] + " nonoa");
                if (receipt != null) {
                    userDB.child("coworkspace").setValue(strings[3]);
                    workspaceDB.child(strings[3] + "/status").setValue("Leased");
                    Log.d("TAG", strings[3] + " bobo");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "Let's get it");
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        Log.d("TAG", "Lala");
        try {
            progBar.get().setVisibility(View.GONE);
            if (b) {
                snackbar.get().setText("Successfully signed the contract");
                Log.d("TAG", "gg");
            } else {
                snackbar.get().setText("Failed to create contract. Please Try Again!").show();
                signBtn.get().setVisibility(View.VISIBLE);
                Log.d("TAG", "babaLala");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "tatababaLala");
        }
    }
}
