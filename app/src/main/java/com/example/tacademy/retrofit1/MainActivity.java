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

import com.example.tacademy.retrofit1.dao.Bike;
import com.example.tacademy.retrofit1.dao.Comment;
import com.example.tacademy.retrofit1.dao.GetObject;
import com.example.tacademy.retrofit1.dao.Image;
import com.example.tacademy.retrofit1.dao.Inquires;
import com.example.tacademy.retrofit1.dao.PostPutDeleteObject;
import com.example.tacademy.retrofit1.dao.Price;
import com.example.tacademy.retrofit1.dao.ReceiveObject;
import com.example.tacademy.retrofit1.dao.Result;
import com.example.tacademy.retrofit1.dao.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler(Looper.getMainLooper());
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.my_text);

//        Intent intent = new Intent(MainActivity.this, SubActivity.class);
//        startActivity(intent);

        // 실제 사용
        // 로그인
        if (Util.checkEmailRegularExpressions("admin@admin.com"))
            Log.i("result", "valid email form");
        else
            Log.e("error", "invalid email form");
        NetworkManager.getInstance().login("admin@admin.com", "dltjdrb", new Callback<ReceiveObject>() {
            @Override
            public void success(ReceiveObject receiveObject, Response response) {
                Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Msg : " + receiveObject.getMsg());
//                // 회원정보보기
//                String user_id = "5630904020ec3699522a999e";
//                NetworkManager.getInstance().selectUser(user_id, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        if (receiveObject != null) {
//                            Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess());
//                            List<Result> results = receiveObject.getResult();
//                            for (Result result : results) {
//                                Log.i("result", "onResponse Id : " + result.get_id() + ", Email : " + result.getEmail() + ", Name : " + result.getName());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 회원수정
//                User user = new User();
//                user.setName("이성규");
//                NetworkManager.getInstance().updateUser(user, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 보유자전거등록
//                File file1 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-15.png");
//                TypedFile typedFile1 = new TypedFile("image/png", file1);
//                File file2 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-17.png");
//                TypedFile typedFile2 = new TypedFile("image/png", file2);
//                Bike bike = new Bike();
//                bike.setType("03");
//                bike.setHeight("A");
//                bike.setTitle("자전거 제목2");
//                Price price = new Price();
//                price.setMonth(10000);
//                price.setDay(1000);
//                price.setHour(200);
//                bike.setPrice(price);
//                NetworkManager.getInstance().insertBicycle(typedFile1, typedFile2, bike, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 보유자전거조회
//                NetworkManager.getInstance().selectBicycle(new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess());
//                        List<Result> results = receiveObject.getResult();
//                        for (Result result : results) {
//                            Log.i("result", "onResponse Id : " + result.get_id() + ", Title : " + result.getTitle()
//                                    + ", getPrice : " + result.getPrice()
//                                    + ", Loc : " + result.getLoc());
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 보유자전거수정
//                String bicycle_id = "56442a9d9fc8a61a65789e35";
//                Bike bike = new Bike();
//                bike.setTitle("수정된 자전거 제목");
//                NetworkManager.getInstance().updateBicycle(bicycle_id, bike, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 보유자전거활성화/비활성화
//                String bicycle_id = "563c0aa79e89f9bc1bc226a2";
//                NetworkManager.getInstance().updateBicycleOnOff(bicycle_id, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 보유자전거삭제
//                String bicycle_id = "56442a9d9fc8a61a65789e35";
//                NetworkManager.getInstance().deleteBicycle(bicycle_id, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 전체자전거조회
//                NetworkManager.getInstance().selectAllBicycle(new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                        List<Result> results = receiveObject.getResult();
//                        for(Result result : results)
//                            Log.i("result", "onResponse Id : " + result.get_id() + ", Type : " + result.getType() + ", Height : " + result.getHeight() + ", Price.month : " + result.getPrice().getMonth());
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 자전거상세조회
//                String bicycle_id = "5644269a2117fac81bc1157e";
//                NetworkManager.getInstance().selectBicycleDetail(bicycle_id, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                        List<Result> results = receiveObject.getResult();
//                        for(Result result : results)
//                            Log.i("result", result.get_id());
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 내평가보기
//                NetworkManager.getInstance().selectUserComment(new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Success");
//                        List<Result> results = receiveObject.getResult();
//                        for (Result result : results)
//                            for (Comment comment : result.getComments())
//                                Log.i("result", "onResponse Id : " + comment.get_id() + ", Point : " + comment.getPoint() + ", Body : " + comment.getBody() + ", Writer Name : " + comment.getWriter().getName());
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                // 자전거후기작성
//                String bicycle_id = "5644792227d301be69973074";
////                List<Comment> comments = new ArrayList<Comment>();
////                comments.add(new Comment());
////                comments.get(0).setPoint(4);
////                comments.get(0).setBody("후기입니다.");
//                Comment comment = new Comment();
//                comment.setBody("후기");
//                comment.setPoint(2);
//                NetworkManager.getInstance().insertBicycleComment(bicycle_id, comment, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

//                String bicycle_id = "563c0bea7ba017ec1915c9cb";
//                // 자전거후기보기
//                NetworkManager.getInstance().selectBicycleComment(bicycle_id, new Callback<ReceiveObject>() {
//                    @Override
//                    public void success(ReceiveObject receiveObject, Response response) {
//                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//                        List<Result> results = receiveObject.getResult();
//                        for (Result result : results) {
//                            for(Comment comment : result.getComments()) {
//                                Log.i("result", "onResponse Point : " + comment.getPoint() + ", Body : " + comment.getBody() + ", writer.name : " + comment.getWriter().getName());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.e("error", "onFailure Error : " + error.toString());
//                    }
//                });

                // TODO 고객문의등록
                Inquires inquires = new Inquires();
                inquires.setTitle("문의");
                inquires.setBody("내용");
                NetworkManager.getInstance().insertInquiry(inquires, new Callback<ReceiveObject>() {
                    @Override
                    public void success(ReceiveObject receiveObject, Response response) {
                        Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("error", "onFailure Error : " + error.toString());
                    }
                });

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("error", "onFailure Error : " + error.toString());
            }
        });

//        // 회원가입
//        User user = new User();
//        user.setName("회원이름");
//        user.setEmail("member@name.com");
//        user.setPhone("010-000-0000");
//        user.setPassword("member");
//        NetworkManager.getInstance().insertUser(user, new Callback<ReceiveObject>() {
//            @Override
//            public void success(ReceiveObject receiveObject, Response response) {
//                Log.i("result", "onResponse Code : " + receiveObject.getCode() + ", Success : " + receiveObject.isSuccess() + ", Msg : " + receiveObject.getMsg() + ", Error : ");
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.e("error", "onFailure Error : " + error.toString());
//            }
//        });
    }

//    public void test() {
//        // DELME
//        File file1 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-15.png");
//        TypedFile typedFile1 = new TypedFile("multipart/form-data", file1);
//        File file2 = new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_2015-11-01-13-38-17.png");
//        TypedFile typedFile2 = new TypedFile("image/png", file2);
//
//        NetworkManager networkManager = NetworkManager.getInstance();
//        networkManager.test(typedFile1, typedFile2, new Callback<String>() {
//            @Override
//            public void success(String s, Response response) {
//                Log.i("result", "onResponse Success");
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.e("error", "onFailure Error : " + error.toString());
//            }
//        });
//    }
}