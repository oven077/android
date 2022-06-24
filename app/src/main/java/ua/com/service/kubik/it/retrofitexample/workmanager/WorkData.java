package ua.com.service.kubik.it.retrofitexample.workmanager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;
import retrofit2.http.POST;
import ua.com.service.kubik.it.retrofitexample.metadata.App;
import ua.com.service.kubik.it.retrofitexample.retrofit.model.RetrofitMyData;
import ua.com.service.kubik.it.retrofitexample.retrofit.remote.ApiService;
import ua.com.service.kubik.it.retrofitexample.retrofit.remote.ApiUtils;

public class WorkData extends Worker {

    public WorkData(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Result resultWork = null;

        Bundle res = getData();
        Data output = new Data.Builder()
                .putString("textMessage", res.getString("error"))
                .build();


//        if ()) { // ошибка
//            resultWork = Result.failure(output);
//        }else {
         resultWork = Result.success(output);
        return resultWork;
    }

    private Bundle getData() {
        Bundle res = new Bundle();

        Response<List<RetrofitMyData>> response;
        ApiService mApiService = ApiUtils.getAPIService();

        if (mApiService == null) {
            res.putString("error", "Ошибка при инициализации клиента");
            return res;
        }

        try {

            response = mApiService.getListData(
                    App.getDefaultSecondURL())
                    .execute();

           /* HashMap<String, Integer> asd = new HashMap();
            asd.put("Test", 1);

            response = mApiService.putListData(
                    App.getDefaultSecondURL(),asd)
                    .execute();*/

            if (response.isSuccessful()) {
                List<RetrofitMyData> body = toDoSomething(response.body());
                response = mApiService.putListData(
                        App.getDefaultSecondURL(),body)
                        .execute();

            }else {
                String errorMessage = response.raw().toString() + "\n"
                        + response.errorBody().string();
                Log.d("MyTag", errorMessage);
            }
        }catch (IOException e) {
            Log.e("MyTag", e.getMessage());
            res.putString("error", e.getMessage());

        }


        return res;
    }






    private List<RetrofitMyData> toDoSomething(List<RetrofitMyData> body) {

        for (RetrofitMyData retrofitMyData : body) {
            int i = 0;

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            System.out.println(formatter.format(date));


            retrofitMyData.setName("Товар 22" + formatter.format(date));

            i++;




        }
        return body;
    }
}
