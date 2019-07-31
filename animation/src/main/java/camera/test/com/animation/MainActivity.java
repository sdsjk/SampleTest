package camera.test.com.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListView();
    }

    private void initListView() {
        ListView listView = findViewById(R.id.my_lv);
        TranslateAnimation translateAnimation=new TranslateAnimation(0,100,0,0);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatCount(0);

        LayoutAnimationController layoutAnimationController=new LayoutAnimationController(translateAnimation);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        MyAdapter myAdapter = new MyAdapter(arrayList);
        listView.setAdapter(myAdapter);
        listView.setLayoutAnimation(layoutAnimationController);


    }

    class MyAdapter extends BaseAdapter {
        ArrayList<String> arrayList;

        public MyAdapter(ArrayList<String> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this,R.layout.list_item, null);
            TextView textView = convertView.findViewById(R.id.item_tv);
            textView.setText(arrayList.get(position));
            return convertView;
        }
    }
}
