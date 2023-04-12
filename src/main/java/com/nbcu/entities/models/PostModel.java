package com.nbcu.entities.models;

import com.nbcu.core.FakeUtil;
import com.nbcu.entities.pojos.PostPojo;

public class PostModel {

    public static PostPojo getPost() {
        return PostPojo.builder()
                .userId(FakeUtil.getRandomUser())
                .title(FakeUtil.getRandomTitle())
                .body(FakeUtil.getRandomBody())
                .build();
    }

    public static PostPojo getPost(int id) {
        PostPojo post = getPost();
        post.setId(id);
        return post;
    }
}
