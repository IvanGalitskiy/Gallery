package com.idzodev.tut2.ui.photo.photo_list.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
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
    private long album_id;
    private int pos = 0;


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
        vGallery.setOnClickListener(this);
        vCamera.setOnClickListener(this);

        if (getArguments()!= null){
            album_id = getArguments().getLong(ALBUM_ID);
           presenter.showPhotos(album_id);
       }


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
    public void saveToAdapter(Photo photo) {
        photoAdapter.addPhoto(photo);
    }

    @Override
    public void deletePhoto(Photo photo) {
        presenter.deletePhoto(photo);
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
       /* Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);*/
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), 1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 1){
                 imageUri = data.getData();
                 pos++;
            }
            if (requestCode == 2) {
                imageUri = null;
            }
            presenter.savePhoto(imageUri.toString(),album_id, pos);

        }
    }
}
