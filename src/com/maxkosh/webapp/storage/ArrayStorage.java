package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        storage[size] = resume;
        size++;
    }

    @Override
    protected void doDelete(Integer index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}
