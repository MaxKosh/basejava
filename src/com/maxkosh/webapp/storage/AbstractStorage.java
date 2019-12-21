package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getByIndex(Object index);

    protected abstract void updateByIndex(Object index, Resume resume);

    protected abstract Object getIndex(String uuid);

    protected abstract void deleteByIndex(Object index);

    protected abstract void insertResume(Resume resume, Object index);

    protected abstract boolean isExist(Object index);

    @Override
    public void save(Resume resume) {
        Object index = checkExistException(resume.getUuid());
        insertResume(resume, index);
    }

    @Override
    public void update(Resume resume) {
        Object index = checkNotExistException(resume.getUuid());
        updateByIndex(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object index = checkNotExistException(uuid);
        return getByIndex(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = checkNotExistException(uuid);
        deleteByIndex(index);
    }

    private Object checkNotExistException(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object checkExistException(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }
}
