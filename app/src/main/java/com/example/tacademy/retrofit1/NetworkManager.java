package com.example.tacademy.retrofit1;

import android.util.Log;

import com.example.tacademy.retrofit1.dao.Comment;
import com.example.tacademy.retrofit1.dao.ResponseForAndroidRequest;
import com.example.tacademy.retrofit1.dao.User;
import com.example.tacademy.retrofit1.dao.Bicycle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * Created by Tacademy on 2015-11-04.
 */
public class NetworkManager {
    private static NetworkManager networkManager;
    RestAdapter restAdapter;
    ServerUrl serverUrl;

    private NetworkManager() {
        OkHttpClient okHttpClient = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        okHttpClient.setCookieHandler(cookieManager);
        Client client = new OkClient(okHttpClient);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ServerUrl.baseUrl)
                .setConverter(new GsonConverter(gson))
                .setClient(client)
                .build();
        serverUrl = restAdapter.create(ServerUrl.class);
    }

    public static NetworkManager getInstance() {
        if (networkManager == null)
            networkManager = new NetworkManager();
        return networkManager;
    }

    public interface ServerUrl {
//        String baseUrl = "http://192.168.211.18:3000";
        String baseUrl = "http://192.168.201.107:3000";

        // TODO 본인정보조회 app.get('/users/:userId',users.profile)
        @GET("/users/{userId}")
        void selectUser(@Path("userId") String user_id, Callback<User> callback);

        // TODO 회원가입 app.post('/users', users.create)
        @POST("/users")
        void insertUser(@Body User user, Callback<ResponseForAndroidRequest> callback);

        // TODO 회원수정 app.put('/users/:userId',userAuth,users.edit);
        @PUT("/users/{userId}")
        void updateUser(@Path("userId") String user_id, @Body User user, Callback<ResponseForAndroidRequest> callback);

        // TODO 보유자전거등록 app.post('/bikes/users',auth.requiresLogin,bikes.create);
        @Multipart
        @POST("/bikes/users")
        void insertBicycle(@Part("image") TypedFile file1,
                           @Part("image") TypedFile file2,
                           @Part("type") String type,
                           @Part("height") String height,
                           @Part("title") String title,
                           @Part("intro") String intro,
                           @Part("price.hour") int price_hour,
                           @Part("price.day") int price_day,
                           @Part("price.month") int price_month,
                           Callback<ResponseForAndroidRequest> callback);

        // TODO 보유자전거조회 app.get('/bikes/users',auth.requiresLogin,bikes.myList); 유저아이디는?
        @GET("/bikes/users")
        void selectBicycle(Callback<List<Bicycle>> callback);

        // TODO 보유자전거수정 app.put('/bikes/users/:bikeId',bikeAuth,bikes.edit);
        @PUT("/bikes/users/{bikeId}")
        void updateBicycle(@Path("bikeID") String bike_id, Callback<ResponseForAndroidRequest> callback);

        // TODO 보유자전거 활성화/비활성화 app.put('/bikes/active/:bikeId',bikeAuth,bikes.active);
        @PUT("/bikes/active/{bikeId}")
        void updateBicycleOnOff(@Path("bikeID") String bike_id, Callback<ResponseForAndroidRequest> callback);

        // TODO 보유자전거삭제 app.delete('/bikes/users/:bikeId',bikeAuth,bikes.delete);
        @DELETE("/bikes/users/{bikeId}")
        void deleteBicycle(@Path("bikeId") String bike_id, Callback<ResponseForAndroidRequest> callback);

        // TODO 전체자전거조회 app.get('/bikes',bikes.index);
        @GET("/bikes")
        void selectAllBicycle(Callback<List<Bicycle>> callback);

        // TODO 자전거상세조회 app.get('/bikes/:bikeId/detail',bikes.detail);
        @GET("/bikes/{bikeId}/detail")
        void selectBicycleDetail(@Path("bikeId") String bike_id, Callback<Bicycle> callback);

        // 로그인
        @FormUrlEncoded
        @POST("/users/session")
        void login(@Field("email") String email, @Field("password") String password, Callback<ResponseForAndroidRequest> callback);

        // TODO 내평가보기 app.get('/comments',auth.requiresLogin,comments.show);
        @GET("/comments")
        void selectUserComment(Callback<List<Comment>> callback);

        // TODO 후기작성실패 app.get('/authfail',comments.fail);
        @GET("/authfail")
        void isFailInsertComment(Callback<ResponseForAndroidRequest> callback);

        // TODO 자전거후기작성 app.post('/comments/:bikeId',commentAuth,comments.create);
        @POST("/comments/{bikeId}")
        void insertBicycleComment(@Path("bikeId") String bike_id, Callback<ResponseForAndroidRequest> callback);

        // TODO 자전거후기보기 app.get('/comments/:bikeId',comments.bike);
        @GET("/comments/{bikeId}")
        void selectBicycleComment(@Path("bikeId") String bike_id, Callback<List<Comment>> callback);

        // TODO 고객문의등록 app.post('/inquiry',auth.requiresLogin,inquires.create);
        @POST("/inquiry")
        void insertInquiry(Callback<ResponseForAndroidRequest> callback);

        @Multipart
        @POST("/test")
        void test(@Part("image") TypedFile file1, @Part("image") TypedFile file2, @Part("description") String description, Callback<String> cb);

        @POST("/register/")
        void registerGCM(@Field("token") String registerationID,
                         @Field("deviceID") String deviceID,
                         @Field("deviceName") String deviceName,
                         @Field("email") String email);

        @POST("/message/{userID}")
        void sendMessage(@Path("userID") String user_id, @Field("message") String message);
    }

    // 모든 날짜 형식을 변환하는 메소드
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

    // TODO 본인정보조회
    public void selectUser(String user_id, Callback<User> callback) {
        serverUrl.selectUser(user_id, callback);
    }

    // TODO 회원가입
    public void insertUser(User user, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.insertUser(user, callback);
    }

    // TODO 회원수정
    public void updateUser(String user_id, User user, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.updateUser(user_id, user, callback);
    }

    // TODO 보유자전거등록
    public void insertBicycle(TypedFile file1, TypedFile file2, Bicycle bicycle, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.insertBicycle(
                file1,
                file2,
                bicycle.getType(),
                bicycle.getHeight(),
                bicycle.getTitle(),
                bicycle.getIntro(),
                bicycle.getPrice().hour,
                bicycle.getPrice().day,
                bicycle.getPrice().month,
                callback
        );
    }

    // TODO 보유자전거조회
    public void selectBicycle(Callback<List<Bicycle>> callback) {
        serverUrl.selectBicycle(callback);
    }

    // TODO 보유자전거수정
    public void updateBicycle(String bike_id, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.updateBicycle(bike_id, callback);
    }

    // TODO 보유자전거 활성화/비활성화
    public void updateBicycleOnOff(String bike_id, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.updateBicycleOnOff(bike_id, callback);
    }

