package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.entities.pojos.PostPojo;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CheckPostTest {

    private PostApi postApi = new PostApi();

    @Test
    public void checkPosts() {
        System.out.println("start test 2.1");
        List<PostPojo> posts = postApi.getAllPosts();
        posts.forEach(postPojo -> assertThat(postPojo.getTitle()).as("Post shouldn't have empty title").isNotEmpty());
        System.out.println("end test 2.1");
    }

    @Test
    public void checkPostHasNotEmptyFields() {
        System.out.println("start test 2.2");
        PostPojo post = postApi.getPostById("1");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(post.getUserId())
                    .as("Post should not have empty user id")
                    .isNotNull();
            softly.assertThat(post.getTitle())
                    .as("Post should not have empty title")
                    .isNotEmpty();
            softly.assertThat(post.getBody())
                    .as("Post should not have empty body")
                    .isNotEmpty();
        });
        System.out.println("end test 2.2");
    }
}
