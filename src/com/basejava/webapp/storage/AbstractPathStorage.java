package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected abstract void doWrite(Resume r, OutputStream outputStream);

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
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
        try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(searchKey))) {
            output.writeObject(r);
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
            throw new RuntimeException(e);
        }
        doUpdate(r, searchKey);
    }

    @Override
    protected Resume doGet(Path searchKey) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Path read error", searchKey.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path searchKey){
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try (Stream<Path> walk = Files.walk(Paths.get(String.valueOf(directory)))) {
            return walk.filter(Files::isRegularFile)
                    .map(this::doGet)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear() {
        try(Stream<Path> walk = Files.list(directory)) {
            walk.forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        String[] list = directory.toFile().list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }
}
