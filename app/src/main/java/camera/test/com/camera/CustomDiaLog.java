package camera.test.com.camera;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Created by zhang on 2018/12/19.
 */

public class CustomDiaLog extends Dialog {
    public CustomDiaLog(@NonNull Context context) {
        super(context);
    }

    public CustomDiaLog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomDiaLog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
    }
}
