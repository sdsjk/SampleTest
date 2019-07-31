package camera.test.com.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity<B extends BasePersenter> extends Activity implements BaseView{

    protected B mPersenter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public  Activity getSelfActivity() {
        return this;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"加载中",Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this,"取消加载",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPersenter!=null) {
            mPersenter.dettachView();
        }
    }
}
