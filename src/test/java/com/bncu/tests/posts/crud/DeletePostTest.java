package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.entities.pojos.PostPojo;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class DeletePostTest {

    private PostApi postApi = new PostApi();
    //supposed to create and delete created 'post', but creation(so as deleting) is only emulated, that's why mocked the one that is not used in other tests
//    private PostPojo postPojo = postApi.createNewPost(PostFixture.getPost());
    private PostPojo postPojo = postApi.getPostById("3");

    @Test
    public void checkPostDeleting() {
        System.out.println("start test 6");
        postApi.deletePostById(String.valueOf(postPojo.getId()));
        //test will fail as only can emulate the deleting behaviour, but doesn't perform it.
        assertThat(postApi.getAllPosts())
                    .as("Deleted post is not expected to be in a list")
                    .doesNotContain(postPojo);
        System.out.println("start test 6");
    }
}
