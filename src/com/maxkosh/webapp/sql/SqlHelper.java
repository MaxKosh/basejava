package com.maxkosh.webapp.sql;

import com.maxkosh.webapp.exception.ExistStorageException;
import com.maxkosh.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sqlRequest, SqlExecutor<T> sqlExecutor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            return sqlExecutor.doExecute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e.getMessage());
            }
            throw new StorageException(e);
        }
    }
}
