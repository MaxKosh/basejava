package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void update(Resume resume) {
        super.update(resume);
    }

    @Override
    protected void updateByIndex(Object index, Resume resume) {

    }

    @Override
    protected Resume getByIndex(Object index) {
        return storage.get(index);
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
    }

    @Override
    protected void deleteByIndex(Object index) {

    }

    @Override
    protected Object getIndex(String uuid) {
        //Object o = uuid;
        /*if(storage.containsKey(uuid)) {
            return uuid;
        }
        return null;*/
        return uuid;
    }

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
    protected void insertResume(Resume resume, Object index) {
        storage.put((String) index, resume);
    }

    @Override
    protected boolean isExist(Object index) {
        if(index != null) {
            return true;
        }
        return false;
    }
}
