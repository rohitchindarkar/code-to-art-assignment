/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
package com.example.android.assignmentcodetoart.MovieList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.android.assignmentcodetoart.GetExample;
import com.example.android.assignmentcodetoart.MovieDetails.MovieDetailsFragment;
import com.example.android.assignmentcodetoart.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;



public class MovieListAdapter extends ArrayAdapter<MovieList> {

    MovieDetailsFragment movieDetailsFragment = null;
    android.app.FragmentManager fragmentManager;
    android.app.FragmentTransaction fragmentTransaction;
    ArrayList<MovieList>  movieList;
    ProgressDialog progressDialog;
    String detailsString,overView="",title="",poster_image="",movieId="",rating="";
    float ratingfloat;
    JSONObject jsonObjectResponse;
    GetExample getExample;
    Bundle bundle;

    public MovieListAdapter(Context context, ArrayList<MovieList> movieList) {
        super(context, 0, movieList);
        this.movieList = movieList;
    }
    String category;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MovieList movieList = getItem(position);

        progressDialog = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_layout, parent, false);
        }

        category = movieList.getCategory();

        TextView movieName = (TextView) convertView.findViewById(R.id.movie_name_id);
        TextView releaseDate = (TextView) convertView.findViewById(R.id.release_date_id);
        TextView category = (TextView) convertView.findViewById(R.id.category_id);
        ImageView poster = (ImageView)convertView.findViewById(R.id.icon);
        RelativeLayout relativeLayout =(RelativeLayout) convertView.findViewById(R.id.relative_layout_id);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieId = movieList.getMovieId();
                getExample = new GetExample();
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog.setMessage("Please Wait....");
                        progressDialog.show();
                    }

                    @Override
                    protected String doInBackground(Void... params) {
                        try {
                            detailsString = getExample.run("https://api.themoviedb.org/3/movie/"+movieId+"?api_key=b7cd3340a794e5a2f35e3abb820b497f");
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


                            jsonObjectResponse = new JSONObject(detailsString);
                            title = jsonObjectResponse.get("title").toString();
                            overView = jsonObjectResponse.get("overview").toString();
                            poster_image =jsonObjectResponse.get("poster_path").toString();
                            rating = jsonObjectResponse.get("vote_average").toString();
                            ratingfloat =Float.parseFloat( rating);
                            ratingfloat =ratingfloat/2;
                            poster_image = "https://image.tmdb.org/t/p/w185"+poster_image;

                            fragmentManager = ((Activity) getContext()).getFragmentManager();
                            movieDetailsFragment = new MovieDetailsFragment();
                            bundle = new Bundle();
                            bundle.putString("title", title);
                            bundle.putString("overView", overView);
                            bundle.putFloat("rating",ratingfloat);
                            bundle.putString("posterImage",poster_image);
                            movieDetailsFragment.setArguments(bundle);
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.main_content_layout, movieDetailsFragment, movieDetailsFragment.getClass().getSimpleName());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                        }catch (JSONException e){

                        }
                        catch (Exception e){

                        }
                    }
                }.execute();

            }
        });
        movieName.setText(movieList.getName());
        releaseDate.setText(movieList.getReleaseDate());
        Picasso.with(getContext()).load(movieList.posterImage).into(poster);
        if(category.equals("true")){
            category.setText("(A)");
        }else {
            category.setText("(U)");
        }
        return convertView;
    }
}