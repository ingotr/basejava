package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.ObjectSerializer;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_PATH.toString(), new ObjectSerializer()));
    }
}
