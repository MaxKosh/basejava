package com.maxkosh.webapp;

import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.storage.MapStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainPath {
    static Path directory;
    public static void main(String[] args) {
        directory = Paths.get("C:\\Users\\1\\IdeaProjects\\basejava\\storage");
        System.out.println(size());
    }

    public static long size() {
        try {
            return Files.size(directory);
        } catch (IOException e) {
            throw new StorageException("Path size error", null, e);
        }
    }
}
