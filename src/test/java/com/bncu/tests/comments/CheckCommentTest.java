package com.bncu.tests.comments;

import com.nbcu.api.CommentApi;
import com.nbcu.core.FakeUtil;
import com.nbcu.entities.pojos.CommentPojo;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CheckCommentTest {

    private static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";
    private CommentApi commentApi = new CommentApi();
    List<CommentPojo> comments = commentApi.getCommentsByPostId(FakeUtil.getRandomPostIdString());

    @Test
    public void checkRandomCommentsExists() {
        assertThat(comments.size())
                .as("Post should have at least one comment")
                .isGreaterThan(0);
    }

    @Test
    public void checkRandomCommentsEmailsFormats() {
        //decided to check email formats are valid in comments, as email has specific values
        comments.forEach(comment ->
                assertThat(comment.getEmail())
                        .as("Comment should have valid emails")
                        .matches(EMAIL_REGEX)
        );
    }

    @Test
    public void checkRandomCommentFields() {
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
