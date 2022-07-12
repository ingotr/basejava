package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected Resume doGet(File file) {
        Objects.requireNonNull(file, "file must not be null");
        return doParse(file);
    }

    protected abstract Resume doParse(File file);

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("file was not deleted", file.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try (Stream<Path> walk = Files.walk(Paths.get("./directory"))) {
            return walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .map(Resume::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear() {
        try (Stream<Path> walk = Files.walk(Paths.get("./directory"))) {
            walk.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList())
                    .clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        try (Stream<Path> walk = Files.walk(Paths.get("./directory"))) {
            return (int) walk.filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
