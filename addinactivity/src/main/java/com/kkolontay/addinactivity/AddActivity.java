package com.kkolontay.addinactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    private TextView jokeTextView;
    private static final String JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        jokeTextView = findViewById(R.id.jokeTextView);
        if (savedInstanceState == null) {
            String joke = getIntent().getStringExtra(JOKE);
            if (joke != null) {
                jokeTextView.setText(joke);
            }
        } else {
            jokeTextView.setText(savedInstanceState.getString(JOKE));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        String joke = jokeTextView.getText().toString();
        outState.putString(JOKE, joke);
        super.onSaveInstanceState(outState);
    }
}
