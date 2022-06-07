package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private int size = 0;

    public void clear() {
        for (Resume resume : storage) {
            if (resume != null) {
                resume = null;
            }
        }
        size = 0;
        System.out.println("Хранилище очищено");
    }

    public void save(Resume r) {
        if (r == null) {
            return;
        }
        int index = findIndex(r.getUuid());
        if (index == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Резюме с таким uuid уже есть в базе");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            System.out.format("Резюме с uuid: %s удалено%n", uuid);
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            storage[size - 1] = null;
            size--;
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.format("Резюме с таким uuid: %s нет в хранилище\n", uuid);
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
