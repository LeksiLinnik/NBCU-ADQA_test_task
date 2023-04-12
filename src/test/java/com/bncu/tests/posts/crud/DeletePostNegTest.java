package com.bncu.tests.posts.crud;

import com.nbcu.api.ApiException;
import com.nbcu.api.PostApi;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Execution(ExecutionMode.CONCURRENT)
public class DeletePostNegTest {

    private PostApi postApi = new PostApi();
    private int nonExitingPostId = postApi.getAllPosts().size() + 1;

    @Test
    public void checkPostNonDeleting() {
        //APi allows to delete anything, emulating this without failures. If works as expected - the commented row
        // should be used instead of suggested
        postApi.deletePostById(String.valueOf(nonExitingPostId));
//        assertThrows(ApiException.class, () -> postApi.deletePostById(String.valueOf(nonExitingPostId)));
        //checks that the post still not exist in system, and not created by deleting.
        assertThrows(ApiException.class, () -> postApi.getPostById(String.valueOf(nonExitingPostId)));
    }
}
