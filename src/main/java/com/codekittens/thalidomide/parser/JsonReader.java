package com.codekittens.thalidomide.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    public static String read(String path) {

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
