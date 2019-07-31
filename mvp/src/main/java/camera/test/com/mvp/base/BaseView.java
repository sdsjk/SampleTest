package camera.test.com.mvp.base;

import android.app.Activity;

public interface  BaseView {

    Activity  getSelfActivity();

   void showLoading();

   void hideLoading();

}
