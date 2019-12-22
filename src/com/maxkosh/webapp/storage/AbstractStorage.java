package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getByIndex(int index);

    protected abstract void updateByIndex(int index, Resume resume);

    protected abstract Integer getIndex(String uuid);

    protected abstract void deleteByIndex(int index);

    protected abstract boolean checkOverflow();

    protected abstract void insertResume(Resume resume, Integer index);

    @Override
    public void save(Resume resume) {
        Integer index = getIndex(resume.getUuid());
        if (index != null && index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (checkOverflow()) {
            throw new StorageException("Storage overflow.", resume.getUuid());
        } else {
            insertResume(resume, index);
        }
    }

    @Override
    public void update(Resume resume) {
        int index = checkException(resume.getUuid());
        updateByIndex(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = checkException(uuid);
        return getByIndex(index);
    }

    @Override
    public void delete(String uuid) {
        int index = checkException(uuid);
        deleteByIndex(index);
    }

    private int checkException(String uuid) {
        Integer index = getIndex(uuid);
        if (index != null && index >= 0) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
