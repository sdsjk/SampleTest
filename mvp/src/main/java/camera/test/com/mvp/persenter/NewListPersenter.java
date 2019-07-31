package camera.test.com.mvp.persenter;

import camera.test.com.mvp.base.BasePersenter;
import camera.test.com.mvp.base.NetWorkCallBack;
import camera.test.com.mvp.contact.ListNewContact;
import camera.test.com.mvp.model.NewListModel;

public class NewListPersenter extends BasePersenter<ListNewContact.NewListView> implements
        ListNewContact.NewListPersenter,NetWorkCallBack<String> {
    NewListModel newListModel;
    public NewListPersenter(){
         newListModel=new NewListModel(this);
    }


    @Override
    public void getNewListData() {
        getView().showLoading();
        newListModel.getNewListData();
    }

    @Override
    public void onSuccess(String data) {

//        getView().hideLoading();
        getView().ShowDataToListView(data);
    }

    @Override
    public void onFailure(String msg) {
        getView().hideLoading();
    }

    @Override
    public void onError() {
        getView().hideLoading();
    }

    @Override
    public void onComplete() {
        getView().hideLoading();
    }
}
