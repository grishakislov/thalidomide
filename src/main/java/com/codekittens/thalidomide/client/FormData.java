package com.codekittens.thalidomide.client;

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Set<String> keys = properties.keySet();

        Iterator<String> it = keys.iterator();

        try {
            while (it.hasNext()) {
                String name = it.next();
                result.append(URLEncoder.encode(name, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(properties.get(name), "UTF-8"));
                if (it.hasNext()) {
                    result.append("&");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
