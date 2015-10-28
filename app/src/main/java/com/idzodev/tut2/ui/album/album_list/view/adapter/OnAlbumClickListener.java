package com.idzodev.tut2.ui.album.album_list.view.adapter;




import android.view.ActionMode;
import android.view.View;

import com.idzodev.tut2.domain.entities.Album;

/**
 * Created by vova on 06.10.15.
 */
public interface OnAlbumClickListener {
    void onAlbumClick(Album album);
    void deleteAlbum(Album album);
    void editAlbum(Album album);
    boolean showContextMenu(ActionMode actionMode, View v, ActionMode.Callback callback);

}
