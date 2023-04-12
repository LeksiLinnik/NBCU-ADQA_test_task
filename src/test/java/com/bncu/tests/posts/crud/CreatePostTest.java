package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.entities.models.PostModel;
import com.nbcu.entities.pojos.PostPojo;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class CreatePostTest {

    private PostApi postApi = new PostApi();
    private PostPojo newPostPojo = PostModel.getPost();

    @Test
    public void checkPostCreation() {
        System.out.println("start test 4");
        PostPojo postPojo = postApi.createNewPost(newPostPojo);
        SoftAssertions.assertSoftly(softly -> {
            //following validations verifies if response comes with valid values
            softly.assertThat(postPojo.getUserId())
                    .as("New post is not created with user id")
                    .isEqualTo(newPostPojo.getUserId());
            softly.assertThat(postPojo.getTitle())
                    .as("New post is not created with title")
                    .isEqualTo(newPostPojo.getTitle());
            softly.assertThat(postPojo.getBody())
                    .as("New post is not created with body")
                    .isEqualTo(newPostPojo.getBody());
            //this validation checks if the newly created post is in the list
            //will fail because "resource will not be really updated on the server but it will be faked as if."
            softly.assertThat(postApi.getAllPosts())
                    .as("New post is not displayed in a list")
                    .contains(newPostPojo);
        });
        System.out.println("end test 4");
    }
}
