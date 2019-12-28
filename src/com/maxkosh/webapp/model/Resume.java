package com.maxkosh.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return fullName + ", " + uuid;
    }

    public class PersonalSection {

        private Map<ContactType, String> contacts = new HashMap<>();

        public void setContacts(ContactType contactType, String s) {
            contacts.put(contactType, s);
        }

        public String getContacts(ContactType contactType) {
            return contacts.get(contactType);
        }
    }

    public class TextSection {
        private SectionType sectionType;
        private String personalInfo;
        private String objectiveInfo;

        public TextSection(SectionType sectionType) {
            this.sectionType = sectionType;
        }
    }

    public class ListSection {
        private SectionType sectionType;
        private List<String> achievementList;
        private List<String> qualificationsList;

        public ListSection(SectionType sectionType) {
            this.sectionType = sectionType;
        }
    }

    public class DateSection {
        private SectionType sectionType;
        private Map<Date, String> experienceMap;
        private Map<Date, String> educationMap;

        public DateSection(SectionType sectionType) {
            this.sectionType = sectionType;
        }
    }
}
