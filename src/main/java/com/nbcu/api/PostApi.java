package com.nbcu.api;

import com.nbcu.core.JsonParser;
import com.nbcu.entities.pojos.CommentPojo;
import com.nbcu.entities.pojos.PostPojo;
import java.util.List;

public class PostApi {

    private final PostService service = new PostService();

    public PostPojo getPostById(String id) {
        return service.get(id)
                .extract()
                .as(PostPojo.class);
    }

    public List<PostPojo> getAllPosts() {
        return service.get()
                .extract()
                .body()
                .jsonPath()
                .getList(".", PostPojo.class);
    }

    public PostPojo createNewPost(PostPojo post) {
        return service.post(JsonParser.toJson(post))
                .extract()
                .as(PostPojo.class);
    }

    public void deletePostById(String id) {
        service.delete(id);
    }

    public PostPojo replacePost(PostPojo post, String id) {
        return service.put(JsonParser.toJson(post), id)
                .extract()
                .as(PostPojo.class);
    }


    public PostPojo updatePost(PostPojo post, String id) {
        return service.patch(JsonParser.toJson(post), id)
                .extract()
                .as(PostPojo.class);
    }

    public List<CommentPojo> getPostComments(String postId) {
        return service.get(postId, "comments")
                .extract()
                .body()
                .jsonPath()
                .getList(".", CommentPojo.class);
    }
}
