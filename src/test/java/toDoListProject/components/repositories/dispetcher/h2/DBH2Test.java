package toDoListProject.components.repositories.dispetcher.h2;

import toDoListProject.components.repositories.dispetcher.IDB;
import toDoListProject.components.repositories.dispetcher.IDBTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class DBH2Test extends IDBTest {

    @Override
    public IDB createIDB() {
        String propertiesPath = Paths.get("", "db_test.properties").toAbsolutePath().toString();
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DBH2(appProps);
    }
}