//    // TODO 보유자전거삭제
    public void deleteBicycle(String bike_id, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.deleteBicycle(bike_id, callback);
    }

    // TODO 전체자전거조회
    public void selectAllBicycle(Callback<List<Bicycle>> callback) {
        serverUrl.selectAllBicycle(callback);
    }

    // TODO 자전거상세조회
    public void selectBicycleDetail(String bike_id, Callback<Bicycle> callback) {
        serverUrl.selectBicycleDetail(bike_id, callback);
    }

    // 로그인
    public void login(String email, String password, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.login(email, password, callback);
    }

    // TODO 내평가보기
    public void selectUserComment(Callback<List<Comment>> callback) {
        serverUrl.selectUserComment(callback);
    }

    // TODO 후기작성실패
    public void isFailInsertComment(Callback<ResponseForAndroidRequest> callback) {
        serverUrl.isFailInsertComment(callback);
    }

    // TODO 자전거후기작성
    public void insertBicycleComment(String bike_id, Callback<ResponseForAndroidRequest> callback) {
        serverUrl.insertBicycleComment(bike_id, callback);
    }

    // TODO 자전거후기보기
    public void selectBicycleComment(String bike_id, Callback<List<Comment>> callback) {
        serverUrl.selectBicycleComment(bike_id, callback);
    }

    // TODO 고객문의등록
    public void insertInquiry(Callback<ResponseForAndroidRequest> callback) {
        serverUrl.insertInquiry(callback);
    }

    public void test(TypedFile file1, TypedFile file2, Callback<String> cb) {
        serverUrl.test(file1, file2, "desc", cb);
    }

    public void registerGCM(String registerationID, String deviceID, String deviceName, String email) {
        serverUrl.registerGCM(registerationID, deviceID, deviceName, email);
    }

    public void sendMessage(String user_id, String message) {
        serverUrl.sendMessage(user_id, message);
    }

//    // TODO 본인정보조회
//    public void selectUser(Callback<User> callback) {
//        NetworkManager networkManager = NetworkManager.getInstance();
//        User user = new User();
//        user.set_id("563093ca49274fc454c610d5");
//        Call<User> result = networkManager.selectUser(user.get_id());
//        result.enqueue(callback);
//    }
}