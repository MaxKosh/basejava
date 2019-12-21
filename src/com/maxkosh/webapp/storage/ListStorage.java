package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
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

    @Override
    protected Integer getIndex(String uuid) {
        int index = 0;
        for (Resume resume : storage) {
            if (Objects.equals(resume.getUuid(), uuid)) {
                return index;
            }
            index++;
        }
        return null;
    }

    @Override
    protected Resume getByIndex(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void updateByIndex(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected void deleteByIndex(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected void insertResume(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }
}
