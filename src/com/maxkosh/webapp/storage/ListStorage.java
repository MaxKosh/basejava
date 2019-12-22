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

    protected Resume getByIndex(int index) {
        return storage.get(index);
    }

    protected void updateByIndex(int index, Resume resume) {
        storage.set(index, resume);
    }

    protected void deleteByIndex(int index) {
        storage.remove(index);
    }

    protected void insertResume(Resume resume, Integer index) {
        storage.add(resume);
    }

    protected boolean checkOverflow() {
        return false;
    }
}
