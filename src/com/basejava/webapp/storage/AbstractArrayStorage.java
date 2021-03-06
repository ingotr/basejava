package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
        System.out.format("В резюме с индексом %d обновлен uuid: %s\n", index, storage[index].getUuid());
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        String msgForOverflow = "Внимание! В хранилище - нет свободного места. \n" +
                "Резюме с uuid %s добавить не удалось. " +
                "Попробуйте удалить неиспользуемые резюме\n\n";
        if (size == STORAGE_LIMIT) {
            throw new StorageException(msgForOverflow, r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    public void doDelete(Integer index) {
        fillDeletedElement(index);
        System.out.format("Резюме с uuid: %s удалено%n", index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public static int getLength() {
        return STORAGE_LIMIT;
    }

    protected abstract void insertElement(Resume r, int position);

    protected abstract void fillDeletedElement(int index);

    protected abstract Integer getSearchKey(String uuid);
}
