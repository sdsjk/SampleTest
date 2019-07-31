package camera.test.com.mvp.contact;

import camera.test.com.mvp.base.BaseView;

public interface ListNewContact {
    interface NewListModle{
            void  getNewListData();
    }

    interface NewListView extends BaseView{
        void ShowDataToListView(String data);
    }

    interface NewListPersenter {
        void getNewListData();
    }
}
