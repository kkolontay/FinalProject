package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.gms.ads.MobileAds;
import com.kkolontay.addinactivity.AddActivity;
import com.kkolontay.joke.JokeMaker;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.JokeAskedInterface {
    private final static String JOKE = "joke";
    private EndpointsAsyncTask endpoint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        endpoint = new EndpointsAsyncTask();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchActivity(View view ) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(JOKE, JokeMaker.maker());
        startActivity(intent);
    }

    public void joking(View view) {
        if (endpoint != null) {
            endpoint.execute(new Pair<Context, String>(this, JokeMaker.maker()));
        }
    }

    @Override
    protected void onDestroy() {
        endpoint = null;
        super.onDestroy();
    }

    public void jokeAsked(String joke) {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra(JOKE, joke);
        startActivity(intent);
    }
}
