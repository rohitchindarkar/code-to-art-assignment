package com.example.android.assignmentcodetoart;

/**
 * Created by user1 on 16/2/17.
 */

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetExample {


    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    public String run(String url) throws IOException {
        try {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();


            return response.body().string();
        } catch (Exception e) {
            return null;/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
        }
    }
}