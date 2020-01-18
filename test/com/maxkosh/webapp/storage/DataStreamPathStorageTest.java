package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.storage.serializer.DataStreamSerializer;

public class DataStreamPathStorageTest extends AbstractStorageTest {
    public DataStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new DataStreamSerializer()));
    }
}
