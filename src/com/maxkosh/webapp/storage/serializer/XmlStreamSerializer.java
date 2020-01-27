package com.maxkosh.webapp.storage.serializer;

import com.maxkosh.webapp.model.*;
import com.maxkosh.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class XmlStreamSerializer implements SerializerStrategy {
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Company.class, Link.class,
                CompanySection.class, TextSection.class,
                ListSection.class, Company.Position.class);
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }
}
