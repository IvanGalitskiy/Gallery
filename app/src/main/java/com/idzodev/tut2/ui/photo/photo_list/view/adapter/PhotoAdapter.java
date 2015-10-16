package com.idzodev.tut2.ui.photo.photo_list.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.idzodev.tut2.R;
import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.entities.Photo;
import com.idzodev.tut2.ui.MainActivity;
import com.idzodev.tut2.ui.album.album_list.view.adapter.OnAlbumClickListener;
import com.idzodev.tut2.ui.photo.photo_list.view.fragment.PhotoListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NOTE on 07.10.2015.
 */
public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<Photo> photos;
    private final LayoutInflater inflater;
    private OnPhotoClickListener listener;

    public PhotoAdapter(Context context) {
        this.photos = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(inflater.inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PhotoViewHolder holder = (PhotoViewHolder) viewHolder;
        holder.itemView.setOnClickListener(this);
        holder.vDelete.setTag(position);
       holder.vDelete.setOnClickListener(this);
        holder.imageView.setTag(position);
        holder.imageView.setOnClickListener(this);
       holder.itemView.setTag(position);


       Photo photo = photos.get(position);


        Picasso.with(holder.imageView.getContext())
                .load(photo.getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    public void addPhoto(Photo photo)
    {
        photos.add(photo);
        notifyDataSetChanged();
    }
    public void deletePhoto(Photo photo)
    {
        for (int i=0;i<photos.size();i++)
        {
            if (photos.get(i).getAlbumId()==photo.getAlbumId() && photos.get(i).getPosition() == photo.getPosition())
            {
                photos.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
    }
    public void setListener(OnPhotoClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        switch (v.getId())
        {
            case R.id.item_photo_image:
                if (listener != null) {
                    listener.onPhotoClick(photos.get(pos));
                }
                break;
            case R.id.item_photo_delbtn:
              Photo photo =   photos.get(pos);
                if (listener != null) {
                    listener.deletePhoto(photo);
                    this.deletePhoto(photo);
                }
                break;
        }

    }
}
