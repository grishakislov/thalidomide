package com.codekittens.thalidomide.client.extractor;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResponseExtractor {

    protected String key;
    protected String delimiter;
    protected int splitPosition;
    protected boolean existsMode;


    public ResponseExtractor(String key) {
        existsMode = true;
        this.key = key;
    }

    public ResponseExtractor(String key, String delimiter, int splitPosition) {
        this.key = key;
        this.delimiter = delimiter;
        this.splitPosition = splitPosition;
    }

    public boolean exists(HttpsURLConnection connection) {
        return extract(connection, true) != null;
    }

    public String extractOne(HttpsURLConnection connection) {
        if (existsMode) {
            return null;
        }
        return extract(connection, true).get(0);
    }

    public List<String> extractList(HttpsURLConnection connection) {
        if (existsMode) {
            return null;
        }
        return extract(connection, false);
    }

    private List<String> extract(HttpsURLConnection connection, boolean first) {
        List<String> result = new ArrayList<String>();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            String extracted;
            while ((line = rd.readLine()) != null) {
                if (line.contains(key)) {
                    String[] split = line.split(delimiter);
                    extracted = split[splitPosition];
                    result.add(extracted);
                    if (first) {
                        return result;
                    }
                }
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
