package com.maxkosh.webapp.storage;


import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.fail;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}