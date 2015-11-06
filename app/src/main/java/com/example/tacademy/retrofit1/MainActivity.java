package com.example.tacademy.retrofit1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eowise.imagemagick.ImageMagickPlugin;
import com.example.tacademy.retrofit1.dao.Bicycle;
import com.example.tacademy.retrofit1.dao.Comment;
import com.example.tacademy.retrofit1.dao.Image;
import com.example.tacademy.retrofit1.dao.Inquiry;
import com.example.tacademy.retrofit1.dao.Price;
import com.example.tacademy.retrofit1.dao.ResponseForAndroidRequest;
import com.example.tacademy.retrofit1.dao.User;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, SubActivity.class);
//        startActivity(intent);

//        insertUser("", "", "", "");
//        login("", "");
        test();
    }

//    new Callback<User>() {
//        @Override
//        public void onResponse(Response<User> response, Retrofit retrofit) {
//            User user = response.body();
//            if (user != null) {
//                Log.i("result", "onResponse onResponse _id:" + user.get_id()
//                        + " updatedAt:" + user.getUpdatedAt()
//                        + " createdAt:" + user.getCreatedAt()
//                        + " authToken:" + user.getAuthToken()
//                        + " salt:" + user.getSalt()
//                        + " hashed_password:" + user.getHashed_password()
//                        + " provider:" + user.getProvider()
//                        + " email:" + user.getEmail()
//                        + " name:" + user.getName());
//                rUser = new User();
//                rUser.set_id(user.get_id());
//                rUser.setUpdatedAt(user.getUpdatedAt());
//                rUser.setCreatedAt(user.getCreatedAt());
//                rUser.setAuthToken(user.getAuthToken());
//                rUser.setSalt(user.getSalt());
//                rUser.setHashed_password(user.getHashed_password());
//                rUser.setProvider(user.getProvider());
//                rUser.setEmail(user.getEmail());
//                rUser.setName(user.getName());
//            } else
//                Log.i("result", "onResponse onResponse Object: NULL");
//        }
//
//        @Override
//        public void onFailure(Throwable t) {
//            Log.e("error", "onResponse onFailure Error:" + t.toString());
//        }
//    }

    // 회원가입
    public void insertUser(String name, String email, String phone, String hashed_password) {
        User user = new User();
        user.setName("이름");
        user.setEmail("1234567@email.com");
        user.setPhone("010-234-1234");
        user.setPassword("12345");

        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.insertUser(user);
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 회원수정
    public void updateUser(String name, String email, String phone) {
        User user = new User();
        user.setName("이름");
        user.setEmail("email@email.com");
//        user.setPhone("010-0000-0000");

        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.updateUser(user.get_id(), user);
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 보유자전거등록
    public void insertBicycle() {
        NetworkManager networkManager = NetworkManager.getInstance();

        Bicycle b = new Bicycle();
        b.setType("03");
        b.setHeight("01");
        b.setTitle("즐거운 자전거");
        b.setIntro("소개자전거");
        Price price = new Price();
        price.hour = 100;
        price.day = 1000;
        price.month = 1111;
        b.setPrice(price);
        File file = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-15.png");
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        networkManager.insertBicycle(
                requestBody,
                requestBody,
                b).enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }


    // TODO 보유자전거조회
    public void selectBicycle(String user_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<List<Bicycle>> result = networkManager.selectBicycle();
        result.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Response<List<Bicycle>> response, Retrofit retrofit) {
                List<Bicycle> bicycles = response.body();
                if (bicycles != null)
                    for (Bicycle bicycle : bicycles)
                        Log.i("result", "onResponse onResponse _id:" + bicycle.get_id()
                                + " user:" + bicycle.getUser()
                                + " type:" + bicycle.getType()
                                + " comments:" + bicycle.getComponents()
                                + " height:" + bicycle.getHeight()
                                + " smartlock:" + bicycle.isSmartlock()
                                + " Loc:" + bicycle.getLoc()
                                + " intro:" + bicycle.getIntro()
                                + " images:" + bicycle.getImages()
                                + " Price:" + bicycle.getPrice()
                                + " delflag:" + bicycle.isDelflag()
                                + " createdAt:" + bicycle.getCreatedAt()
                                + " updateAt:" + bicycle.getUpdatedAt());
                else
                    Log.i("result", "onResponse Object: NULL");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 보유자전거수정
    public void updateBicycle(String user_id, String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.updateBicycle(bicycle_id);
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 보유자전거활성화/비활성화
    public void updateBicycleOnOff(String user_id, String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.updateBicycleOnOff(bicycle_id);
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 보유자전거삭제
    public void deleteBicycle(String user_id, String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.deleteBicycle(bicycle_id);
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 전체자전거조회
    public void selectAllBicycle() {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<List<Bicycle>> result = networkManager.selectAllBicycle();
        result.enqueue(new Callback<List<Bicycle>>() {
            @Override
            public void onResponse(Response<List<Bicycle>> response, Retrofit retrofit) {
                List<Bicycle> bicycles = response.body();
                if (bicycles != null)
                    for (Bicycle bicycle : bicycles)
                        Log.i("result", "onResponse onResponse _id:" + bicycle.get_id()
                                + " user:" + bicycle.getUser()
                                + " type:" + bicycle.getType()
                                + " comments:" + bicycle.getComponents()
                                + " height:" + bicycle.getHeight()
                                + " smartlock:" + bicycle.isSmartlock()
                                + " Loc:" + bicycle.getLoc()
                                + " intro:" + bicycle.getIntro()
                                + " images:" + bicycle.getImages()
                                + " Price:" + bicycle.getPrice()
                                + " delflag:" + bicycle.isDelflag()
                                + " createdAt:" + bicycle.getCreatedAt()
                                + " updateAt:" + bicycle.getUpdatedAt());
                else
                    Log.i("result", "onResponse Object: NULL");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 자전거상세조회
    public void selectBicycleDetail(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<Bicycle> result = networkManager.selectBicycleDetail(bicycle_id);
        result.enqueue(new Callback<Bicycle>() {
            @Override
            public void onResponse(Response<Bicycle> response, Retrofit retrofit) {
                Bicycle bicycle = response.body();
                if (bicycle != null)
                    Log.i("result", "onResponse onResponse _id:" + bicycle.get_id()
                            + " user:" + bicycle.getUser()
                            + " type:" + bicycle.getType()
                            + " comments:" + bicycle.getComponents()
                            + " height:" + bicycle.getHeight()
                            + " smartlock:" + bicycle.isSmartlock()
                            + " Loc:" + bicycle.getLoc()
                            + " intro:" + bicycle.getIntro()
                            + " images:" + bicycle.getImages()
                            + " Price:" + bicycle.getPrice()
                            + " delflag:" + bicycle.isDelflag()
                            + " createdAt:" + bicycle.getCreatedAt()
                            + " updateAt:" + bicycle.getUpdatedAt());
                else
                    Log.i("result", "onResponse Object: NULL");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 로그인 정규식 체크 http://www.java2go.net/java/java_regex.html, http://wintness.tistory.com/225
    public void login(String email, String password) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.login("admin@admin.com", "dltjdrb");
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
//                insertBicycle();
                test();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 내평가보기
    public void selectUserComment() {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<List<Comment>> result = networkManager.selectUserComment();
        result.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Response<List<Comment>> response, Retrofit retrofit) {
                List<Comment> comments = response.body();
                if (comments != null)
                    for (Comment comment : comments)
                        Log.i("result", "onResponse onResponse user:" + comment.getUser()
                                + " _id::" + comment.get_id()
                                + " createAt:" + comment.getCreatedAt()
                                + " body:" + comment.getBody()
                                + " point:" + comment.getPoint());
                Log.i("result", "onResponse Object: NULL");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 후기작성실패패
    public void isFailInsertComment(String email, String password) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.isFailInsertComment();
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 자전거후기작성
    public void insertBicycleComment(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.insertBicycleComment(bicycle_id);
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 자전거후기보기
    public void selectBicycleComment(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<List<Comment>> result = networkManager.selectBicycleComment(bicycle_id);
        result.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Response<List<Comment>> response, Retrofit retrofit) {
                List<Comment> comments = response.body();
                if (comments != null)
                    for (Comment comment : comments)
                        Log.i("result", "onResponse onResponse user:" + comment.getUser()
                                + " _id::" + comment.get_id()
                                + " createAt:" + comment.getCreatedAt()
                                + " body:" + comment.getBody()
                                + " point:" + comment.getPoint());
                else
                    Log.i("result", "onResponse Object: NULL");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    // TODO 고객문의등록
    public void insertInquiry() {
        NetworkManager networkManager = NetworkManager.getInstance();
        Call<ResponseForAndroidRequest> result = networkManager.insertInquiry();
        result.enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }

    public void test() {
        NetworkManager networkManager = NetworkManager.getInstance();

        File file = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-15.png");
//        Log.i("test", "test file:" + file.toString());
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/octet-stream"), file);
        networkManager.test(requestBody).enqueue(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void onResponse(Response<ResponseForAndroidRequest> response, Retrofit retrofit) {
                ResponseForAndroidRequest responseForAndroidRequest = response.body();
                if (responseForAndroidRequest != null)
                    Log.i("result", "onResponse Success:" + responseForAndroidRequest.getSuccess() + " / Result:" + responseForAndroidRequest.getResult());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "onFailure Error:" + t.toString());
            }
        });
    }
}