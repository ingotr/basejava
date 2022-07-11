package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<F> implements Storage {
    protected abstract F getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, F searchKey);

    protected abstract boolean isExist(F searchKey);

    protected abstract void doSave(Resume r, F searchKey);

    protected abstract Resume doGet(F searchKey);

    protected abstract void doDelete(F searchKey);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        F searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        F searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        F searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        F searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private F getExistedSearchKey(String uuid) {
        F searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private F getNotExistedSearchKey(String uuid) {
        F searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
