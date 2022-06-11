package com.basejava.webapp.storage;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void sortAfterDelete(int index) {
        storage[index] = storage[size() - 1];
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.format("Резюме с uuid: %s нет в хранилище\n", uuid);
        return -1;
    }
}
