package com.bncu.tests.posts;

import com.nbcu.api.PostApi;
import com.nbcu.entities.pojos.CommentPojo;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CheckPostCommentTest {

    private PostApi postApi = new PostApi();

    @Test
    public void checkPostComments() {
        System.out.println("start test 8");
        List<CommentPojo> comments = postApi.getPostComments("4");
        comments.forEach(comment -> assertThat(comment.getEmail()).as("Comment shouldn't have empty emails").isNotNull());
        System.out.println("end test 8");
    }
}
