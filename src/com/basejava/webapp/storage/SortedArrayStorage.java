package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size() - (index + 1));
    }

    @Override
    protected int findIndex(String uuid) {
        int index = Arrays.binarySearch(storage, 0, size(), new Resume(uuid));
        if(index < 0) {
            System.out.format("Резюме с uuid: %s нет в хранилище\n", uuid);
        }
        return index;
    }

    @Override
    protected void insertElement(Resume r, int position) {
        int insertionIndex = - position - 1;
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
        storage[insertionIndex] = r;
    }
}
