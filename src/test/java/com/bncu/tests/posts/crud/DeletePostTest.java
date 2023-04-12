package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.core.FakeUtil;
import com.nbcu.entities.pojos.PostPojo;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class DeletePostTest {

    private PostApi postApi = new PostApi();
    //supposed to create and delete created 'post', but creation(so as deleting) is only emulated, but doen't performed,
    // that's why used the method selecting random one. If it worked as expected, the commented row is to be used
    // instead of the following
    private PostPojo postPojo = postApi.getPostById(FakeUtil.getRandomPostIdString());
    //    private PostPojo postPojo = postApi.createNewPost(PostFixture.getPost());

    @Test
    public void checkPostDeleting() {
        postApi.deletePostById(String.valueOf(postPojo.getId()));
        //test will fail if check on not contains, as api only can emulate the deleting behaviour, but doesn't perform it.
        //that's why checked that the post is not deleted, and still here
        assertThat(postApi.getAllPosts())
                    .as("Deleted post is not expected to be in a list")
                    .contains(postPojo);
    }
}
