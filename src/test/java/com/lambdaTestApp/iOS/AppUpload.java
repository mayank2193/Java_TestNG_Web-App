package com.lambdaTestApp.iOS;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class AppUpload {
    public String upload() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("appFile", "apps/lambdatest.ipa",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("apps/lambdatest.ipa")))
                .addFormDataPart("name", "Demo")
                .build();
        Request request = new Request.Builder()
                .url("https://manual-api.lambdatest.com/app/upload/realDevice")
                .method("POST", body)
                .addHeader("Authorization", "Basic ZGVla3NoYXNhbHVndTp0RlU2ZzBjcmJHSjg1V0tDR3U0V1ZTNnJyUGxYOXdRdGM1U29KeG1rNDBvaVNWY0FjVQ==")
                .build();
        Response response = client.newCall(request).execute();
        String a = response.body().string().substring(77,108);
        System.out.println(a);
        return a;
    }
}

// String a[] = response.body().string().split(":");
//System.out.println(a[4]);