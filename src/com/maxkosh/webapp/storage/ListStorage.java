package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.Resume;
import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<Resume>();
    public int size = storage.size();

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
    public void update(Resume resume) {
        int index = storage.indexOf(resume);
        if (index == -1) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.set(index, resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        int index = storage.indexOf(resume);
        if (index == -1) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            return storage.get(index);
        }
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        boolean removed = storage.remove(resume);
        if(!removed) {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
