package com.codekittens.thalidomide.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FormData {

    private Map<String, String> properties;

    public FormData() {
        properties = new HashMap<String, String>();
    }

    public void addProperty(String name, String value) {
        if (properties.containsKey(name)) {
            throw new IllegalStateException("Property " + name + "already added");
        }
        properties.put(name, value);
    }

    public void addProperty(String name, boolean value) {
        addProperty(name, Boolean.toString(value));
    }

    public void addProperty(String name, int value) {
        addProperty(name, Integer.toString(value));
    }

    public void clear() {
        properties.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        properties.keySet().forEach((String name) -> {
            if (result.toString().length() > 0) {
                result.append("&");
            }
            try {
                result.append(URLEncoder.encode(name, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(properties.get(name), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        return result.toString();
    }
}
