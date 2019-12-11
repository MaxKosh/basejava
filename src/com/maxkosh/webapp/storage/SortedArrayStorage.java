package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume) {


    }

    @Override
    protected void deleteByIndex(int index) {
        while (index != size) {
            storage[index] = storage[index + 1];
            storage[index + 1] = null;
            index++;
        }
        size--;
    }
}
