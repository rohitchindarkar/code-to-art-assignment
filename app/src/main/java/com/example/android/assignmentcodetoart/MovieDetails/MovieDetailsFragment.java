/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
package com.example.android.assignmentcodetoart.MovieDetails;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.android.assignmentcodetoart.AppStatus;
import com.example.android.assignmentcodetoart.R;
import java.util.ArrayList;
import java.util.List;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


    public class MovieDetailsFragment extends Fragment {

    ViewGroup rootLayout;
    private Toolbar toolbar;
    BannerSlider bannerImage;
    TextView title;
    TextView overView;
    RatingBar movieRating;
    float rating;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
             rootLayout = (ViewGroup)inflater.inflate(R.layout.fragment_movie_details, container, false);

        //Check for internet.
            if (!AppStatus.getInstance(getActivity()).isOnline()) {

                String alertMessage = "You are not Connected to Internet";
                new android.app.AlertDialog.Builder(getActivity())
                        .setMessage(alertMessage)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }

            // Attaching the layout to the toolbar object
            toolbar = (Toolbar) rootLayout.findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setTitleTextColor(Color.WHITE);
            View mCustomView =   ((AppCompatActivity) getActivity()).getLayoutInflater().inflate(R.layout.movie_details_toolbar_layout, null);
            toolbar.addView(mCustomView);


            bannerImage =(BannerSlider) rootLayout.findViewById(R.id.banner_image);
            movieRating =(RatingBar) rootLayout.findViewById(R.id.rating_id);
            title =(TextView)rootLayout.findViewById(R.id.title_id);
            overView =(TextView)rootLayout .findViewById(R.id.overview_id);
            title.setText(getArguments().get("title").toString());
            overView.setText(getArguments().get("overView").toString());
            rating =getArguments().getFloat("rating");
            movieRating.setRating(rating);
            movieRating.setNumStars(5);
            LayerDrawable stars = (LayerDrawable) movieRating.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#FF4081"), PorterDuff.Mode.SRC_ATOP);

            //Displaying banner image.
            List<Banner> banners=new ArrayList<>();
            banners.add(new RemoteBanner(getArguments().getString("posterImage")));
            banners.add(new DrawableBanner(R.drawable.noimage));
            banners.add(new DrawableBanner(R.drawable.noimage));
            banners.add(new DrawableBanner(R.drawable.noimage));
            banners.add(new DrawableBanner(R.drawable.noimage));
            bannerImage.setBanners(banners);

            return rootLayout;
    }

}
