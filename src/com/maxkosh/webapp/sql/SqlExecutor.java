package com.maxkosh.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SqlExecutor<T> {
    T doExecute(PreparedStatement ps) throws SQLException;
}
