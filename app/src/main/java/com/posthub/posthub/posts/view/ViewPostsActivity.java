package com.posthub.posthub.posts.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.posthub.posthub.R;
import com.posthub.posthub.enums.SocialMediaPlatform;
import com.posthub.posthub.model.Post;
import com.posthub.posthub.service.ApiService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPostsActivity extends AppCompatActivity {

    ViewPostsAdapter viewPostsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_view_posts);

        Toolbar viewPostsToolBar = findViewById(R.id.ViewPostsToolBar);
        setSupportActionBar(viewPostsToolBar);

        RecyclerView recyclerView = findViewById(R.id.viewPostsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //-----------------------------------------------------------
        try{
            String pageId = "100015171030262";
            String url = "https://us-central1-posthubfirebase.cloudfunctions.net/getPublicPagePosts/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService api = retrofit.create(ApiService.class);

            api.getPosts(pageId).enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Post> posts = response.body();
                        Log.e("API_INFO", response.body().toString());
//                    adapter = new PostAdapter(posts);
//                    recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    Log.e("API_ERROR", t.getMessage());
                }
            });

        }catch(Exception e){
            Log.e("API_ERROR", e.getMessage());
        }

        //TODO:get this part dynamically from firebase ---------------
        LinkedList<Post> postsList = new LinkedList<>();
        Post firstPost = new Post();
        firstPost.setPublisherName("Mohamed Bendary");
        firstPost.setContent("this is my first post !");
        firstPost.setPublishedAt("20250921 14:05:49");
        firstPost.setPlatform(SocialMediaPlatform.FACEBOOK);

        Post secondPost = new Post();
        secondPost.setPublisherName("Mohamed Bendary");
        secondPost.setContent("this is my second post");
        secondPost.setPublishedAt("20250921 14:05:49");
        secondPost.setPlatform(SocialMediaPlatform.TWITTER);

        Post thirsPost = new Post();
        thirsPost.setPublisherName("Mohamed Bendary");
        thirsPost.setContent("this is my third post");
        thirsPost.setPublishedAt("20250921 14:05:49");
        thirsPost.setPlatform(SocialMediaPlatform.INSTAGRAM);

        postsList.add(firstPost);
        postsList.add(secondPost);
        postsList.add(thirsPost);
        //-----------------------------------------------------

        viewPostsAdapter = new ViewPostsAdapter(this, postsList);
        recyclerView.setAdapter(viewPostsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_posts, menu);
        return true;
    }

}