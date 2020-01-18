package com.maxkosh.webapp.storage.serializer;

import com.maxkosh.webapp.model.Resume;

import java.io.*;

public interface SerializerStrategy {

    void doWrite(Resume resume, OutputStream os) throws IOException;

    Resume doRead(InputStream is) throws IOException;
}
