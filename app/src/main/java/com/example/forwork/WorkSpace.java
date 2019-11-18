package com.example.forwork;

import java.util.ArrayList;

public class WorkSpace {
    private String name;
    private String address;
    private int image;
    private String description;
    private int capacity;
    private String opening_hour;
    private ArrayList<String> amenities;
    private String lessor;

    public WorkSpace() {
    }

    public WorkSpace(String name, String address, int image) {
        setName(name);
        setAddress(address);
        setImage(image);
    }

    public WorkSpace(String name, String description, String address, int capacity, String opening_hour, ArrayList<String> amenities, String lessor) {
        setName(name);
        setAddress(address);
        setDescription(description);
        setCapacity(capacity);
        setOpening_hour(opening_hour);
        setAmenities(amenities);
        setLessor(lessor);
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
