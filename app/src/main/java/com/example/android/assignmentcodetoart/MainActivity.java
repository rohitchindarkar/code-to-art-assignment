/*

* Purpose – Class summary.

* @author

* Created on

* Modified on

*/
package com.example.android.assignmentcodetoart;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.assignmentcodetoart.MovieList.MovieListFragment;



public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new MovieListFragment();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content_layout, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();

    }
}
