package com.codekittens.thalidomide;

import com.codekittens.thalidomide.model.WrappedVoteResponse;
import com.codekittens.thalidomide.parser.JsonReader;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.List;

public class Helper<T> {

    List<T> readFromFile(String fileName) {
        String json = new JsonReader().read(fileName);
        try {
            List<T> decoded =
                    new ObjectMapper().readValue(json, new TypeReference<List<T>>() {});
            return decoded;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    List<WrappedVoteResponse> read(String fileName) {
        String json = new JsonReader().read(fileName);
        try {
            List<WrappedVoteResponse> decoded =
                    new ObjectMapper().readValue(json, new TypeReference<List<WrappedVoteResponse>>() {});
            return decoded;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
