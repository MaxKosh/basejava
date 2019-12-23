package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] mapToArray = new Resume[storage.size()];
        return storage.values().toArray(mapToArray);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected Resume getByIndex(Object index) {
        return storage.get(index);
    }

    @Override
    protected void updateByIndex(Object index, Resume resume) {
        storage.replace((String) index, resume);
    }

    @Override
    protected void deleteByIndex(Object index) {
        storage.remove(index);
    }

    @Override
    protected void insertResume(Resume resume, Object index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Object index) {
        if (index != null) {
            return true;
        }
        return false;
    }
}
