package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new XmlStreamSerializer()));
    }
}
