package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.ArrayStorage;
import com.basejava.webapp.storage.Storage;

/**
 * Test for your com.basejava.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        Resume r4 = new Resume("uuid4");
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.update(r4);
        ARRAY_STORAGE.update(r3);
        printAll();

//        for (int i = 5; i <= ARRAY_STORAGE.getLength(); i++) {
//            Resume r = new Resume("uuid" + i);
//            ARRAY_STORAGE.save(r);
//        }
//        System.out.println("current storage size: " + ARRAY_STORAGE.size());

        Resume r10001 = new Resume("uuid10001");
        ARRAY_STORAGE.save(r10001);
        Resume r10002 = new Resume("uuid10002");
        ARRAY_STORAGE.save(r10002);

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete("uuid1");
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
