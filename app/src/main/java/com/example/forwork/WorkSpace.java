package com.example.forwork;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class WorkSpace implements Parcelable {
    private String name;
    private String address;
    private String description;
    private int capacity;
    private String opening_hour;
    private ArrayList<String> amenities;
    private String lessor;
    private String contractAddress;
    private String status;
    private ArrayList<String> imageList;
    private String location;
    private String id;

    public WorkSpace() {
    }

    public WorkSpace(String id, String name, String description, String address, int capacity, String opening_hour, ArrayList<String> amenities, String lessor, String location) {
        setId(id);
        setName(name);
        setAddress(address);
        setDescription(description);
        setCapacity(capacity);
        setOpening_hour(opening_hour);
        setAmenities(amenities);
        setLessor(lessor);
        setContractAddress("");
        setStatus("Available");
        setLocation(location);
        setImageList(new ArrayList<>());
    }

    private WorkSpace(Parcel in) {
        setId(in.readString());
        setName(in.readString());
        setAddress(in.readString());
        setDescription(in.readString());
        setCapacity(in.readInt());
        setOpening_hour(in.readString());
        setAmenities((ArrayList<String>) in.readSerializable());
        setLessor(in.readString());
        setContractAddress(in.readString());
        setStatus(in.readString());
        setImageList((ArrayList<String>) in.readSerializable());
        setLocation(in.readString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getOpening_hour() {
        return opening_hour;
    }

    public void setOpening_hour(String opening_hour) {
        this.opening_hour = opening_hour;
    }

    public ArrayList<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<String> amenities) {
        this.amenities = amenities;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addImage(String imageUrl) {
        this.imageList.add(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getName());
        dest.writeString(getAddress());
        dest.writeString(getDescription());
        dest.writeInt(getCapacity());
        dest.writeString(getOpening_hour());
        dest.writeSerializable(getAmenities());
        dest.writeString(getLessor());
        dest.writeString(getContractAddress());
        dest.writeString(getStatus());
        dest.writeSerializable(getImageList());
        dest.writeString(getLocation());
    }

    public static final Creator<WorkSpace> CREATOR = new Creator<WorkSpace>() {
        @Override
        public WorkSpace createFromParcel(Parcel in) {
            return new WorkSpace(in);
        }

        @Override
        public WorkSpace[] newArray(int size) {
            return new WorkSpace[size];
        }
    };
}
