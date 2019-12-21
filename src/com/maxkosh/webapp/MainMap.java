package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;
import com.maxkosh.webapp.storage.MapStorage;

public class MainMap {
    public static void main(String[] args) {
        MapStorage testMapStorage = new MapStorage();

        Resume resume_1 = new Resume("uuid_1");
        Resume resume_2 = new Resume("uuid_2");
        Resume resume_3 = new Resume("uuid_3");
        Resume resume_4 = new Resume("uuid_4");
        Resume resume_5 = new Resume("uuid_5");
        Resume resume_6 = new Resume("uuid_6");

        System.out.println("SAVE + SIZE + GETALL funcs ----------------- ");
        System.out.println("GET: " + testMapStorage.get(resume_3.getUuid())); //NotExistException
        testMapStorage.save(resume_2);
        testMapStorage.save(resume_1);
        testMapStorage.save(resume_3);
        testMapStorage.save(resume_4);
        testMapStorage.save(resume_5);
        //testMapStorage.save(resume_1); //ExistException

        System.out.println("Size: " + testMapStorage.size());

        for (Resume r : testMapStorage.getAll()) {
            System.out.println("Get resume: " + r);
        }

        System.out.println("GET func ----------------- ");
        System.out.println("GET: " + testMapStorage.get(resume_5.getUuid()));
        //System.out.println("GET: " + testListStorage.get(resume_6.getUuid()));  //NotExistException
    }
}
