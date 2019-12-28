package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage <Integer> {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage.set(index, resume);
    }

    @Override
    protected void doDelete(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        storage.add(resume);
    }

    @Override
    protected boolean isExist(Integer index) {
        return index != null;
    }
}
