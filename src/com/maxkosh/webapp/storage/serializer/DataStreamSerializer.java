package com.maxkosh.webapp.storage.serializer;

import com.maxkosh.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializerStrategy {

    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeWithException(resume.getContacts().entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeWithException(resume.getSections().entrySet(), dos, entry -> {
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
                        writeWithException(((ListSection) section).getStringList(), dos, dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeWithException(((CompanySection) entry.getValue()).getCompanyList(), dos, company -> {
                            Link homePage = company.getHomePage();
                            dos.writeUTF(homePage.getCompanyName());
                            if (homePage.getUrl() == null) {
                                dos.writeUTF("");
                            } else {
                                dos.writeUTF(homePage.getUrl());
                            }
                            writeWithException(company.getPositions(), dos, position -> {
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
            Section section = null;
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int listSize = dis.readInt();
                        List<String> stringList = new ArrayList<>();
                        for (int j = 0; j < listSize; j++) {
                            stringList.add(dis.readUTF());
                        }
                        section = new ListSection(stringList);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int companyListSize = dis.readInt();
                        List<Company> companyList = new ArrayList<>();
                        for (int j = 0; j < companyListSize; j++) {
                            String companyName = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("")) {
                                url = null;
                            }
                            Link link = new Link(companyName, url);
                            int positionListSize = dis.readInt();
                            List<Company.Position> companyPositionList = new ArrayList<>();
                            for (int k = 0; k < positionListSize; k++) {
                                String positionTitle = dis.readUTF();
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String description = dis.readUTF();
                                if (description.equals("")) {
                                    description = null;
                                }
                                companyPositionList.add(new Company.Position(positionTitle, startDate, endDate, description));
                            }
                            companyList.add(new Company(link, companyPositionList));
                        }
                        section = new CompanySection(companyList);
                        break;
                }
                resume.addSection(sectionType, section);
            }
            return resume;
        }
    }


    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Operator<T> operator) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            operator.operate(t);
        }
    }

    @FunctionalInterface
    interface Operator<T> {
        void operate(T t) throws IOException;
    }
}


