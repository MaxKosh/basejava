package com.maxkosh.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        //String dirPath = "/Users/max/basejava/src"";
        String dirPath = "C:/Users/1/IdeaProjects/basejava/src";
        getPath(dirPath, null);
    }

    public static void getPath(String path, File dir) {
        dir = new File(path);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            System.out.println(file.getName());
            if (file.isDirectory()) {
                try {
                    String canonicalPath = file.getCanonicalPath();
                    getPath(canonicalPath, file);
                } catch (IOException e) {
                    throw new RuntimeException("Error", e);
                }
            }
        }
    }
}
