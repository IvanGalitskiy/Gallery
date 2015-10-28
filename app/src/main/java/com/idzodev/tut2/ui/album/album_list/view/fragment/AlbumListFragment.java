package com.idzodev.tut2.ui.album.album_list.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;


import com.idzodev.tut2.R;
import com.idzodev.tut2.data.reporitories.AlbumRepositoryImpl;
import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.repositories.AlbumRepository;
import com.idzodev.tut2.ui.album.album_detail.AlbumDetailFragment;
import com.idzodev.tut2.ui.album.album_list.presenter.AlbumPresenter;
import com.idzodev.tut2.ui.album.album_list.presenter.AlbumPresenterImpl;
import com.idzodev.tut2.ui.album.album_list.view.AlbumListView;
import com.idzodev.tut2.ui.album.album_list.view.adapter.AlbumAdapter;
import com.idzodev.tut2.ui.album.album_list.view.adapter.OnAlbumClickListener;
import com.idzodev.tut2.ui.photo.photo_list.view.fragment.PhotoListFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumListFragment extends Fragment implements AlbumListView, OnAlbumClickListener,
        View.OnClickListener{
    @InjectView(R.id.fragment_album_list_recycler)
    RecyclerView vRecyclerView;

    @InjectView(R.id.fragment_album_list_create)
    Button vCreateButton;


    Toolbar vToolbar;

    private AlbumAdapter albumAdapter;
    private AlbumPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlbumRepository repository = new AlbumRepositoryImpl(getActivity());
        presenter = new AlbumPresenterImpl(this, repository);
        vToolbar = (Toolbar) getActivity().findViewById(R.id.fragment_album_list_toolbar);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumAdapter = new AlbumAdapter(getActivity());
        albumAdapter.setListener(this);
        vRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vRecyclerView.setAdapter(albumAdapter);

        vCreateButton.setOnClickListener(this);

        if(vToolbar != null)
        ((AppCompatActivity) getActivity()).setSupportActionBar(vToolbar);

        presenter.showAlbums();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.fragment_album_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId())
      {
          case R.id.menu_fragment_album_list_add:
              presenter.onAlbumCreate();
              return true;


          default:
              return super.onOptionsItemSelected(item);
      }

    }


    @Override
    public void showAlbums(List<Album> albums) {
        albumAdapter.setAlbums(albums);
    }

    @Override
    public void showCreateAlbumFragment(Album album) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, AlbumDetailFragment.newInstance(album))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showPhotoListFragment(Album album) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, PhotoListFragment.newInstance(album))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAlbumClick(Album album) {
        presenter.onAlbumClick(album);
    }

    @Override
    public void deleteAlbum(Album album) {
        presenter.deleteAlbum(album);
    }

    @Override
    public void editAlbum(Album album) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, AlbumDetailFragment.newInstance(album))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean showContextMenu(ActionMode actionMode, View v, ActionMode.Callback callback) {
       albumAdapter.setvActionMode( getActivity().startActionMode(callback));
        v.setSelected(true);
        return  true;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_album_list_create:
                presenter.onAlbumCreate();
                break;
        }
    }


}
