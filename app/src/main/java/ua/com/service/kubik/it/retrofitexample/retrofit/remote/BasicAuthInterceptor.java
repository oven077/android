package ua.com.service.kubik.it.retrofitexample.retrofit.remote;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class BasicAuthInterceptor implements Interceptor{
    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
        credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .addHeader("Authorization", credentials)
                .build();
        return chain.proceed(authenticatedRequest);
    }

}
