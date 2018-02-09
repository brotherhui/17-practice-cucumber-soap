package com.brotherhui.cucumber.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;

import java.io.IOException;

/**
 * @author xingang.a.wang
 * @create 2017-07-12 10:45 AM
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String format(Object value) {
        String json = null;
        try {
            json = mapper.writeValueAsString(value);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return json;
    }

    public static <T> T parse(String json, Class<T> clazz) {
        T object = null;
        try {
            object = mapper.readValue(json, clazz);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return object;
    }

}
