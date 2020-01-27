package com.maxkosh.webapp.storage.serializer;

import com.maxkosh.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializerStrategy {

    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(sectionType.name());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeUTF(sectionType.name());
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> stringList = listSection.getStringList();
                        dos.writeInt(stringList.size());
                        for (String string : stringList) {
                            dos.writeUTF(string);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        dos.writeUTF(sectionType.name());
                        CompanySection companySection = (CompanySection) entry.getValue();
                        List<Company> companyList = companySection.getCompanyList();
                        dos.writeInt(companyList.size());
                        for (Company company : companyList) {
                            Link homePage = company.getHomePage();
                            dos.writeUTF(homePage.getCompanyName());
                            if (homePage.getUrl() == null) {
                                dos.writeUTF("null");
                            } else {
                                dos.writeUTF(homePage.getUrl());
                            }
                            List<Company.Position> companyPosition = company.getPositions();
                            dos.writeInt(companyPosition.size());
                            for (Company.Position position : companyPosition) {
                                dos.writeUTF(position.getPositionTitle());
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                if (position.getDescription() == null) {
                                    dos.writeUTF("null");
                                } else {
                                    dos.writeUTF(position.getDescription());
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
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
                        TextSection textSection = new TextSection(dis.readUTF());
                        resume.addSection(sectionType, textSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int listSize = dis.readInt();
                        List<String> stringList = new ArrayList<>();
                        for (int j = 0; j < listSize; j++) {
                            stringList.add(dis.readUTF());
                        }
                        ListSection listSection = new ListSection(stringList);
                        resume.addSection(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int companyListSize = dis.readInt();
                        List<Company> companyList = new ArrayList<>();
                        for (int j = 0; j < companyListSize; j++) {
                            String companyName = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("null")) {
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
                                if (description.equals("null")) {
                                    description = null;
                                }
                                companyPositionList.add(new Company.Position(positionTitle, startDate, endDate, description));
                            }
                            companyList.add(new Company(link, companyPositionList));
                        }
                        resume.addSection(sectionType, new CompanySection(companyList));
                        break;
                }
            }
            return resume;
        }
    }
}
