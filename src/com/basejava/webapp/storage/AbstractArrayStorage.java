package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Хранилище очищено");
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.format("В резюме с индексом %d обновлен uuid: %s\n", index, storage[index].getUuid());
        }
    }

    public void save(Resume r) {
        Objects.requireNonNull(r);
        int index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            String msgForOverflow = "Внимание! В хранилище - нет свободного места. \n" +
                    "Резюме с uuid %s добавить не удалось. " +
                    "Попробуйте удалить неиспользуемые резюме\n\n";
            System.out.format(msgForOverflow, r.getUuid());
        } else if (index >= 0) {
            System.out.format("Резюме с %s уже есть в базе\n", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.out.format("Резюме с uuid: %s удалено%n", uuid);
            fillDeletedElement(index);
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

    public int getLength() {
        return STORAGE_LIMIT;
    }

    protected abstract void insertElement(Resume r, int position);

    protected abstract void fillDeletedElement(int index);

    protected abstract int findIndex(String uuid);
}
