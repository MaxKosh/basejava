package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume getByIndex(int index);

    protected abstract void updateByIndex(int index, Resume resume);

    protected abstract Integer getIndex(String uuid);

    protected abstract void deleteByIndex(int index);

    protected abstract void insertResume(Resume resume, Integer index);

    protected abstract boolean isExist(Integer index);

    @Override
    public void save(Resume resume) {
        Integer index = checkExistException(resume.getUuid());
        insertResume(resume, index);
    }

    @Override
    public void update(Resume resume) {
        int index = checkNotExistException(resume.getUuid());
        updateByIndex(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = checkNotExistException(uuid);
        return getByIndex(index);
    }

    @Override
    public void delete(String uuid) {
        int index = checkNotExistException(uuid);
        deleteByIndex(index);
    }

    private Integer checkNotExistException(String uuid) {
        Integer index = getIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Integer checkExistException(String uuid) {
        Integer index = getIndex(uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }
}
