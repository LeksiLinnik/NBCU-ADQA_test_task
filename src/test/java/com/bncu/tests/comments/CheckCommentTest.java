package com.bncu.tests.comments;

import com.nbcu.api.CommentApi;
import com.nbcu.entities.pojos.CommentPojo;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;
@Execution(ExecutionMode.CONCURRENT)
public class CheckCommentTest {

    private CommentApi commentApi = new CommentApi();

    @Test
    public void checkPostComments() {
        System.out.println("start test 1");
        List<CommentPojo> comments = commentApi.getCommentsByPostId("5");
        comments.forEach(comment -> assertThat(comment.getEmail()).as("Comment shouldn't have empty emails").isNotNull());
        System.out.println("end test 1");
    }
}
