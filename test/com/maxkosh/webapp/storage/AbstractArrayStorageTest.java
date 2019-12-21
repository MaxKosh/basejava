package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException storageException) {
            fail("Test was failed");
        }
        storage.save(new Resume());
    }
}