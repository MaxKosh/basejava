package com.maxkosh.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume with UUID: " + uuid + " does not exist", uuid);
    }
}
