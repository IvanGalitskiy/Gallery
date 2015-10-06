package com.idzodev.tut2.ui.album.album_list.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idzodev.tut2.R;
import com.idzodev.tut2.domain.entities.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<Album> albums;
    private final LayoutInflater inflater;
    private OnAlbumClickListener listener;

    public AlbumAdapter(Context context) {
        albums = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AlbumViewHolder(inflater.inflate(R.layout.item_album, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AlbumViewHolder holder = (AlbumViewHolder) viewHolder;
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(i);

        Album album = albums.get(i);

        holder.textView.setText(album.getName());

        Picasso.with(holder.imageView.getContext())
                .load(album.getPhoto())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    public void setListener(OnAlbumClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (listener != null){
            listener.onAlbumClick(albums.get(pos));
        }
    }


}
