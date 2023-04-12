package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.entities.models.PostModel;
import com.nbcu.entities.pojos.PostPojo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;
@Execution(ExecutionMode.CONCURRENT)
public class CreatePostNegTest {

    private PostApi postApi = new PostApi();
    private PostPojo newPostPojo = PostModel.getPost();

    //The following is supposed to fail on creation, but not failed because api allows creation with potentially invalid data

    @Test
    public void checkPostNotCreatedWithEmptyUser() {
        System.out.println("start test 3.1");
        newPostPojo.setUserId(null);

        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                    .as("Non created post is not expected to be in a list")
                    .doesNotContain(newPostPojo);
        System.out.println("end test 3.1");
    }

    @Test
    public void checkPostNotCreatedWithNotExistingUser() {
        System.out.println("start test 3.2");
        newPostPojo.setUserId(0);

        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
        System.out.println("end test 3.2");
    }

    @Test
    public void checkPostNotCreatedWithEmptyTitle() {
        System.out.println("start test 3.3");
        newPostPojo.setTitle(StringUtils.EMPTY);

        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
        System.out.println("end test 3.3");
    }

    @Test
    public void checkPostNotCreatedWithEmptyBody() {
        System.out.println("start test 3.4");
        newPostPojo.setBody(StringUtils.EMPTY);

        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
        System.out.println("end test 3.4");
    }

    @Test
    public void checkPostNotCreatedWithoutBody() {
        System.out.println("start test 3.5");
        newPostPojo.setBody(null);

        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
        System.out.println("end test 3.5");
    }

    @Test
    public void checkPostNotCreatedWithoutTitle() {
        System.out.println("start test 3.6");
        newPostPojo.setTitle(null);

        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
        System.out.println("end test 3.6");
    }
}
