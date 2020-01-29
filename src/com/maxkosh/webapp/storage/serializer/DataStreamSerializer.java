package com.maxkosh.webapp.storage.serializer;

import com.maxkosh.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements SerializerStrategy {

    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            write(resume.getContacts().entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            write(resume.getSections().entrySet(), dos, entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        write(((ListSection) section).getStringList(), dos, dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        write(((CompanySection) entry.getValue()).getCompanyList(), dos, company -> {
                            Link homePage = company.getHomePage();
                            dos.writeUTF(homePage.getCompanyName());
                            if (homePage.getUrl() == null) {
                                dos.writeUTF("");
                            } else {
                                dos.writeUTF(homePage.getUrl());
                            }
                            write(company.getPositions(), dos, position -> {
                                dos.writeUTF(position.getPositionTitle());
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                if (position.getDescription() == null) {
                                    dos.writeUTF("");
                                } else {
                                    dos.writeUTF(position.getDescription());
                                }
                            });
                        });
                        break;
                }
            });
        }
    }

    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            simpleRead(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            simpleRead(dis, () -> {
                Section section = null;
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section = new ListSection(readList(dis, dis::readUTF));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        section = new CompanySection(readCompany(dis));
                        break;
                }
                resume.addSection(sectionType, section);
            });
            return resume;
        }
    }

    @FunctionalInterface
    interface SimpleWriter<T> {
        void doSimpleWrite(T t) throws IOException;
    }

    private <T> void write(Collection<T> collection, DataOutputStream dos, SimpleWriter<T> simpleWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            simpleWriter.doSimpleWrite(t);
        }
    }

    @FunctionalInterface
    interface SimpleReader {
        void doSimpleRead() throws IOException;
    }

    private void simpleRead(DataInputStream dis, SimpleReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.doSimpleRead();
        }
    }

    @FunctionalInterface
    interface ListReader<T> {
        T doListRead() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ListReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> stringList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stringList.add(reader.doListRead());
        }
        return stringList;
    }

    private List<Company> readCompany(DataInputStream dis) throws IOException {
        return readList(dis, () -> {
            String companyName = dis.readUTF();
            String url = dis.readUTF();
            if (url.equals("")) {
                url = null;
            }
            Link link = new Link(companyName, url);
            return new Company(link, readList(dis, () -> {
                String positionTitle = dis.readUTF();
                LocalDate startDate = LocalDate.parse(dis.readUTF());
                LocalDate endDate = LocalDate.parse(dis.readUTF());
                String description = dis.readUTF();
                if (description.equals("")) {
                    description = null;
                }
                return new Company.Position(positionTitle, startDate, endDate, description);
            })
            );
        });
    }
}


