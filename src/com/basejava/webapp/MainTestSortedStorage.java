package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.SortedArrayStorage;
import com.basejava.webapp.storage.Storage;

public class MainTestSortedStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        printAll();
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        printAll();
        ARRAY_STORAGE.save(r3);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.update(r4);
        ARRAY_STORAGE.update(r3);
        printAll();

//        for (int i = 5; i <= ARRAY_STORAGE.getLength(); i++) {
//            Resume r = new Resume();
//            r.setUuid("uuid" + i);
//            ARRAY_STORAGE.save(r);
//        }
//        System.out.println("current storage size: " + ARRAY_STORAGE.size());

        Resume r10001 = new Resume();
        r10001.setUuid("uuid10001");
        ARRAY_STORAGE.save(r10001);
        Resume r10002 = new Resume();
        r10002.setUuid("uuid10002");
        ARRAY_STORAGE.save(r10002);

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete("uuid1");
        printAll();
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
