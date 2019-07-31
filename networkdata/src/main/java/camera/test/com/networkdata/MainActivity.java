package camera.test.com.networkdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import camera.test.com.networkdata.okutils.RxActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void ok(View v) {
        startActivity(new Intent(this,OkActivity.class));
    }

    public void rx(View view) {
        startActivity(new Intent(this,RxActivity.class));
    }

    public void rtf(View view) {
        startActivity(new Intent(this,RtActivity.class));
    }
}
