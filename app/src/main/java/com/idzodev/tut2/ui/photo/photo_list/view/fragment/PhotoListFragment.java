package com.idzodev.tut2.ui.photo.photo_list.view.fragment;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.idzodev.tut2.R;
import com.idzodev.tut2.data.reporitories.PhotoRepositoryImpl;
import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.entities.Photo;
import com.idzodev.tut2.domain.repositories.PhotoRepository;
import com.idzodev.tut2.ui.photo.photo_list.presenter.PhotoPresenter;
import com.idzodev.tut2.ui.photo.photo_list.presenter.PhotoPresenterImpl;
import com.idzodev.tut2.ui.photo.photo_list.view.PhotoListView;
import com.idzodev.tut2.ui.photo.photo_list.view.adapter.OnPhotoClickListener;
import com.idzodev.tut2.ui.photo.photo_list.view.adapter.PhotoAdapter;

import java.net.URI;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by NOTE on 09.10.2015.
 */
public class PhotoListFragment extends Fragment implements PhotoListView, OnPhotoClickListener, View.OnClickListener {
    @InjectView(R.id.fragment_photo_list_recycler)
    RecyclerView vRecyclerView;
    @InjectView(R.id.fragment_photo_list_gallery)
    Button vGallery;
    @InjectView(R.id.fragment_photo_list_camera)
    Button vCamera;

    public static final String ALBUM_ID = "ALBUM_ID";
    private PhotoAdapter photoAdapter;
    private PhotoPresenter presenter;


    private Uri imageUri;
    public static PhotoListFragment newInstance(Album album){
        Bundle args = new Bundle();
        long id = 0;
        if (album != null){
            id = album.getId();
        }
        args.putLong(ALBUM_ID, id);
        PhotoListFragment fragment = new PhotoListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PhotoRepository repository = new PhotoRepositoryImpl(getActivity());
        presenter = new PhotoPresenterImpl(this, repository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.inject(this, v);
        return  v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        photoAdapter = new PhotoAdapter(getActivity());
        photoAdapter.setListener(this);
        vRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vRecyclerView.setAdapter(photoAdapter);

        presenter.showPhotos(savedInstanceState.getLong(ALBUM_ID));
    }

    @Override
    public void showPhoto(List<Photo> photos) {
        photoAdapter.setPhotos(photos);
    }

    @Override
    public void createPhotoFromGallery() {
        openGallery();
    }

    @Override
    public void createPhotoFromCamera() {
        openCamera();
    }

    @Override
    public void showPhotoSlideFragment(Photo photo) {

    }

    @Override
    public void onPhotoClick(Photo photo) {
        presenter.onPhotoClick(photo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_photo_list_gallery:
                presenter.onPhotoCreateFromGallery();
                break;
            case R.id.fragment_photo_list_camera:
                presenter.onPhotoCreateFromCamera();
                break;
        }
    }

    private void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "StartTodo image"+new Date().getTime());
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image from Camera");
        imageUri = getActivity().getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 2);
    }
    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
}
