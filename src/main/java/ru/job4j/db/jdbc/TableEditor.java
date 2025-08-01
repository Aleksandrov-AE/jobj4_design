package ru.job4j.db.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("state.properties")) {
            properties.load(in);
            String driver = properties.getProperty("db.driver");
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void createTable(String tableName) {
        validateIdentifier(tableName);
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name TEXT"
        );
        execute(sql);
    }


    public void dropTable(String tableName) {
        validateIdentifier(tableName);
        String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        validateIdentifier(tableName);
        validateIdentifier(columnName);
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type
        );
        execute(sql);

    }

    public void dropColumn(String tableName, String columnName) {
        validateIdentifier(tableName);
        validateIdentifier(columnName);
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        validateIdentifier(tableName);
        validateIdentifier(columnName);
        validateIdentifier(newColumnName);
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        execute(sql);

    }

    private void validateIdentifier(String name) {
        if (!name.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            throw new IllegalArgumentException("Invalid identifier: " + name);
        }
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        String tableName = "test";
        String addColumnName = "pet";
        String addColumnNameType = "varchar(20)";
        try (TableEditor editor = new TableEditor(new Properties())) {
            editor.createTable(tableName);
            System.out.println(editor.getTableScheme(tableName));
            editor.addColumn(tableName, addColumnName, addColumnNameType);
            System.out.println(editor.getTableScheme(tableName));
            editor.dropColumn(tableName, addColumnName);
            System.out.println(editor.getTableScheme(tableName));
            editor.renameColumn(tableName, "name", "first_name");
            System.out.println(editor.getTableScheme(tableName));
            editor.dropTable(tableName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
