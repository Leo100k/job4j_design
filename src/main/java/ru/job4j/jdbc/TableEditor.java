package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws IOException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws IOException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            String url = config.getProperty("url");  /*jdbc:postgresql:localhost:5432/some_db*/
            String username = config.getProperty("username"); /*postgres*/
            String password = config.getProperty("password"); /*root*/

            connection = DriverManager
                    .getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (%s, %s);", tableName,
                "id SERIAL PRIMARY KEY",
                "name TEXT"
        );
        exequteSQL(sql, tableName);
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s", tableName);
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(String tableName, String columnName, String typeColumn) {
        String sql = String.format(
                "ALTER TABLE " + tableName + " ADD " + columnName + " " + typeColumn);
        exequteSQL(sql, tableName);

    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE " + tableName + " DROP COLUMN " + columnName);
        exequteSQL(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName);
        exequteSQL(sql, tableName);

    }

    private void exequteSQL(String sql, String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
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

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        TableEditor tableEditor = new TableEditor(prop);
        tableEditor.createTable("test_demo");
        tableEditor.addColumn("test_demo", "address", "varchar(100)");
        tableEditor.renameColumn("test_demo", "address", "location");
        tableEditor.dropColumn("test_demo", "location");
        tableEditor.dropTable("test_demo");

    }

}

