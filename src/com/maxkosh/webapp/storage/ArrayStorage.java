package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private short realLength = 0;

    public void clear() {
        Arrays.fill(storage, null);
        realLength = 0;
    }

    public void save(Resume r) {
        if (realLength != storage.length && resumeExist(r.getUuid()) == -1) {
            storage[realLength] = r;
            realLength++;
        } else if (realLength == storage.length) {
            System.out.println("Error: no space left in the storage.");
        } else {
            System.out.println("Error: resume with UUIN: " + r.getUuid() + " is already exist in the storage.");
        }
    }

    public void update(Resume r) {
        int index = resumeExist(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Error: resume with UUIN: " + r.getUuid() + " is not exist in the storage.");
        }
    }

    public Resume get(String uuid) {
        int index = resumeExist(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Error: resume with UUIN: " + uuid + " is not exist in the storage.");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = resumeExist(uuid);
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

    private int resumeExist(String uuid) {
        for (int i = 0; i < realLength; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
