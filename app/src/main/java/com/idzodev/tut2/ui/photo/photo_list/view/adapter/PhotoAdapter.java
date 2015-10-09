package com.idzodev.tut2.ui.photo.photo_list.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idzodev.tut2.R;
import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.entities.Photo;
import com.idzodev.tut2.ui.album.album_list.view.adapter.OnAlbumClickListener;
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

    public void setListener(OnPhotoClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (listener != null){
            listener.onPhotoClick(photos.get(pos));
        }
    }
}
