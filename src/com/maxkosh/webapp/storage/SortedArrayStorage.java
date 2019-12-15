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
    protected void insertResume(Resume resume, int index) {
        int insertIndex = (index + 1) * (-1);
        size++;

        for (int i = size - 1; i > insertIndex; i--) {
            storage[i] = storage[i - 1];
        }
        storage[insertIndex] = resume;
    }

    @Override
    protected void deleteByIndex(int index) {
        while (index < size - 1) {
            storage[index] = storage[index + 1];
            storage[index + 1] = null;
            index++;
        }
        size--;
    }
}
