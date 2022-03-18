package com.lambdaTestApp.android;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class AppUploadAndroid {
    public String upload() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("appFile","apps/WikipediaSample.apk",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("apps/WikipediaSample.apk")))
                .build();
        Request request = new Request.Builder()
                .url("https://manual-api.lambdatest.com/app/upload/realDevice")
                .method("POST", body)
                .addHeader("Authorization", "Basic ZGVla3NoYXNhbHVndTp0RlU2ZzBjcmJHSjg1V0tDR3U0V1ZTNnJyUGxYOXdRdGM1U29KeG1rNDBvaVNWY0FjVQ==")
                .build();
        Response response = client.newCall(request).execute();
        String a = response.body().string().substring(96,127);
        System.out.println(a);
        return a;
    }
}
