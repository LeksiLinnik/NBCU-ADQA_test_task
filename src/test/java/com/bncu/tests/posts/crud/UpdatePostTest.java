package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.core.FakeUtil;
import com.nbcu.entities.models.PostModel;
import com.nbcu.entities.pojos.PostPojo;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class UpdatePostTest {

    private PostApi postApi = new PostApi();
    private PostPojo postPojo = postApi.getPostById(FakeUtil.getRandomPostIdString());
    private PostPojo newPostPojo = PostModel.getPost();
    private PostPojo updatedPostPojo;
    private PostPojo replacedPostPojo;

    @Test
    public void checkPostUpdatedResponse() {
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
    }

    //due to api restrictions of updating - verified that updated post is not in the list
    @Test
    public void checkPostCanBeUpdated() {
        postPojo.setBody(newPostPojo.getBody());
        updatedPostPojo = postApi.updatePost(postPojo, String.valueOf(postPojo.getId()));
        assertThat(postApi.getAllPosts())
                .as("Post is not expected to be in a list")
                .doesNotContain(updatedPostPojo);
    }

    @Test
    public void checkPostReplacedResponse() {
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
    }

    //due to api restrictions of updating - verified that replaced post is not in the list
    @Test
    public void checkPostCanBeReplaced() {
        replacedPostPojo = postApi.replacePost(newPostPojo, String.valueOf(postPojo.getId()));
        assertThat(postApi.getAllPosts())
                .as("Post is not expected to be in a list")
                .doesNotContain(replacedPostPojo);
    }
}
