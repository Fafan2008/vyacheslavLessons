package toDoListProject.components.repositories.dispetcher.h2;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.repositories.dispetcher.IDB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class DBH2 implements IDB {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_PATH = "jdbc:h2:file:" + Paths.get("", "dbH2").toAbsolutePath().toString();
    static final String DB_TABLE_TASKS = "TASK1";
    static final String DB_TABLE_ACCOUNTS = "ACCOUNTS1";

    public DBH2(){
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_PATH);
            System.out.println("Checking of existing " + DB_TABLE_ACCOUNTS + " table...");
            // Check if table exist
            ResultSet res = conn.getMetaData().getTables(null, null, "ACCOUNTS1", null);
            boolean tableExists = false;
            if (res.next()){
                tableExists = true;
                System.out.println("ACCOUNTS table exist.");
            }else{
                System.out.println("ACCOUNTS table not exist!!!");
            }

            //STEP 3: Execute a query
            if(!tableExists){
                System.out.println("Creating table " + DB_TABLE_ACCOUNTS + " in given database...");
                stmt = conn.createStatement();
                String sql =  "CREATE TABLE " + DB_TABLE_ACCOUNTS + " " +
                        "( LOGIN VARCHAR(255), " +
                        " SURNAME VARCHAR(255), " +
                        " CREATED VARCHAR(255), " +
                        " PRIMARY KEY ( LOGIN ))";
                stmt.executeUpdate(sql);
                System.out.println("Created table ACCOUNTS in given database...");
            }

            // Check if table exist
            res = conn.getMetaData().getTables(null, null, "TASKS1", null);
            tableExists = false;
            if (res.next()){
                tableExists = true;
                System.out.println("TASKS table exist.");
            }else{
                System.out.println("TASKS table not exist!!!");
            }

            //REPEAT STEP 3 for table TASKS: Execute a query
            if(!tableExists){
                System.out.println("Creating table " + DB_TABLE_TASKS + " in given database...");
                stmt = conn.createStatement();
                String sql =  "CREATE TABLE " + DB_TABLE_TASKS + " " +
                        "( UUID VARCHAR(255), " +
                        " OWNER VARCHAR(255), " +
                        " NAME VARCHAR(255), " +
                        " DESCRIPTION VARCHAR(255), " +
                        " ISOPEN BOOLEAN, " +
                        " CREATED VARCHAR(255), " +
                        " PRIMARY KEY ( UUID ))";
                stmt.executeUpdate(sql);
                System.out.println("Created table " + DB_TABLE_TASKS + " in given database...");
            }

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public Optional<Task> getTask(String taskId) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> addTask(UpdateTask task) {

        return Optional.empty();
    }

    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        return Optional.empty();
    }

    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        return false;
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        return null;
    }

    @Override
    public Optional<User> addUser(UpdateUser update) {
        Connection conn = null;
        Statement stmt = null;
        User user = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_PATH);
            System.out.println("Connected database successfully...");

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO "+DB_TABLE_ACCOUNTS+" VALUES (" +
                    "'" + update.getId() + "'"
                    + "," +
                    "'" + update.getSurname() + "'"
                    + "," +
                    "'" + new Date().toString() + "'"
                    + ")";

            stmt.executeUpdate(sql);

            System.out.println("Inserted record into the table...");

            sql = "SELECT LOGIN, SURNAME, CREATED FROM "+DB_TABLE_ACCOUNTS+" WHERE LOGIN = '" + update.getId() +"'";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                String name  = rs.getString("LOGIN");
                String surname = rs.getString("SURNAME");
                String strDate = rs.getString("CREATED");
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", new Locale("en_US"));
                Date date = formatter.parse(strDate);
                user = new User(name, surname, date);
            }

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> updateUser(UpdateUser update) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public Optional<User> getUser(String userId) {
        return Optional.empty();
    }
}
