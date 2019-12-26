package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        Resume updatedResume = (Resume) searchKey;
        storage.replace(updatedResume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        Resume removedResume = (Resume) searchKey;
        storage.remove(removedResume.getUuid());
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}

