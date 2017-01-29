package com.youtube.sorcjc.sga_mobile.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SgaApiAdapter {

    private static SgaApiService API_SERVICE;

    public static SgaApiService getApiService() {
        /*
        // Creating the interceptor, and setting the log level
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        */
        // Adding the interceptor to a client
        OkHttpClient httpClient = new OkHttpClient();
        // httpClient.interceptors().add(logging);

        // Define the base url by country
        String baseUrl = "http://192.168.173.1:50";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(buildGsonConverter())
                    .client(httpClient) // <-- using the log level
                    .build();
            API_SERVICE = retrofit.create(SgaApiService.class);
        }

        return API_SERVICE;
    }

    private static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        /*
        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(LoginResponse.class, new LoginDeserializer());
        gsonBuilder.registerTypeAdapter(ChatResponse.class, new ChatDeserializer());
        */
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }
    
}
