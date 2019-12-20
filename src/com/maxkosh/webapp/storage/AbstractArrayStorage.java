package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void insertResume(Resume resume, Integer index);

    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        }
        super.save(resume);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
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

    protected boolean isExist(Integer index) {
        if (index >= 0) {
            return true;
        }
        return false;
    }
}
