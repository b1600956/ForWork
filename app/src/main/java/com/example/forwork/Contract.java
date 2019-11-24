package com.example.forwork;

import java.math.BigInteger;

public class Contract {
    private String rateOfCharge;
    private String coworkspaceId;
    private BigInteger min_duration;
    private BigInteger fee;
    private String ownerKey;

    public Contract(String rateOfCharge, String coworkspaceId, BigInteger min_duration, BigInteger fee, String ownerKey) {
        setRateOfCharge(rateOfCharge);
        setCoworkspaceId(coworkspaceId);
        setMin_duration(min_duration);
        setFee(fee);
        setOwnerKey(ownerKey);
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    public String getRateOfCharge() {
        return rateOfCharge;
    }

    public void setRateOfCharge(String rateOfCharge) {
        this.rateOfCharge = rateOfCharge;
    }

    public String getCoworkspaceId() {
        return coworkspaceId;
    }

    public void setCoworkspaceId(String coworkspaceId) {
        this.coworkspaceId = coworkspaceId;
    }

    public BigInteger getMin_duration() {
        return min_duration;
    }

    public void setMin_duration(BigInteger min_duration) {
        this.min_duration = min_duration;
    }

    public BigInteger getFee() {
        return fee;
    }

    public void setFee(BigInteger fee) {
        this.fee = fee;
    }
}
