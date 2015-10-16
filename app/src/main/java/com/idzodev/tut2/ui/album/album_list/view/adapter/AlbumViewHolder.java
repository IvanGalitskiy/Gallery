package com.idzodev.tut2.ui.album.album_list.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.idzodev.tut2.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumViewHolder extends RecyclerView.ViewHolder{
    @InjectView(R.id.item_album_image) ImageView imageView;
    @InjectView(R.id.item_album_delete) ImageButton vDelete;
    @InjectView(R.id.item_album_edit) ImageButton vEdit;
    @InjectView(R.id.item_album_name) TextView textView;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
