package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;
import com.maxkosh.webapp.storage.ListStorage;

public class MainCollection {
    public static void main(String[] args) {
        ListStorage testListStorage = new ListStorage();

        Resume resume_1 = new Resume("uuid_1");
        Resume resume_2 = new Resume("uuid_2");
        Resume resume_3 = new Resume("uuid_3");
        Resume resume_4 = new Resume("uuid_4");
        Resume resume_5 = new Resume("uuid_5");
        Resume resume_6 = new Resume("uuid_6");

        System.out.println("SAVE + SIZE + GETALL funcs ----------------- ");
        //System.out.println("GET: " + testListStorage.get(resume_3.getUuid())); //NotExistException
        testListStorage.save(resume_1);
        testListStorage.save(resume_2);
        testListStorage.save(resume_3);
        testListStorage.save(resume_4);
        testListStorage.save(resume_5);
        //testListStorage.save(resume_1); //ExistException

        System.out.println("Size: " + testListStorage.size());

        for (Resume r : testListStorage.getAll()) {
            System.out.println("Get resume: " + r);
        }

        System.out.println("GET func ----------------- ");
        System.out.println("GET: " + testListStorage.get(resume_3.getUuid()));
        //System.out.println("GET: " + testListStorage.get(resume_6.getUuid()));  //NotExistException

        System.out.println("UPDATE func --------------------------");
        //testListStorage.update(resume_6); //NotExistException
        testListStorage.update(resume_1);

        System.out.println("DELETE func --------------------------");
        //testListStorage.delete(resume_6.getUuid()); //NotExistException
        testListStorage.delete(resume_1.getUuid());
        System.out.println("Size: " + testListStorage.size());

        for (Resume r : testListStorage.getAll()) {
            System.out.println("Get resume: " + r);
        }

        System.out.println("CLEAR func --------------------------");
        testListStorage.clear();
        System.out.println("Size: " + testListStorage.size());
    }
}
