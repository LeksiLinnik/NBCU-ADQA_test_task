package com.nbcu.core;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FakeUtil {

    //current resource has fixed number of unchangeable posts and users
    private static final int MAX_POST_ID = 100;
    private static final int MAX_USER_ID = 10;

    @Getter private static Faker faker = new Faker();

    public static int getRandomPostId() {
        return faker.number().numberBetween(1, MAX_POST_ID);
    }

    public static String getRandomPostIdString() {
        return String.valueOf(getRandomPostId());
    }

    public static int getRandomUserId() {
        return faker.number().numberBetween(1, MAX_USER_ID);
    }

    public static String getRandomTitle() {
        return faker.lorem().sentence();
    }

    public static String getRandomBody() {
        return faker.lorem().paragraph();
    }
}