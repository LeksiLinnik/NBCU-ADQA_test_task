package com.nbcu.core;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FakeUtil {

    @Getter private static Faker faker = new Faker();

    public static int getRandomUser() {
        return faker.number().numberBetween(1, 10);
    }

    public static String getRandomTitle() {
        return faker.lorem().sentence();
    }

    public static String getRandomBody() {
        return faker.lorem().paragraph();
    }
}