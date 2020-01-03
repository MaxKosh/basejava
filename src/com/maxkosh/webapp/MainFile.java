package com.maxkosh.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        //String dirPath = "/Users/max/basejava/src";
        String dirPath = "C:/Users/1/IdeaProjects/basejava/src";
        printFileName(dirPath);
    }

    public static void printFileName(String path) throws IOException {
        File dir = new File(path);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (!file.isDirectory()) {
                System.out.println(file.getName());
            } else {
                String canonicalPath = file.getCanonicalPath();
                printFileName(canonicalPath);
            }
        }
    }
}
