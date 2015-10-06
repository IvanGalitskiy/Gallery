package com.idzodev.tut2.domain.entities;

/**
 * Created by vova on 06.10.15.
 */
public class Album {
    private long id;
    private String name;
    private String photo;

    public Album() {
    }

    public Album(long id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
