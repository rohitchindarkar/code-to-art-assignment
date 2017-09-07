
/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
package com.example.android.assignmentcodetoart.MovieList;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.android.assignmentcodetoart.AppStatus;
import com.example.android.assignmentcodetoart.CreatedBy.CreatedByFragment;
import com.example.android.assignmentcodetoart.GetExample;
import com.example.android.assignmentcodetoart.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;


public class MovieListFragment extends Fragment {

    ViewGroup rootLayout ;
    CreatedByFragment createdByFragment = null;
    FragmentTransaction fragmentTransaction;
    ArrayList<MovieList> arrayOfUsers;
    GetExample getExample;
    JSONObject jsonArrayElement;
    JSONObject jsonstaffDetails;
    JSONArray jsonDataArray;
    private Toolbar toolbar;
    ProgressDialog progressDialog;
    MovieListAdapter adapter;
    MovieList movieList;
    ListView listView;
    String responseString,mod_release_date;
    JSONObject jsonObjectResponse;
    String movieName="",releaseDate="",category="",movieImage="",movieId="",alertMessage="";
    ImageButton infoButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootLayout = (ViewGroup)inflater.inflate(R.layout.fragment_movie_list, container, false);
        // Attaching the layout to the toolbar object
        if (!AppStatus.getInstance(getActivity()).isOnline()) {

            alertMessage = "You are not Connected to Internet";
            new android.app.AlertDialog.Builder(getActivity())
                    .setMessage(alertMessage)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }

        progressDialog = new ProgressDialog(getActivity(),R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);

        toolbar = (Toolbar) rootLayout.findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        View mCustomView =   ((AppCompatActivity) getActivity()).getLayoutInflater().inflate(R.layout.movie_list_toolbar_layout, null);
        toolbar.addView(mCustomView);

        infoButton=(ImageButton)mCustomView.findViewById(R.id.info_button_id);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createdByFragment = new CreatedByFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_content_layout, createdByFragment, createdByFragment.getClass().getSimpleName());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        arrayOfUsers = new ArrayList<MovieList>();
        adapter = new MovieListAdapter(getActivity(), arrayOfUsers);
        listView = (ListView)rootLayout. findViewById(R.id.listview);
        listView.setAdapter(adapter);

        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Please Wait....");
                progressDialog.show();
            }

            @Override
            protected String doInBackground(Void... params) {

                getExample = new GetExample();
                try {
                    responseString =getExample.run("https://api.themoviedb.org/3/movie/upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                progressDialog.dismiss();

                try {
                    jsonObjectResponse = new JSONObject(responseString);
                    jsonDataArray = jsonObjectResponse.optJSONArray("results");
                    for (int i = 0; i < jsonDataArray.length(); i++) {

                        jsonArrayElement = new JSONObject(String.valueOf(jsonDataArray.optJSONObject(i)));
                        jsonstaffDetails = jsonArrayElement;

                        movieName = jsonstaffDetails.get("title").toString();
                        releaseDate = jsonstaffDetails.get("release_date").toString();
                        category = jsonstaffDetails.get("adult").toString();
                        movieImage = jsonstaffDetails.get("poster_path").toString();
                        movieId = jsonstaffDetails.get("id").toString();
                        mod_release_date = releaseDate.substring(8,10)+"-"+releaseDate.substring(5,7)+"-"+releaseDate.substring(0,4);
                        movieList = new MovieList(movieName, mod_release_date, category, "https://image.tmdb.org/t/p/w185"+movieImage,movieId);
                        adapter.add(movieList);
                    }

                } catch(JSONException e){

                } catch (Exception e){

                }
            }
        }.execute();

        return rootLayout;
    }

}
