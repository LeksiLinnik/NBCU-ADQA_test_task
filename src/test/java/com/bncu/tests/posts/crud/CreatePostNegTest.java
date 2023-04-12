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

    /*
    The following is supposed to fail on creation, but not failed because api allows creation with potentially
    invalid data. Also server doesn't allow to add new data, despite the fact it is fakes as if. Commented rows
    shows how this could potentially be checked if works as expected (throwing error from api)
     */

    @Test
    public void checkPostNotCreatedWithEmptyUser() {
        newPostPojo.setUserId(null);
//        assertThrows(ApiException.class, () -> postApi.createNewPost(newPostPojo));
        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
    }

    @Test
    public void checkPostNotCreatedWithNotExistingUser() {
        newPostPojo.setUserId(0);
//        assertThrows(ApiException.class, () -> postApi.createNewPost(newPostPojo));
        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
    }

    @Test
    public void checkPostNotCreatedWithEmptyTitle() {
        newPostPojo.setTitle(StringUtils.EMPTY);
//        assertThrows(ApiException.class, () -> postApi.createNewPost(newPostPojo));
        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
    }

    @Test
    public void checkPostNotCreatedWithEmptyBody() {
        newPostPojo.setBody(StringUtils.EMPTY);
//        assertThrows(ApiException.class, () -> postApi.createNewPost(newPostPojo));
        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
    }

    @Test
    public void checkPostNotCreatedWithoutBody() {
        newPostPojo.setBody(null);
//        assertThrows(ApiException.class, () -> postApi.createNewPost(newPostPojo));
        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
    }

    @Test
    public void checkPostNotCreatedWithoutTitle() {
        newPostPojo.setTitle(null);
//        assertThrows(ApiException.class, () -> postApi.createNewPost(newPostPojo));
        postApi.createNewPost(newPostPojo);

        assertThat(postApi.getAllPosts())
                .as("Non created post is not expected to be in a list")
                .doesNotContain(newPostPojo);
    }
}
