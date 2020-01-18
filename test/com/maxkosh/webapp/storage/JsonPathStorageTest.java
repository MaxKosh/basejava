package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.storage.serializer.JsonStreamSerializer;
import com.maxkosh.webapp.storage.serializer.XmlStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new JsonStreamSerializer()));
    }
}
