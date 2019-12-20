package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;
import com.maxkosh.webapp.storage.ListStorage;

import java.util.ArrayList;

public class MainCollection {
    public static void main(String[] args) {
        ListStorage testListStorage = new ListStorage();
        Resume resume_1 = new Resume("uuid_1");
        Resume resume_2 = new Resume("uuid_2");
        Resume resume_3 = new Resume("uuid_3");
        Resume resume_4 = new Resume("uuid_4");
        Resume resume_5 = new Resume("uuid_5");

        testListStorage.save(resume_1);
        testListStorage.save(resume_2);
        testListStorage.save(resume_3);
        testListStorage.save(resume_4);
        testListStorage.save(resume_5);

        System.out.println("Size of list: " + testListStorage.size());
        for (int i = 1; i <= testListStorage.size(); i++) {
            System.out.println("Resume from testList: " + testListStorage.get("uuid_" + i));
        }
        testListStorage.clear();
        System.out.println("Size of list: " + testListStorage.size());
    }
}
