package com.idzodev.tut2.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.idzodev.tut2.R;
import com.idzodev.tut2.ui.album.album_list.view.fragment.AlbumListFragment;


public class MainActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getFragmentManager().findFragmentById(R.id.container) == null){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new AlbumListFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
            return;
        }


        super.onBackPressed();
    }
}
