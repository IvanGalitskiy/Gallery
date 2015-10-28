package com.idzodev.tut2.ui.album.album_list.view.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener{
    private List<Album> albums;
    private final LayoutInflater inflater;
    private OnAlbumClickListener listener;
    private ActionMode vActionMode;

 private Album album;
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
        holder.itemView.setOnLongClickListener(this);
        holder.itemView.setTag(i);
        holder.vDelete.setTag(i);
        holder.vDelete.setOnClickListener(this);
        holder.vEdit.setTag(i);
        holder.vEdit.setOnClickListener(this);
        holder.imageView.setTag(i);
        holder.imageView.setOnClickListener(this);
        holder.imageView.setOnLongClickListener(this);

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
    public void deleteAlbum(Album album)
    {
        for (int i=0;i<albums.size();i++)
        {
            if (albums.get(i).getId() == album.getId())
            {
                albums.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
    }
    public void setListener(OnAlbumClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        Album album = albums.get(pos);
        switch (v.getId()) {
            case (R.id.item_album_image):
                if (listener != null) {
                listener.onAlbumClick(album);
            }
                break;
            case (R.id.item_album_delete):
                if (listener != null) {
                    listener.deleteAlbum(album);
                    this.deleteAlbum(album);
                }
                break;
            case (R.id.item_album_edit):
                if (listener != null) {
                    listener.editAlbum(album);
                }
                break;


        }
    }
    @Override
    public boolean onLongClick(View view) {

        if (vActionMode != null) {
            return false;
        }
        int pos = (int) view.getTag();
        album = albums.get(pos);
        switch (view.getId())
        {
            case R.id.item_album_image:

                return listener.showContextMenu(vActionMode, view, mActionModeCallback);
        }

        return false;
    }

    public ActionMode getvActionMode() {
        return vActionMode;
    }

    public void setvActionMode(ActionMode vActionMode) {
        this.vActionMode = vActionMode;
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.fragment_album_list_context_action, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_fragment_album_list_context_action_delete:
                    listener.deleteAlbum(album);
                    mode.finish();
                    return true;
                case R.id.menu_fragment_album_list_context_action_edit:
                    listener.editAlbum(album);
                    mode.finish();
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
           setvActionMode(null);
        }
    };
}
