package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serializer.Serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final Serializer serializer;

    protected PathStorage(String dir, Serializer serializer) {
        directory = Paths.get(dir);
        this.serializer = serializer;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir +
                    " is not directory or it is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path searchKey) {
        try {
            serializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path searchKey) {
        return Files.exists(searchKey);
    }

    @Override
    protected void doSave(Resume r, Path searchKey) {
        try {
            Files.createFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("Path save error", searchKey.getFileName().toString(),e);
        }
        doUpdate(r, searchKey);
    }

    @Override
    protected Resume doGet(Path searchKey) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path read error", searchKey.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new StorageException("Path delete error", searchKey.toString(), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getPaths("Path copyAll error").map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getPaths("Path delete error").forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getPaths("Path read error").count();
    }

    private Stream<Path> getPaths(String message) {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException(message, null, e);
        }
    }
}
