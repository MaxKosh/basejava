package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<Resume>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) {
        if (storage.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storage.add(resume);
        }
    }

    @Override
    public Resume[] getAll() {
        Resume[] listToArray = new Resume[storage.size()];
        return storage.toArray(listToArray);
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected int getIndex(String uuid) {
        int index = 0;
        for (Resume resume : storage) {
            if (Objects.equals(resume.getUuid(), uuid)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    protected Resume getByIndex(int index) {
        return storage.get(index);
    }

    protected void updateByIndex(int index, Resume resume) {
        storage.set(index, resume);
    }

    protected void deleteByIndex(int index) {
        storage.remove(index);
    }
}
