package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.entities.models.PostModel;
import com.nbcu.entities.pojos.PostPojo;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CreatePostTest {

    private PostApi postApi = new PostApi();
    private PostPojo newPostPojo = PostModel.getPost();
    private PostPojo responsePostPojo = postApi.createNewPost(newPostPojo);

    /*
    Following test verifies if response on new post creation comes with valid values. Server doesn't allow to add
    new data, it is fakes as if, and can be verified in response.
     */
    @Test
    public void checkPostCreationResponse() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(responsePostPojo.getUserId())
                    .as("New post is not created with user id")
                    .isEqualTo(newPostPojo.getUserId());
            softly.assertThat(responsePostPojo.getTitle())
                    .as("New post is not created with title")
                    .isEqualTo(newPostPojo.getTitle());
            softly.assertThat(responsePostPojo.getBody())
                    .as("New post is not created with body")
                    .isEqualTo(newPostPojo.getBody());
        });
    }

    /*
    Following test verifies if the newly created post is in the list, but, as server doesn't allow to add new data,
    despite the fact it is fakes as if, it is decided to verify it is not created
     */
    @Test
    public void checkCreatedPostIsInList() {
        assertThat(postApi.getAllPosts())
                    .as("New post is not displayed in a list")
                    .doesNotContain(newPostPojo);
    }
}
