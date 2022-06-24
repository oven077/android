package ua.com.service.kubik.it.retrofitexample.retrofit.remote;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ua.com.service.kubik.it.retrofitexample.retrofit.model.RetrofitMyData;

public interface ApiService {
    @GET("{secondURL}/{id}/getMyData")
    Call<List<RetrofitMyData>> getListData(@Path(value = "secondURL", encoded = true) String secondURL);

    @POST("{secondURL}/json/{data}")
    Call<List<RetrofitMyData>> putListData(@Path(value = "secondURL", encoded = true) String secondURL, @Body List data);


}
