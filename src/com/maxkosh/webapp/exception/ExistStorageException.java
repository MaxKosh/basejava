package com.maxkosh.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume with UUID: " + uuid + " already exist", uuid);
    }
}
