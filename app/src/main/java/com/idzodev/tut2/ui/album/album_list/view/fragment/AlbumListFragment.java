package com.idzodev.tut2.ui.album.album_list.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumListFragment extends Fragment implements AlbumListView, OnAlbumClickListener, View.OnClickListener {
    @InjectView(R.id.fragment_album_list_recycler)
    RecyclerView vRecyclerView;

    @InjectView(R.id.fragment_album_list_create)
    Button vCreateButton;

    private AlbumAdapter albumAdapter;
    private AlbumPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlbumRepository repository = new AlbumRepositoryImpl(getActivity());
        presenter = new AlbumPresenterImpl(this, repository);
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
        presenter.showAlbums();
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

    }

    @Override
    public void onAlbumClick(Album album) {
        presenter.onAlbumClick(album);
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
