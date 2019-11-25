package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FeedbackActivity extends AppCompatActivity {
    private TextInputLayout commentView;
    private DatabaseReference feedbackDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private RadioGroup feedbackCategory;
    private String commentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        commentView = findViewById(R.id.comment);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        feedbackDatabase = FirebaseDatabase.getInstance().getReference("Feedback");
        feedbackCategory = findViewById(R.id.feedbackCategory);
    }

    private boolean validateComment() {
        commentStr = commentView.getEditText().getText().toString().trim();
        if (commentStr.isEmpty()) {
            commentView.setError("Field can't be empty!");
            return false;
        } else {
            commentView.setError(null);
            commentView.setErrorEnabled(false);
        }
        return true;
    }

    public void submitFeedback(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (validateComment()) {
            String category;
            switch (feedbackCategory.getCheckedRadioButtonId()) {
                case R.id.suggestion:
                    category = "Suggestion";
                    break;
                case R.id.compliment:
                    category = "Compliment";
                    break;
                default:
                    category = "Complaint";
                    break;
            }
            Feedback feedback = new Feedback(user.getEmail(), category, commentStr, new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
            feedbackDatabase.child(feedbackDatabase.push().getKey()).setValue(feedback);
            Snackbar.make(findViewById(R.id.feedback_layout), getString(R.string.feedback_sent), Snackbar.LENGTH_LONG).show();
        }
    }
}
