package com.kkolontay.addinactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    private TextView jokeTextView;
    private Button pushMessageButton;
    private static final String JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        pushMessageButton = findViewById(R.id.pushToServerButton);
        jokeTextView = findViewById(R.id.jokeTextView);
        if (savedInstanceState == null) {
            String joke = getIntent().getStringExtra(JOKE);
            if (joke != null) {
                jokeTextView.setText(joke);
            }
        } else {
            jokeTextView.setText(savedInstanceState.getString(JOKE));
        }
        pushMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EndpointsAsyncTask().execute(new Pair<Context, String>(getApplicationContext(), jokeTextView.getText().toString()));
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        String joke = jokeTextView.getText().toString();
        outState.putString(JOKE, joke);
        super.onSaveInstanceState(outState);
    }
}
