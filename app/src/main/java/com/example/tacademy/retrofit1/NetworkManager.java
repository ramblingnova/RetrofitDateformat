package com.example.tacademy.retrofit1;

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
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
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

/**
 * Created by Tacademy on 2015-11-04.
 */
public class NetworkManager {
    private static NetworkManager networkManager;
    Retrofit retrofit;
    ServerUrl serverUrl;

    private NetworkManager() {
        OkHttpClient client = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieManager);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerUrl.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        serverUrl = retrofit.create(ServerUrl.class);
    }

    public static NetworkManager getInstance() {
        if (networkManager == null)
            networkManager = new NetworkManager();
        return networkManager;
    }

    public interface ServerUrl {
        String baseUrl = "http://bikee.kr.pe";

//        // 후기 정보 받기
//        @GET("/comments/56372230faf540174aff15ed")
//        Call<List<Bicycle>> test1();
//
//        @POST("/bikes/users")
//        Call<ResponseForAndroidRequest> test2(@Body Bikes bikes);
//
//        // 보유자전거등록하기
//        @POST("/users/{userid}/bikes")
//        Call<ResponseForAndroidRequest> registerOwningBicycle(@Path("userid") String user_id, @Body OwningBicycle owningBicycle);

        // 본인정보조회 app.get('/users/:userId',users.profile)
        @GET("/users/{userId}")
        Call<User> selectUser(@Path("userId") String user_id);

        // 회원가입 app.post('/users', users.create)
        @POST("/users")
        Call<ResponseForAndroidRequest> insertUser(@Body User user);

        // TODO 회원수정 app.put('/users/:userId',userAuth,users.edit);
        @PUT("/users/{userId}")
        Call<ResponseForAndroidRequest> updateUser(@Path("userId") String user_id, @Body User user);

        // TODO 보유자전거등록 app.post('/bikes/users',auth.requiresLogin,bikes.create);
        //@FormUrlEncoded
        @Multipart
        @POST("/bikes/users")
        Call<ResponseForAndroidRequest> insertBicycle(@Part("image") RequestBody file,
                                                      @Part("image") RequestBody file2,
                                                      @Part("type") String type,
                                                      @Part("height") String height,
                                                      @Part("title") String title,
                                                      @Part("intro") String intro,
                                                      @Part("price.hour") int price_hour,
                                                      @Part("price.day") int price_day,
                                                      @Part("price.month") int price_month);

        @Multipart
        @POST("/test")
        Call<ResponseForAndroidRequest> test(@Part("image") RequestBody file);

        // TODO 보유자전거조회 app.get('/bikes/users',auth.requiresLogin,bikes.myList); 유저아이디는?
        @GET("/bikes/users")
        Call<List<Bicycle>> selectBicycle();

        // TODO 보유자전거수정 app.put('/bikes/users/:bikeId',bikeAuth,bikes.edit);
        @PUT("/bikes/users/{bikeId}")
        Call<ResponseForAndroidRequest> updateBicycle(@Path("bikeID") String bike_id);

        // TODO 보유자전거 활성화/비활성화 app.put('/bikes/active/:bikeId',bikeAuth,bikes.active);
        @PUT("/bikes/active/{bikeId}")
        Call<ResponseForAndroidRequest> updateBicycleOnOff(@Path("bikeID") String bike_id);

        // TODO 보유자전거삭제 app.delete('/bikes/users/:bikeId',bikeAuth,bikes.delete);
        @DELETE("/bikes/users/{bikeId}")
        Call<ResponseForAndroidRequest> deleteBicycle(@Path("bikeId") String bike_id);

        // TODO 전체자전거조회 app.get('/bikes',bikes.index);
        @GET("/bikes")
        Call<List<Bicycle>> selectAllBicycle();

        // TODO 자전거상세조회 app.get('/bikes/:bikeId/detail',bikes.detail);
        @GET("/bikes/{bikeId}/detail")
        Call<Bicycle> selectBicycleDetail(@Path("bikeId") String bike_id);

        // 로그인
        @FormUrlEncoded
        @POST("/users/session")
        Call<ResponseForAndroidRequest> login(@Field("email") String email, @Field("password") String password);

        // TODO 내평가보기 app.get('/comments',auth.requiresLogin,comments.show);
        @GET("/comments")
        Call<List<Comment>> selectUserComment();

        // TODO 후기작성실패 app.get('/authfail',comments.fail);
        @GET("/authfail")
        Call<ResponseForAndroidRequest> isFailInsertComment();

        // TODO 자전거후기작성 app.post('/comments/:bikeId',commentAuth,comments.create);
        @POST("/comments/{bikeId}")
        Call<ResponseForAndroidRequest> insertBicycleComment(@Path("bikeId") String bike_id);

        // TODO 자전거후기보기 app.get('/comments/:bikeId',comments.bike);
        @GET("/comments/{bikeId}")
        Call<List<Comment>> selectBicycleComment(@Path("bikeId") String bike_id);

        // TODO 고객문의등록 app.post('/inquiry',auth.requiresLogin,inquires.create);
        @POST("/inquiry")
        Call<ResponseForAndroidRequest> insertInquiry();


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

//    // 후기 정보 받는 메소드
//    public Call<List<Bicycle>> getBicycle() {
//        return serverUrl.test1();
//    }
//
//    //
//    public Call<ResponseForAndroidRequest> getComment(Bikes bikes) {
//        return serverUrl.test2(bikes);
//    }

    // 본인정보조회
    public Call<User> selectUser(String user_id) {
        return serverUrl.selectUser(user_id);
    }

    // TODO 회원가입
    public Call<ResponseForAndroidRequest> insertUser(User user) {
        return serverUrl.insertUser(user);
    }

    // TODO 회원수정
    public Call<ResponseForAndroidRequest> updateUser(String user_id, User user) {
        return serverUrl.updateUser(user_id, user);
    }

    // TODO 보유자전거등록
    public Call<ResponseForAndroidRequest> insertBicycle(
            RequestBody file,
            RequestBody file2,
            Bicycle bicycle) {
        return serverUrl.insertBicycle(
                file,
                file2,
                bicycle.getType(),
                bicycle.getHeight(),
                bicycle.getTitle(),
                bicycle.getIntro(),
                bicycle.getPrice().hour,
                bicycle.getPrice().day,
                bicycle.getPrice().month
        );
    }

    // TODO 보유자전거조회
    public Call<List<Bicycle>> selectBicycle() {
        return serverUrl.selectBicycle();
    }

    // TODO 보유자전거수정
    public Call<ResponseForAndroidRequest> updateBicycle(String bike_id) {
        return serverUrl.updateBicycle(bike_id);
    }

    // TODO 보유자전거 활성화/비활성화
    public Call<ResponseForAndroidRequest> updateBicycleOnOff(String bike_id) {
        return serverUrl.updateBicycleOnOff(bike_id);
    }

    // TODO 보유자전거삭제
    public Call<ResponseForAndroidRequest> deleteBicycle(String bike_id) {
        return serverUrl.deleteBicycle(bike_id);
    }

    // TODO 전체자전거조회
    public Call<List<Bicycle>> selectAllBicycle() {
        return serverUrl.selectAllBicycle();
    }

    // TODO 자전거상세조회
    Call<Bicycle> selectBicycleDetail(String bike_id) {
        return serverUrl.selectBicycleDetail(bike_id);
    }

    // 로그인
    public Call<ResponseForAndroidRequest> login(String email, String password) {
        return serverUrl.login(email, password);
    }

    // TODO 내평가보기
    public Call<List<Comment>> selectUserComment() {
        return serverUrl.selectUserComment();
    }

    // TODO 후기작성실패
    public Call<ResponseForAndroidRequest> isFailInsertComment() {
        return serverUrl.isFailInsertComment();
    }

    // TODO 자전거후기작성
    public Call<ResponseForAndroidRequest> insertBicycleComment(String bike_id) {
        return serverUrl.insertBicycleComment(bike_id);
    }

    // TODO 자전거후기보기
    public Call<List<Comment>> selectBicycleComment(String bike_id) {
        return serverUrl.selectBicycleComment(bike_id);
    }

    // TODO 고객문의등록
    public Call<ResponseForAndroidRequest> insertInquiry() {
        return insertInquiry();
    }

    // 본인정보조회
    public void selectUser(Callback<User> callback) {
        NetworkManager networkManager = NetworkManager.getInstance();
        User user = new User();
        user.set_id("563093ca49274fc454c610d5");
        Call<User> result = networkManager.selectUser(user.get_id());
        result.enqueue(callback);
    }

    // DELEME 시험용
    public Call<ResponseForAndroidRequest> test(RequestBody file) {
        file.toString();
        return serverUrl.test(file);
    }
}