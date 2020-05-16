package com.example.coolq.utils;

import lombok.Builder;
import lombok.Data;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HttpUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Data
    @Builder
    public static class HttpRes {
        private Integer httpCode;
        private String header;
        private String data;
        private String req;
        private String url;
    }

    public static HttpRes get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return HttpRes.builder()
                    .httpCode(response.code())
                    .header(response.headers().toString())
                    .data(Objects.requireNonNull(response.body()).string())
                    .url(url)
                    .build();
        }
    }

    public static HttpRes post(String url, String data) throws IOException {
        RequestBody body = RequestBody.create(data, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return HttpRes.builder()
                    .httpCode(response.code())
                    .header(response.headers().toString())
                    .data(Objects.requireNonNull(response.body()).string())
                    .url(url)
                    .req(data)
                    .build();
        }
    }

    public static HttpRes post(String url, File file) throws IOException {

        MediaType mediaTypePng = MediaType.parse("image/png");

        RequestBody fileBody = MultipartBody.create(mediaTypePng, file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("media", file.getName(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return HttpRes.builder()
                    .httpCode(response.code())
                    .header(response.headers().toString())
                    .data(Objects.requireNonNull(response.body()).string())
                    .url(url)
                    .build();
        }
    }

}
