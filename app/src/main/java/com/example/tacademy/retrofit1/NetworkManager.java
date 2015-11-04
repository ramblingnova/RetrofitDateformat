package com.example.tacademy.retrofit1;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Tacademy on 2015-11-04.
 */
public class NetworkManager {
    private static NetworkManager networkManager;

    private NetworkManager() {
    }

    public static NetworkManager getInstance() {
        if (networkManager == null)
            networkManager = new NetworkManager();
        return networkManager;
    }

    public interface Test {
        String base_url = "http://bikee.kr.pe";
        // String base_url = "https://api.github.com";

        @GET("/comments/56372230faf540174aff15ed")
        Call<List<Bicycle>> test1();



//        @GET("/repos/{owner}/{repo}/contributors")
//        Call<List<Contributor>> contributors(
//                @Path("owner") String owner,
//                @Path("repo") String repo);
    }

    private Test init() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Test.base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(Test.class);
    }

    public class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String date = json.getAsString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            try {
                return format.parse(date.replaceAll("Z$", "+0000"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Call<List<Bicycle>> getBicycle() {
        Test test = init();
        Call<List<Bicycle>> result = null;
        if (test != null)
            result = test.test1();
        return result;
    }

//    public Call<List<Contributor>> getContributors() {
//        Test test = init();
//        Call<List<Contributor>> result = null;
//        if (test != null)
//            result = test.contributors("square", "retrofit");
//        return result;
//    }

//    public static class Contributor {
//        public final String login;
//        public final int contributions;
//
//        public Contributor(String login, int contributions) {
//            this.login = login;
//            this.contributions = contributions;
//        }
//    }
}
