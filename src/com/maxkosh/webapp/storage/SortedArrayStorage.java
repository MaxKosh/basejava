package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        int insertIndex = ((Integer) index + 1) * (-1);
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;
        size++;
    }

    @Override
    protected void doDelete(Object index) {
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size - ((Integer) index + 1));
        storage[size - 1] = null;
        size--;
    }
}
