package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;
import com.maxkosh.webapp.storage.MapStorage;
//import com.maxkosh.webapp.storage.MapUuidStorage;

public class MainMap {
    public static void main(String[] args) {
        MapStorage testMapUuidStorage = new MapStorage();

        Resume resume_1 = new Resume("uuid_5", "Max Koshelev");
        Resume resume_2 = new Resume("uuid_3", "Lena Zotova");
        Resume resume_3 = new Resume("uuid_4", "Nikolas Frolkov");
        Resume resume_4 = new Resume("uuid_1", "Alex Terekhov");
        Resume resume_5 = new Resume("uuid_6", "Nikolas Frolkov");
        Resume resume_6 = new Resume("uuid_2", "Dima Morozov");

        System.out.println("SAVE + SIZE + GETALL funcs ----------------- ");
        //System.out.println("GET: " + testMapUuidStorage.get(resume_3.getUuid())); //NotExistException
        testMapUuidStorage.save(resume_2);
        testMapUuidStorage.save(resume_1);
        testMapUuidStorage.save(resume_3);
        testMapUuidStorage.save(resume_4);
        testMapUuidStorage.save(resume_5);
        //testMapUuidStorage.save(resume_1); //ExistException

        System.out.println("Size: " + testMapUuidStorage.size());

        for (Resume r : testMapUuidStorage.getAllSorted()) {
            System.out.println("Get resume: " + r);
        }

        System.out.println("GET func ----------------- ");
        System.out.println("GET: " + testMapUuidStorage.get(resume_5.getUuid()));
        //System.out.println("GET: " + testMapUuidStorage.get(resume_6.getUuid()));  //NotExistException

        System.out.println("UPDATE func --------------------------");
        //testMapUuidStorage.update(resume_6); //NotExistException
        testMapUuidStorage.update(resume_1);

        System.out.println("DELETE func --------------------------");
        //testListStorage.delete(resume_6.getUuid()); //NotExistException
        testMapUuidStorage.delete(resume_3.getUuid());
        System.out.println("Size: " + testMapUuidStorage.size());

        for (Resume r : testMapUuidStorage.getAllSorted()) {
            System.out.println("Get resume: " + r);
        }

        System.out.println("CLEAR func --------------------------");
        testMapUuidStorage.clear();
        System.out.println("Size: " + testMapUuidStorage.size());
    }
}
