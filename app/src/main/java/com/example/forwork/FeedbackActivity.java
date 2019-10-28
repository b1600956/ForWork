package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class FeedbackActivity extends AppCompatActivity {
    private TextInputLayout commentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        commentView = findViewById(R.id.comment);
    }

    private boolean validateComment() {
        String commentStr = commentView.getEditText().getText().toString().trim();
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
            Snackbar.make(findViewById(R.id.feedback_layout), getString(R.string.feedback_sent), Snackbar.LENGTH_LONG).show();
            commentView.getEditText().setText("");
        }
    }
}
