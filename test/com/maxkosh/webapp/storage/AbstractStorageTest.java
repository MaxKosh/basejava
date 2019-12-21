package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Max");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Lena");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Ivan");
    private static final Resume RESUME_4 = new Resume(UUID_4, "Lena");

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(UUID_4));
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy", ""));
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() {
        storage.save(RESUME_4);
        List<Resume> storageAsList = storage.getAllSorted();
        ArrayList<Resume> testList = new ArrayList<>();
        testList.add(RESUME_3);
        testList.add(RESUME_2);
        testList.add(RESUME_4);
        testList.add(RESUME_1);
        for (int i = 0; i < storage.size(); i++) {
            assertEquals(storageAsList.get(i), testList.get(i));
        }
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int expected) {
        assertEquals(expected, storage.size());
    }
}