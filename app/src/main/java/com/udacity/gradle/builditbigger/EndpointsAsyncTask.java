package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import androidx.core.util.Pair;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    public interface JokeAskedInterface {
        void jokeAsked(String joke);
    }

    private static JokeApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8886/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        context = params[0].first;
        String joke = params[0].second;

        try {
            return myApiService.sayJoke(joke).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
       EndpointsAsyncTask.JokeAskedInterface interfase =  (EndpointsAsyncTask.JokeAskedInterface) context;
       if (interfase != null) {
           interfase.jokeAsked(result);
       }
    }

}