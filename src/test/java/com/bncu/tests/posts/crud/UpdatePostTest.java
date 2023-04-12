package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.entities.models.PostModel;
import com.nbcu.entities.pojos.PostPojo;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class UpdatePostTest {

    private PostApi postApi = new PostApi();
    private PostPojo postPojo = postApi.getPostById("2");
    private PostPojo newPostPojo = PostModel.getPost();
    private PostPojo updatedPostPojo;
    private PostPojo replacedPostPojo;

    @Test
    public void checkPostCanBeUpdated() {
        System.out.println("start test 7.1");
        postPojo.setBody(newPostPojo.getBody());
        updatedPostPojo = postApi.updatePost(postPojo, String.valueOf(postPojo.getId()));
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(updatedPostPojo.getId())
                    .as("Post is not expected to have updated id")
                    .isEqualTo(postPojo.getId());
            softly.assertThat(updatedPostPojo.getUserId())
                    .as("Post is not expected to have updated user id")
                    .isEqualTo(postPojo.getUserId());
            softly.assertThat(updatedPostPojo.getTitle())
                    .as("Post is not expected to have updated title")
                    .isEqualTo(postPojo.getTitle());
            softly.assertThat(updatedPostPojo.getBody())
                    .as("Post is expected to have updated body")
                    .isEqualTo(newPostPojo.getBody());
        });
        System.out.println("end test 7.1");
    }

    @Test
    public void checkPostCanBeReplaced() {
        System.out.println("start test 7.2");
        replacedPostPojo = postApi.replacePost(newPostPojo, String.valueOf(postPojo.getId()));
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(replacedPostPojo.getId())
                    .as("Post is not expected to have updated id")
                    .isEqualTo(postPojo.getId());
            softly.assertThat(replacedPostPojo.getUserId())
                    .as("Post is expected to have updated user id")
                    .isEqualTo(newPostPojo.getUserId());
            softly.assertThat(replacedPostPojo.getTitle())
                    .as("Post is expected to have updated title")
                    .isEqualTo(newPostPojo.getTitle());
            softly.assertThat(replacedPostPojo.getBody())
                    .as("Post is expected to have updated body")
                    .isEqualTo(newPostPojo.getBody());
        });
        System.out.println("end test 7.2");
    }
}
