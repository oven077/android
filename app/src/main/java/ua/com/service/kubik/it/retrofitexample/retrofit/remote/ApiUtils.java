package ua.com.service.kubik.it.retrofitexample.retrofit.remote;

import ua.com.service.kubik.it.retrofitexample.metadata.App;

public class ApiUtils {
    private static String BASE_URL;
    private ApiUtils(){}

    public static ApiService getAPIService() {
        BASE_URL = App.getBaseURL();
        try {
            return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
        }catch (Exception e) {
            return null;
        }


    }

}
