package com.maxkosh.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:/Users/1/IdeaProjects/basejava/src");
        String tab = "";
        printFileName(dir, tab);
    }

    public static void printFileName(File directory, String tab) throws IOException {
        tab += "  ";
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                System.out.println(tab + "File: " + file.getName());
            }
            if (file.isDirectory()) {
                System.out.println(tab + "Directory: " + file.getName());
                printFileName(file, tab);
            }
        }
    }
}
