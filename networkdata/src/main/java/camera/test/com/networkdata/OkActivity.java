package camera.test.com.networkdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok);

    }

    public void postAync(View view){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder formBody=new FormBody.Builder();
        formBody.add("username","zhangsan");//传递键值对参数
        Request request=new Request.Builder()
                .post(formBody.build())
                .url("https://www.baidu.com/")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure::" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "onResponse::" + response.body().string());
            }
        });
    }

    public void getAync(View view) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        Request request = new Request.Builder()
                .url("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure::" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "onResponse::" + response.body().string());
            }
        });
    }

    public void getAsync(View view) throws IOException {
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
                        .build();
                Call call = okHttpClient.newCall(request);
                Response response = null;
                try {
                    response = call.execute();

                    if (response.isSuccessful()) {
                        Log.e("TAG", "response.code()==" + response.code());
                        Log.e("TAG", "response.message()==" + response.message());
                        Log.e("TAG", "res==" + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
