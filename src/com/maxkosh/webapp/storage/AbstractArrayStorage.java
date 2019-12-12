package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume);

    protected abstract void deleteByIndex(int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Error: no space left in the storage.");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Error: resume with UUIN: " + resume.getUuid() + " already exists in the storage." + " " + getIndex(resume.getUuid()));
        } else {
            insertResume(resume);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Error: resume with UUIN: " + resume.getUuid() + " does not exist in the storage.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Storage index of resume is: " + index);
            return storage[index];
        } else {
            System.out.println("Error: resume with UUIN: " + uuid + " does not exist in the storage.");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        System.out.println("Index of deleting element: " + index);
        if (index >= 0) {
            deleteByIndex(index);
        } else {
            System.out.println("Error: resume with UUIN: " + uuid + " does not exist in the storage.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
