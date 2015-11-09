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

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

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
//        test();
    }

    // TODO 회원정보보기
    public void selectUser(String user_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.selectUser(user_id, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error: " + error.toString());
            }
        });
    }

    // TODO 회원가입
    public void insertUser(User user) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.insertUser(user, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error: " + error.toString());
            }
        });
    }

    // TODO 회원수정
    public void updateUser(String user_id, User user) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.updateUser(user_id, user, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error: " + error.toString());
            }
        });
    }

    // TODO 보유자전거등록
    public void insertBicycle(Bicycle bicycle) {
        // DELME
        File file1 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-15.png");
        TypedFile typedFile1 = new TypedFile("multipart/form-data", file1);
        File file2 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-17.png");
        TypedFile typedFile2 = new TypedFile("multipart/form-data", file2);

        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.insertBicycle(typedFile1, typedFile2, bicycle, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 보유자전거조회
    public void selectBicycle() {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.selectBicycle(new Callback<List<Bicycle>>() {
            @Override
            public void success(List<Bicycle> bicycles, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 보유자전거수정
    public void updateBicycle(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.updateBicycle(bicycle_id, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 보유자전거활성화/비활성화
    public void updateBicycleOnOff(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.updateBicycleOnOff(bicycle_id, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 보유자전거삭제
    public void deleteBicycle(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.deleteBicycle(bicycle_id, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 전체자전거조회
    public void selectAllBicycle() {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.selectAllBicycle(new Callback<List<Bicycle>>() {
            @Override
            public void success(List<Bicycle> bicycles, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 자전거상세조회
    public void selectBicycleDetail(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.selectBicycleDetail(bicycle_id, new Callback<Bicycle>() {
            @Override
            public void success(Bicycle bicycle, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // "admin@admin.com", "dltjdrb"
    // TODO 로그인 정규식 체크 http://www.java2go.net/java/java_regex.html, http://wintness.tistory.com/225
    public void login(String email, String password) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.login(email, password, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 내평가보기
    public void selectUserComment() {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.selectUserComment(new Callback<List<Comment>>() {
            @Override
            public void success(List<Comment> comments, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 후기작성실패
    public void isFailInsertComment(String email, String password) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.isFailInsertComment(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 자전거후기작성
    public void insertBicycleComment(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.insertBicycleComment(bicycle_id, new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 자전거후기보기
    public void selectBicycleComment(String bicycle_id) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.selectBicycleComment(bicycle_id, new Callback<List<Comment>>() {
            @Override
            public void success(List<Comment> comments, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    // TODO 고객문의등록
    public void insertInquiry() {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.insertInquiry(new Callback<ResponseForAndroidRequest>() {
            @Override
            public void success(ResponseForAndroidRequest responseForAndroidRequest, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }

    public void test() {
        // DELME
        File file1 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-15.png");
        TypedFile typedFile1 = new TypedFile("multipart/form-data", file1);
        File file2 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-17.png");
        TypedFile typedFile2 = new TypedFile("image/png", file2);

        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.test(typedFile1, typedFile2, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.i("result", "onResponse Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error:" + error.toString());
            }
        });
    }
}