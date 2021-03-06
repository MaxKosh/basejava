package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;
import com.maxkosh.webapp.storage.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for com.maxkosh.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | update uuid| delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(SORTED_ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(uuid, "");
                    SORTED_ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    r = new Resume(uuid, "");
                    SORTED_ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "delete":
                    SORTED_ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    System.out.println(SORTED_ARRAY_STORAGE.get(uuid));
                    break;
                case "clear":
                    SORTED_ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = SORTED_ARRAY_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}