package com.example.tacademy.retrofit1.dao;

/**
 * Created by Tacademy on 2015-11-12.
 */
public class Writer {
    public String _id = null;
    public Images images = null;
    public String email = null;
    public String name = null;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
