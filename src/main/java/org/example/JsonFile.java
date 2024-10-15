package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonFile {
    public static String loadFileString(String fileName) throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("File not found!");
        }
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

}