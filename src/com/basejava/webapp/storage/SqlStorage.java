package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.Section;
import com.basejava.webapp.model.SectionType;
import com.basejava.webapp.sql.SqlHelper;
import com.basejava.webapp.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM contact");
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            sqlHelper.execute("DELETE FROM contact WHERE resume_uuid=?", ps -> {
                ps.setString(1, r.getUuid());
                ps.execute();
                return null;
            });
            sqlHelper.execute("DELETE  FROM section WHERE resume_uuid=?", ps -> {
                ps.setString(1, r.getUuid());
                ps.execute();
                return null;
            });

            insertContact(r, conn);
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
                for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, e.getKey().name());
                    Section section = e.getValue();
                    ps.setString(3, JsonParser.write(section, Section.class));
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return null;
        });

        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });

    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, r.getFullName());
                    ps.execute();
                }
                insertContact(r, conn);
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
                    for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, e.getKey().name());
                        Section section = e.getValue();
                        ps.setString(3, JsonParser.write(section, Section.class));
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                return null;
            }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
                Resume r;
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid =?")) {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    r = new Resume(uuid, rs.getString("full_name"));
                }

                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid =?")) {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        addContact(rs, r);
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid =?")) {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String content = rs.getString("content");
                        if (content != null) {
                            SectionType type = SectionType.valueOf(rs.getString("type"));
                            r.addSection(type, JsonParser.read(content, Section.class));
                        }
                    }
                }
            return r;
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
        return sqlHelper.execute("" +
                        "   SELECT * FROM resume r\n" +
                        "LEFT JOIN contact c ON r.uuid = c.resume_uuid\n" +
                        "ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> resumes = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume r = resumes.get(uuid);
                if (r == null) {
                    r = new Resume(uuid, rs.getString("full_name"));
                    resumes.put(uuid, r);
                }
                addContact(rs, r);
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }
}
