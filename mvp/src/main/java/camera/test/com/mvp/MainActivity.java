package camera.test.com.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import camera.test.com.mvp.base.BaseActivity;
import camera.test.com.mvp.contact.ListNewContact;
import camera.test.com.mvp.persenter.NewListPersenter;

public class MainActivity extends BaseActivity<NewListPersenter> implements ListNewContact.NewListView {
    NewListPersenter newListPersenter;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newListPersenter = new NewListPersenter();
        newListPersenter.attachView(this);
        textView=findViewById(R.id.myView);
    }

    public void getData(View v) {
        newListPersenter.getNewListData();

    }

    @Override
    public void ShowDataToListView(String data) {
        textView.setText("====="+data);

    }
}
