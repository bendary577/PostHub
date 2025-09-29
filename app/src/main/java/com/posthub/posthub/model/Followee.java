package com.posthub.posthub.model;

import com.posthub.posthub.enums.SocialMediaPlatform;

import java.util.LinkedList;

public class Followee {

    private Long id;
    private String name;
    private String AccountURL;
    private SocialMediaPlatform platform;

    private LinkedList<Post> postsInPast24HoursList;

}
