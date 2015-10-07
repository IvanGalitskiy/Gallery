package com.idzodev.tut2.ui.photo.photo_list.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;

import com.idzodev.tut2.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by NOTE on 07.10.2015.
 */
public class PhotoViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_photo_image)
    ImageView imageView;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
