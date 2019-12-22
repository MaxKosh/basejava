package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void deleteByIndex(int index);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Error: no space left in the storage.", resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertResume(resume, index);
            size++;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected Resume getByIndex(int index) {
        return storage[index];
    }

    protected void updateByIndex(int index, Resume resume) {
        storage[index] = resume;
    }
}
