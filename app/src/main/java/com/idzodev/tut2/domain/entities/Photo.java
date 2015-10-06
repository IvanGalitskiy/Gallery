package com.idzodev.tut2.domain.entities;

/**
 * Created by vova on 06.10.15.
 */
public class Photo {
    private long id;
    private String url;
    private long albumId;
    private int position;

    public Photo() {
    }

    public Photo(long id, String url, long albumId, int position) {
        this.id = id;
        this.url = url;
        this.albumId = albumId;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
