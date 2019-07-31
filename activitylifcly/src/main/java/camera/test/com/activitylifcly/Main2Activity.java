package camera.test.com.activitylifcly;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();

    }

    private void initView() {
        TextView my_tv2=findViewById(R.id.my_tv2);

        my_tv2.setText("Stack栈中Activity的个数:"+activityStackManager.getStackNum()+"Stack栈中顶层Activity是:"+activityStackManager.getStackTopActivity().getLocalClassName()+"Stack底层:"+activityStackManager.getBottomActivity().getLocalClassName());
    }
}
