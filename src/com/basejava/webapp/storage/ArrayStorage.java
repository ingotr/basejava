package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[4];

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Хранилище очищено");
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index].setUuid(storage[index].getUuid() + "U");
            System.out.format("В резюме с индексом %d обновлен uuid: %s\n", index, storage[index].getUuid());
        }
    }

    public void save(Resume r) {
        if (r == null) {
            return;
        }
        if(size >= storage.length) {
            String msgForOverflow = "Внимание! В хранилище - нет свободного места. \n" +
                    "Резюме с uuid %s добавить не удалось. " +
                    "Попробуйте удалить неиспользуемые резюме\n\n";
            System.out.format(msgForOverflow, r.getUuid());
            return;
        }
        int index = findIndex(r.getUuid());
        if (index == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.format("Резюме с %s уже есть в базе\n", r.getUuid());
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.format("Резюме с uuid: %s нет в хранилище\n", uuid);
        return -1;
    }
}