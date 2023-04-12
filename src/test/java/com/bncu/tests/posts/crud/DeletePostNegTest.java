package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class DeletePostNegTest {

    private PostApi postApi = new PostApi();
    private int nonExitingPostId = postApi.getAllPosts().size() + 1;
//    private PostPojo nonExistingPost = PostModel.getPost(nonExitingPostId);

    @Test
    public void checkPostDeleting() {
        System.out.println("start test 5");
        postApi.deletePostById(String.valueOf(nonExitingPostId));
        System.out.println("end test 5");
    }
}
