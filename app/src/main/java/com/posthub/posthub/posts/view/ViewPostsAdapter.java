package com.posthub.posthub.posts.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.posthub.posthub.R;
import com.posthub.posthub.model.Post;

import java.util.LinkedList;

public class ViewPostsAdapter extends RecyclerView.Adapter<ViewPostsAdapter.PostsViewHolder> {

    Context context;
    LinkedList<Post> postsList;

    public ViewPostsAdapter(ViewPostsActivity viewPostsActivity, LinkedList<Post> posts) {
        context = viewPostsActivity;
        postsList = posts;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.post, parent,false);
       return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Post post = postsList.get(position);
        holder.publisherName.setText(post.getPublisherName());
        holder.content.setText(post.getContent());
        holder.publishedAt.setText(post.getPublishedAt());
        holder.platform.setText(String.valueOf(post.getPlatform())); //TODO:change
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder{

         TextView publisherName;
         TextView content;
         TextView publishedAt;
         TextView platform;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            publisherName = itemView.findViewById(R.id.postPublisherName);
            content = itemView.findViewById(R.id.postContent);
            publishedAt = itemView.findViewById(R.id.publishedAt);
            platform = itemView.findViewById(R.id.platform);
        }
    }
}
