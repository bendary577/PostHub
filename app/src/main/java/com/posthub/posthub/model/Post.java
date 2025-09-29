package com.posthub.posthub.model;

import com.posthub.posthub.enums.SocialMediaPlatform;

public class Post {
    private String publisherName;
    private String content;
    private String publishedAt;

    private SocialMediaPlatform platform;

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public SocialMediaPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(SocialMediaPlatform platform) {
        this.platform = platform;
    }
}
