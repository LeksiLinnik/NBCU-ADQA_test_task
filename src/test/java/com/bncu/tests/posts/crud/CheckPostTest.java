package com.bncu.tests.posts.crud;

import com.nbcu.api.PostApi;
import com.nbcu.core.FakeUtil;
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
    List<PostPojo> posts = postApi.getAllPosts();
    //all existing posts shouldn't have empty values, decided to check random of the list
    PostPojo post = postApi.getPostById(FakeUtil.getRandomPostIdString());

    @Test
    public void checkAllPostsUsers() {
        //there shouldn't be any post that have no userId
        posts.forEach(postPojo ->
                assertThat(postPojo.getUserId())
                        .as("Post should have user id")
                        .isNotNull());
    }

    @Test
    public void checkRandomPostFields() {
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
    }
}
