package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void sortAfterDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size() - (index + 1));
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        System.out.println("current storage size " + size());
        int index = Arrays.binarySearch(storage, 0, size(), searchKey);
        if(index < 0) {
            System.out.format("Резюме с uuid: %s нет в хранилище\n", uuid);
        }
        return index;
    }
}
