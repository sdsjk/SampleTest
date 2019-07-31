package camera.test.com.networkdata.Api;

import com.google.gson.JsonObject;

import java.util.HashMap;

import camera.test.com.networkdata.bean.NewListBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NetWorkApi {

    @GET("getJoke")
    Call<NewListBean> getNewList(@Query("page") String page,@Query("count") String conut,@Query("type") String type);
    @GET("getJoke")
    Call<NewListBean> getNewList2(@QueryMap HashMap<String ,Object> hashMap);

    @GET("getJoke")
    Call<JsonObject> getNewList3(@QueryMap HashMap<String ,Object> hashMap);
}
