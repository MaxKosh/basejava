package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10_000];
    private short realLength = 0;

    public void clear() {
        Arrays.fill(storage, 0, realLength, null);
        realLength = 0;
    }

    public void save(Resume resume) {
        if (realLength == storage.length) {
            System.out.println("Error: no space left in the storage.");
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Error: resume with UUIN: " + resume.getUuid() + " is already exist in the storage.");
        } else {
            storage[realLength] = resume;
            realLength++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Error: resume with UUIN: " + resume.getUuid() + " is not exist in the storage.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Error: resume with UUIN: " + uuid + " is not exist in the storage.");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[realLength - 1];
            storage[realLength - 1] = null;
            realLength--;
        } else {
            System.out.println("Error: resume with UUIN: " + uuid + " is not exist in the storage.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, realLength);
    }

    public int size() {
        return realLength;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < realLength; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
