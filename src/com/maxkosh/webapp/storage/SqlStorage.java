package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.NotExistStorageException;
import com.maxkosh.webapp.model.*;
import com.maxkosh.webapp.sql.SqlHelper;
import com.maxkosh.webapp.util.JsonParser;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) " +
                    "VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            saveContactData(resume, connection);
            saveSectionData(resume, connection);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume " +
                    "SET full_name = ? " +
                    "WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteData(resume, connection, "DELETE  FROM contact WHERE resume_uuid=?");
            deleteData(resume, connection, "DELETE  FROM section WHERE resume_uuid=?");
            saveContactData(resume, connection);
            saveSectionData(resume, connection);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContacts(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSections(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(connection -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume r ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    resumes.put(uuid, new Resume(uuid, fullName));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("resume_uuid");
                    addContacts(rs, resumes.get(uuid));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("resume_uuid");
                    addSections(rs, resumes.get(uuid));
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) AS size FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("size") : 0;
        });
    }

    private void saveContactData(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (type, value, resume_uuid) " +
                "VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(2, entry.getValue());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void saveSectionData(Resume resume, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section (type, data, resume_uuid) " +
                "VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(3, resume.getUuid());
                Section section = entry.getValue();
                ps.setString(2, JsonParser.write(section, Section.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteData(Resume resume, Connection connection, String sql) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContacts(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.addContact(type, value);
        }
    }

    private void addSections(ResultSet rs, Resume resume) throws SQLException {
        String data = rs.getString("data");
        if (data != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.addSection(type, JsonParser.read(data, Section.class));
        }
    }
}
