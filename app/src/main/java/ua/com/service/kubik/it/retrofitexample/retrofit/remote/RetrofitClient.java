package ua.com.service.kubik.it.retrofitexample.retrofit.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// синглтон
public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String user = "Admin";
    private static String pwd = "1";

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MILLISECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new BasicAuthInterceptor(user, pwd))
                        .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();



        }
        return retrofit;
    }

}
