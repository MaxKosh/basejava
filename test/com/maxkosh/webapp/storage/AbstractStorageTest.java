package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.Config;
import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();//new File("C:/Users/1/IdeaProjects/basejava/storage");
    protected Storage storage;

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = ResumeTestData.resume_1;
        RESUME_2 = ResumeTestData.resume_2;
        RESUME_3 = ResumeTestData.resume_3;
        RESUME_4 = ResumeTestData.resume_4;
    }


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
        assertEquals(RESUME_4, storage.get(RESUME_4.getUuid()));
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(RESUME_1.getUuid(), "");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(RESUME_1.getUuid())));
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy", ""));
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        storage.get(RESUME_1.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> storageAsList = storage.getAllSorted();
        ArrayList<Resume> testList = new ArrayList<>();
        testList.add(RESUME_1);
        testList.add(RESUME_2);
        testList.add(RESUME_3);
        assertEquals(storageAsList, testList);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int expected) {
        assertEquals(expected, storage.size());
    }
}