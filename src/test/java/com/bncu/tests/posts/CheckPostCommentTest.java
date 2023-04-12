package com.bncu.tests.posts;

import com.nbcu.api.PostApi;
import com.nbcu.core.FakeUtil;
import com.nbcu.entities.pojos.CommentPojo;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CheckPostCommentTest {

    private static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";
    private PostApi postApi = new PostApi();
    //as expected to work exactly the same as for comments collected by post id - performed similar validations
    List<CommentPojo> comments = postApi.getPostComments(FakeUtil.getRandomPostIdString());

    @Test
    public void checkRandomPostCommentsExists() {
        assertThat(comments.size())
                .as("Post should have at least one comment")
                .isGreaterThan(0);
    }

    @Test
    public void checkRandomPostCommentsEmailsFormats() {
        //decided to check email formats are valid in comments, as email has specific values
        comments.forEach(comment ->
                assertThat(comment.getEmail())
                        .as("Comment should have valid emails")
                        .matches(EMAIL_REGEX)
        );
    }

    @Test
    public void checkRandomPostCommentFields() {
        //At least one comment exists in each post, that's why selected first
        CommentPojo comment = comments.get(0);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(comment.getPostId())
                    .as("Post should have non empty postId")
                    .isNotNull();
            softly.assertThat(comment.getName())
                    .as("Post should have not empty name")
                    .isNotEmpty();
            softly.assertThat(comment.getBody())
                    .as("Post should have not empty body")
                    .isNotEmpty();
            softly.assertThat(comment.getEmail())
                    .as("Post should have not empty email")
                    .isNotEmpty();
        });
    }
}
