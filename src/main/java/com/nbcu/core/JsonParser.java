package com.nbcu.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonParser {
    private static ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static String toJson(Object object) {
        return toJson(object, true);
    }

    @SneakyThrows
    public static String toJson(Object object, boolean ignoreNull) {
        if (ignoreNull) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T fromJson(String json, Class<T> type) {
        return mapper.readValue(json, type);
    }
}