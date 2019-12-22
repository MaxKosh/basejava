package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getByIndex(int index);

    protected abstract void updateByIndex(int index, Resume resume);

    protected abstract int getIndex(String uuid);

    protected abstract void deleteByIndex(int index);

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            updateByIndex(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }

    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getByIndex(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteByIndex(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
