package com.posthub.posthub.service;

import com.posthub.posthub.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("posts")
    Call<List<Post>> getPosts(@Query("pageId") String pageId);
}
