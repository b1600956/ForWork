package com.example.forwork;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;

import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class ViewContract extends AsyncTask<String, Void, Contract> {
    private WeakReference<TextView> contractAddressView;
    private WeakReference<TextView> contractMinDurationView;
    private WeakReference<TextView> contractFeeView;
    private WeakReference<TextView> contractAddressLabel;
    private WeakReference<TextView> contractMinDurationLabel;
    private WeakReference<TextView> contractFeeLabel;
    private WeakReference<ProgressBar> progressBar;
    private static Web3j web3;

    public ViewContract(ProgressBar progressBar, TextView contractAddressView, TextView contractMinDurationView, TextView contractFeeView, TextView contractAddressLabel, TextView contractMinDurationLabel, TextView contractFeeLabel) {
        this.progressBar = new WeakReference<>(progressBar);
        this.contractAddressView = new WeakReference<>(contractAddressView);
        this.contractMinDurationView = new WeakReference<>(contractMinDurationView);
        this.contractFeeView = new WeakReference<>(contractFeeView);
        this.contractAddressLabel = new WeakReference<>(contractAddressLabel);
        this.contractMinDurationLabel = new WeakReference<>(contractMinDurationLabel);
        this.contractFeeLabel = new WeakReference<>(contractFeeLabel);
        web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/45897e33eb4148ff8fa7c4af4eed5a16"));
    }

    @Override
    protected Contract doInBackground(String... strings) {
        TransactionManager txManager = null;
        try {
            BigInteger dummykey = new BigInteger("5F5766F42F4CB164D13AEE4A88EC447A3833A963DBA36FE46CF42593858D9AA3", 16);
            ECKeyPair ecKeyPair = ECKeyPair.create(dummykey);
            txManager = new FastRawTransactionManager(web3, Credentials.create(ecKeyPair), new NoOpProcessor(web3));
            LeaseContract contract = LeaseContract.load(strings[0], web3, txManager, DefaultGasProvider.GAS_PRICE,
                    DefaultGasProvider.GAS_LIMIT);
            Contract contractObj;
            //Log.d("TAG","kk"+contract.getDeployedAddress("3"));
            //BigInteger abc = contract.getDuration().send();
            //Log.d("TAG","kk"+abc);
            BigInteger minDuration = contract.getDuration().send();
            String rateOfCharge = contract.getRateOfCharge().send();
            BigInteger fee = contract.getPrice().send();
            contractObj = new Contract(rateOfCharge, "", minDuration, fee, "");
            return contractObj;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "Let's get it");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Contract c) {
        super.onPostExecute(c);
        Log.d("TAG", "Lala");
        try {
            if (c != null) {
                String period;
                if (c.getRateOfCharge().equalsIgnoreCase("Daily")) {
                    period = "Day(s)";
                } else if (c.getRateOfCharge().equalsIgnoreCase("Weekly")) {
                    period = "Week(s)";
                } else {
                    period = "Month(s)";
                }
                contractMinDurationView.get().setText(c.getMin_duration() + " " + period);
                contractFeeView.get().setText(c.getFee() + " ETH " + c.getRateOfCharge());
                this.contractAddressLabel.get().setVisibility(View.VISIBLE);
                this.contractMinDurationLabel.get().setVisibility(View.VISIBLE);
                this.contractFeeLabel.get().setVisibility(View.VISIBLE);
                contractAddressView.get().setVisibility(View.VISIBLE);
                progressBar.get().setVisibility(View.GONE);
                Log.d("TAG", "gg");
            } else {
                Log.d("TAG", "babaLala");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG", "tatababaLala");
        }
    }
}
