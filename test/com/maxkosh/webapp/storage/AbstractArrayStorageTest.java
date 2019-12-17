package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid_4");
        storage.save(resume);

        Assert.assertEquals(storage.get("uuid_4"), resume);
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid_4");
        storage.save(resume);
        storage.update(resume);

        Assert.assertEquals(storage.get("uuid_4"), resume);
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void get() {
        Resume resume = new Resume("uuid_1");
        Assert.assertEquals(storage.get("uuid_1"), resume);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] storageCopy = storage.getAll();
        for (int i = 0; i < storageCopy.length; i++) {
            Assert.assertEquals(storageCopy[i], storage.get("uuid_" + (i + 1)));
        }
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() throws Exception {
        storage.save(storage.get("uuid_1"));
    }

    @Test(expected = StorageException.class)
    public void getOverflow() throws Exception {
        try {
            for (int i = 4; i <= 10_000; i++) {
                storage.save(new Resume("uuid_" + i));
            }
        } catch (StorageException storageException) {
            Assert.fail("Test was failed");
        } finally {
            storage.save(new Resume("uuid_10001"));
        }
    }
}