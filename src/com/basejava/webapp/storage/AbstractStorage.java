package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract void runUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void runSave(Resume r, Object searchKey);

    protected abstract Resume runGet(Object searchKey);

    protected abstract void runDelete(Object searchKey);

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        runUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        runSave(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        runDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return runGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
