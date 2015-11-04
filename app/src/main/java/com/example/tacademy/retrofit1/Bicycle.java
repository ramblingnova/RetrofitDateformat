package com.example.tacademy.retrofit1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Tacademy on 2015-11-04.
 */

public class Bicycle
{
    public User user;
    public String _id;
    public Date createdAt;
    public String body;
    public int point;


    public class Facebook
    {
        public String id;
        public String name;
        public Picture picture;
        public String email;
    }
    public class Data
    {
        public boolean is_silhouette;
        public String url;
    }

    public class Picture
    {
        public Data data;
    }


    public class User
    {
        public String _id;
        public Facebook facebook;
        public String email;
        public String name;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss");

        return simpleDateFormat.format(createdAt);
    }
}
