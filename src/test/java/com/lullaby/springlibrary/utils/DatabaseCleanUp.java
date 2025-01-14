package com.lullaby.springlibrary.utils;


import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;


@ActiveProfiles("test")
@Service
public class DatabaseCleanUp {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DatabaseCleanUp(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Transactional
    public void execute() {
        final List<String> tableNames = extractTableNames();
        execute("SET FOREIGN_KEY_CHECKS = 0");

        for (final String tableName : tableNames) {
            execute("TRUNCATE TABLE " + tableName);
        }

        execute("SET FOREIGN_KEY_CHECKS = 1");
    }

    private List<String> extractTableNames() {
        return jdbcTemplate.query("SHOW TABLES", (resultSet, rowNumber) -> resultSet.getString(1));
    }

    private void execute(final String query) {
        jdbcTemplate.update(query, Collections.emptyMap());
    }

}
