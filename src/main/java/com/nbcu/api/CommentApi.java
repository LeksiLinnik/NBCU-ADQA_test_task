package com.nbcu.api;

import com.nbcu.entities.pojos.CommentPojo;
import java.util.List;
import java.util.Map;

public class CommentApi {

    private final CommentService service = new CommentService();

    public List<CommentPojo> getCommentsByPostId(String id) {
        return service.get(
                        Map.of("postId", id))
                .extract()
                .body()
                .jsonPath()
                .getList(".", CommentPojo.class);
    }
}
