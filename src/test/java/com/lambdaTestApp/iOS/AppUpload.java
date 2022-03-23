package com.lambdaTestApp.iOS;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class AppUpload {
    public static String userName = System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY");
    public static void upload1() throws IOException {
        String credential = Credentials.basic(userName, accessKey);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("appFile", "apps/lambdatest.ipa",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("apps/lambdatest.ipa")))
                .addFormDataPart("custom_id","iOS_appurl")
                .build();
        Request request = new Request.Builder()
                .url("https://manual-api.lambdatest.com/app/upload/realDevice")
                .method("POST", body)
                .addHeader("Authorization", credential)
                .build();
        Response response = client.newCall(request).execute();
    }
}
