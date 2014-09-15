package com.codekittens.thalidomide.out;

import java.io.PrintWriter;

public class FileHelper {

    private PrintWriter writer;

    public FileHelper(String fileName) {
        try {
            writer = new PrintWriter(fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        writer.close();
    }

    public void println() {
        writer.println();
    }

    public void println(String x) {
        writer.println(x);
    }
}
