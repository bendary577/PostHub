package com.posthub.posthub.enums;

public enum SocialMediaPlatform {
    FACEBOOK(1),
    INSTAGRAM(2),
    TWITTER(3),
    TIKTOK(4),
    LINKEDIN(5);

    private final int code;

    SocialMediaPlatform(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
