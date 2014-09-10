package com.codekittens.thalidomide.parser;

import org.codehaus.jackson.map.ObjectMapper;

public class Parser<T> {

    ObjectMapper mapper = new ObjectMapper();

    public T parse(Class<T> cls, String json) {
        try {
            return mapper.readValue(json, cls);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
