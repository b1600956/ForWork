package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddWorkspaceActivity extends AppCompatActivity {
    private TextInputLayout placeName;
    private TextInputLayout placeDescription;
    private TextInputLayout placeAddress;
    private TextInputLayout placeCapacity;
    private TextInputLayout placeOpeningHour;
    private ArrayList<Uri> imgList;
    private ArrayList<String> amenitiesList;
    private Button amenitiesBtn;
    private Button uploadImgBtn;
    private final int SELECT_IMG = 1;
    private StorageReference storageReference;
    private DatabaseReference workspaceDB;
    private DatabaseReference userDB;
    private FirebaseUser user;
    private String nameStr;
    private String descStr;
    private String addressStr;
    private int capacity;
    private String openingHourStr;
    public static final String MESSAGE = "com.example.android.forwork.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workspace);
        placeName = findViewById(R.id.place_name);
        placeDescription = findViewById(R.id.place_description);
        placeAddress = findViewById(R.id.place_address);
        placeCapacity = findViewById(R.id.place_capacity);
        placeOpeningHour = findViewById(R.id.opening_hour);
        amenitiesBtn = findViewById(R.id.amenities);
        uploadImgBtn = findViewById(R.id.images);
        imgList = new ArrayList<>();
        amenitiesList = new ArrayList<>();
        storageReference = FirebaseStorage.getInstance().getReference("Co-Workspace");
        user = FirebaseAuth.getInstance().getCurrentUser();
        workspaceDB = FirebaseDatabase.getInstance().getReference("Co-Workspace");
        userDB = FirebaseDatabase.getInstance().getReference("user").child(user.getUid());
    }

    private boolean validatePlaceName() {
        nameStr = placeName.getEditText().getText().toString().trim();
        if (nameStr.isEmpty()) {
            placeName.setError("Field can't be empty!");
            return false;
        } else {
            placeName.setError(null);
            placeName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePlaceDesc() {
        descStr = placeDescription.getEditText().getText().toString().trim();
        if (descStr.isEmpty()) {
            placeDescription.setError("Field can't be empty!");
            return false;
        } else {
            placeDescription.setError(null);
            placeDescription.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePlaceAddress() {
        addressStr = placeAddress.getEditText().getText().toString().trim();
        if (addressStr.isEmpty()) {
            placeAddress.setError("Field can't be empty!");
            return false;
        } else {
            placeAddress.setError(null);
            placeAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePlaceCapacity() {
        String capacityStr = placeCapacity.getEditText().getText().toString().trim();
        if (capacityStr.isEmpty()) {
            placeCapacity.setError("Field can't be empty!");
            return false;
        }
        try {
            capacity = Integer.parseInt(capacityStr);
            if (capacity == 0) {
                placeCapacity.setError("Capacity can't be zero!");
                return false;
            } else {
                placeCapacity.setError(null);
                placeCapacity.setErrorEnabled(false);
            }
            return true;
        } catch (NumberFormatException nfe) {
            placeCapacity.setError("Please enter number!");
            return false;
        }
    }

    private boolean validateOpeningHour() {
        openingHourStr = placeOpeningHour.getEditText().getText().toString().trim();
        if (openingHourStr.isEmpty()) {
            placeOpeningHour.setError("Field can't be empty!");
            return false;
        } else {
            placeOpeningHour.setError(null);
            placeOpeningHour.setErrorEnabled(false);
        }
        return true;
    }

    public void submitWorkspace(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (validatePlaceName() && validatePlaceDesc() && validatePlaceAddress() && validatePlaceCapacity() && validateOpeningHour()) {
            if (imgList.isEmpty()) {
                Snackbar.make(findViewById(R.id.addWorkspace_layout), getString(R.string.upload_img_alert), Snackbar.LENGTH_LONG).show();
            } else if (amenitiesList.isEmpty()) {
                Snackbar.make(findViewById(R.id.addWorkspace_layout), getString(R.string.select_amenities_alert), Snackbar.LENGTH_LONG).show();
            } else {
                WorkSpace workspace = new WorkSpace(nameStr, descStr, addressStr, capacity, openingHourStr, amenitiesList, user.getUid());
                String workSpaceId = workspaceDB.push().getKey();
                workspaceDB.child(workSpaceId).setValue(workspace);
                userDB.child("co-workspace").setValue(workSpaceId);
                for (Uri img : imgList) {
                    storageReference.child(workSpaceId + "/" + UUID.randomUUID().toString()).putFile(img);
                }
                clearField();
                Snackbar.make(findViewById(R.id.addWorkspace_layout), getString(R.string.upload_img_alert), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void clearField() {
        placeOpeningHour.getEditText().setText("");
        placeCapacity.getEditText().setText("");
        placeAddress.getEditText().setText("");
        placeDescription.getEditText().setText("");
        placeName.getEditText().setText("");
    }

    public void getSelectedAmenities(ArrayList<String> mSelectedItems) {
        amenitiesBtn.setText(amenitiesBtn.getText().toString() + getString(R.string.display_selected_count)
                + mSelectedItems.size() + getString(R.string.display_selected_text));
        amenitiesList = mSelectedItems;
    }

    public void selectAmenities(View view) {
        DialogFragment serviceFragment = new SelectAmenitiesFragment();
        serviceFragment.show(getSupportFragmentManager(), getResources().getString(R.string.amenities_title));
    }

    public void uploadImages(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(getString(R.string.img_file_type)); //allows any image file type. Change * to specific extension to limit it
//**These following line is the important one!
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMG && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                    imgList.add(data.getClipData().getItemAt(i).getUri());
                }
            } else if (data.getData() != null) {
                Uri file = data.getData();
                imgList.add(file);
            }
            uploadImgBtn.setText(uploadImgBtn.getText().toString() + getString(R.string.display_selected_count)
                    + imgList.size() + getString(R.string.display_selected_text));
        }
    }
}
