package camera.test.com.mvp.model;

import camera.test.com.mvp.base.NetWorkCallBack;
import camera.test.com.mvp.contact.ListNewContact;

public class NewListModel implements ListNewContact.NewListModle {
    private NetWorkCallBack mNetWorkCallBack;
     public  NewListModel(NetWorkCallBack netWorkCallBack){
         mNetWorkCallBack=netWorkCallBack;
     }

    @Override
    public void getNewListData() {
        mNetWorkCallBack.onSuccess("请求数据成功啦.......");
    }


}
