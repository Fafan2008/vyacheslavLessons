package org.slava.company.toDoListProject.dispetcher.h2;

import org.slava.company.toDoListProject.components.repositories.dispetcher.IDB;
import org.slava.company.toDoListProject.components.repositories.dispetcher.h2.DBH2;
import org.slava.company.toDoListProject.dispetcher.IDBTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class DBH2Test extends IDBTest {

    @Override
    public IDB createIDB() {
        Properties appProps = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("db_test.properties");
        try {
            appProps.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DBH2(appProps);
    }
}