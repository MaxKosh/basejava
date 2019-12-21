package com.maxkosh.webapp.model;

import java.util.Comparator;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public static final Comparator<Resume> RESUME_UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);
    public static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName);
    public static final Comparator<Resume> RESUME_COMPARATOR = RESUME_FULL_NAME_COMPARATOR.thenComparing(RESUME_UUID_COMPARATOR);

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return fullName + ", " + uuid;
    }
}
