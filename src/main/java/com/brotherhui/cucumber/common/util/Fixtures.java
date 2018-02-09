package com.brotherhui.cucumber.common.util;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.io.Resources;

/**
 * @author xingang.a.wang
 * @create 2017-06-06 6:21 PM
 */
public class Fixtures {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String readResourceAsString(String resource) {
        URL failureRequest = Resources.getResource(resource);
        String result = null;
        try {
            result = Resources.toString(failureRequest, Charset.defaultCharset());
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return result;
    }

    public static String format(Object value) {
        String json = null;
        try {
            json = mapper.writeValueAsString(value);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return json;
    }
}
