package com.idzodev.tut2.ui.album.album_detail;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.idzodev.tut2.R;
import com.idzodev.tut2.data.reporitories.AlbumRepositoryImpl;
import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.repositories.AlbumRepository;
import com.idzodev.tut2.ui.MainActivity;
import com.idzodev.tut2.ui.album.album_detail.presenter.AlbumDetailPresenter;
import com.idzodev.tut2.ui.album.album_detail.presenter.AlbumDetailPresenterImpl;
import com.idzodev.tut2.ui.album.album_list.presenter.AlbumPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumDetailFragment extends Fragment implements View.OnClickListener, AlbumDetailView{

    @InjectView(R.id.fragment_album_detail_open_gallery) Button btGallery;
    @InjectView(R.id.fragment_album_detail_open_camera)Button btCamera;
    @InjectView(R.id.fragment_album_detail_save) Button btSave;
    @InjectView(R.id.fragment_album_detail_image)ImageView imageView;
    @InjectView(R.id.fragment_album_detail_name)EditText vEditText;

   private AlbumDetailPresenter presenter;
    private long id = 0;
    private Uri imageUri;


    public static AlbumDetailFragment newInstance(Album album){
        Bundle args = new Bundle();
        long id = 0;
        String url = null;
        if (album != null){
            id = album.getId();
            url = album.getPhoto();
            args.putLong("id", id);
            args.putString("url",url);
        }
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlbumRepository repository = new AlbumRepositoryImpl(getActivity());
        presenter = new AlbumDetailPresenterImpl(this, repository);
        if (getArguments()!=null) {
            id = getArguments().getLong("id");
            if (getArguments().getString("url")!=null) {
                imageUri = Uri.parse(getArguments().getString("url"));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        btCamera.setOnClickListener(this);
        btGallery.setOnClickListener(this);
        btSave.setOnClickListener(this);

        presenter.showAlbum(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_album_detail_open_camera:
                openCamera();
                break;

            case R.id.fragment_album_detail_open_gallery:
                openGallery();
                break;

            case R.id.fragment_album_detail_save:
                presenter.saveAlbum(id, vEditText.getText().toString(), imageUri.toString());
                getActivity().onBackPressed();
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
      /*  Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,2);*/
    }
    private static final int SELECT_PICTURE = 1;
    private void openGallery(){
      /*  Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);*/
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 1){
                 imageUri = data.getData();
            }
            if (requestCode == 2){
                imageUri = null;
            }

            Picasso.with(getActivity())
                    .load(imageUri)
                    .into(imageView);
        }
    }

    @Override
    public void showEditDetail(Album album) {
    if (album != null) {
     vEditText.setText(album.getName(), TextView.BufferType.EDITABLE);
       Picasso.with(getActivity())
                .load(album.getPhoto())
                .into(imageView);
        }
    }
}
