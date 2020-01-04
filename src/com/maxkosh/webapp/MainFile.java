package com.maxkosh.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File dir = new File("/Users/max/basejava/src");
        printFileName(dir);
    }

    public static void printFileName(File directory) throws IOException {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (!file.isDirectory()) {
                System.out.println(file.getName());
            } else {
                printFileName(file);
            }
        }
    }
}
