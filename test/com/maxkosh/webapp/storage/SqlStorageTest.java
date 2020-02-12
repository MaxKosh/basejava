package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}