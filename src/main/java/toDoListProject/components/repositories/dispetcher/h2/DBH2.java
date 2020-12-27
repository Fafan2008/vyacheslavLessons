package toDoListProject.components.repositories.dispetcher.h2;

import toDoListProject.components.entities.task.Task;
import toDoListProject.components.entities.task.UpdateTask;
import toDoListProject.components.entities.user.UpdateUser;
import toDoListProject.components.entities.user.User;
import toDoListProject.components.presenters.console.Display;
import toDoListProject.components.presenters.console.additinalPackage.StringHelper;
import toDoListProject.components.repositories.dispetcher.IDB;

import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class DBH2 implements IDB {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_PATH = "jdbc:h2:file:" + Paths.get("", "dbH2").toAbsolutePath().toString();
    static final String DB_TABLE_TASKS = "TASK4";
    static final String DB_TABLE_ACCOUNTS = "ACCOUNTS4";
    static final String DB_TASK_ID = "UUID";
    static final String DB_TASK_OWNER = "OWNER";
    static final String DB_TASK_NAME = "NAME";
    static final String DB_TASK_DESCRIPTION = "DESCRIPTION";
    static final String DB_TASK_ISOPEN = "ISOPEN";
    static final String DB_TASK_CREATED = "CREATED";
    static final String DB_USER_NAME = "LOGIN";
    static final String DB_USER_SURNAME = "SURNAME";
    static final String DB_USER_CREATED = "CREATED";
    static Connection m_con = null;

    public DBH2() {
        Statement stmt = null;
        try {
            // Check if table DB_TABLE_ACCOUNTS exist
            System.out.println("Checking of existing " + DB_TABLE_ACCOUNTS + " table...");
            ResultSet res = getConnection().getMetaData().getTables(null, null, DB_TABLE_ACCOUNTS, null);
            boolean tableExists = false;
            if (res.next()) {
                tableExists = true;
                System.out.println(DB_TABLE_ACCOUNTS + " table exist.");
            } else {
                System.out.println(DB_TABLE_ACCOUNTS + " table not exist!!!");
            }
            //Execute a query for table DB_TABLE_ACCOUNTS:
            if (!tableExists) {
                System.out.println("Creating table " + DB_TABLE_ACCOUNTS + " in given database...");
                stmt = getConnection().createStatement();

                String sql = "CREATE TABLE " + DB_TABLE_ACCOUNTS + " ("
                        + DB_USER_NAME + " VARCHAR(255) NOT NULL, "
                        + DB_USER_SURNAME + " VARCHAR(255), "
                        + DB_USER_CREATED + " VARCHAR(255), "
                        + " PRIMARY KEY ( " + DB_USER_NAME + " )"
                        + ")";
                stmt.executeUpdate(sql);
                System.out.println("Created table " + DB_TABLE_ACCOUNTS + " in given database...");
            }
            // Check if table DB_TABLE_TASKS exist
            res = getConnection().getMetaData().getTables(null, null, DB_TABLE_TASKS, null);
            tableExists = false;
            if (res.next()) {
                tableExists = true;
                System.out.println(DB_TABLE_TASKS + " table exist.");
            } else {
                System.out.println(DB_TABLE_TASKS + " table not exist!!!");
            }
            //REPEAT for table DB_TABLE_TASKS: Execute a query
            if (!tableExists) {
                System.out.println("Creating table " + DB_TABLE_TASKS + " in given database...");
                stmt = getConnection().createStatement();
                String sql = "CREATE TABLE " + DB_TABLE_TASKS + " ("
                        + DB_TASK_ID + " VARCHAR(255), "
                        + DB_TASK_NAME + " VARCHAR(255), "
                        + DB_TASK_OWNER + " VARCHAR(255), "
                        + DB_TASK_DESCRIPTION + " VARCHAR(255), "
                        + DB_TASK_ISOPEN + " BOOLEAN, "
                        + DB_TASK_CREATED + " VARCHAR(255), "
                        + " PRIMARY KEY ( "+DB_TASK_ID+" )"
                        + ")";
                stmt.executeUpdate(sql);
                System.out.println("Created table " + DB_TABLE_TASKS + " in given database...");
            }
        } catch (Exception se) {
            //Handle errors
            se.printStackTrace();
        }
        finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        } //end finally try
    }

    public static Connection getConnection()
    {
        if (m_con != null) return m_con;
        // get db, user, pass from settings file
        String db ="";
        String user ="";
        String pass ="";
        return getConnection(db, user, pass);
    }
    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            //m_con = DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+user_name+"&password="+password);
            Class.forName(JDBC_DRIVER);
            Display.connectingToDB();
            m_con = DriverManager.getConnection(DB_PATH);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return m_con;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public Optional<Task> getTask(String taskId) {
        //System.out.println("Request task from database...");
        Statement stmt = null;
        Optional<Task> task = Optional.empty();
        try {
            stmt = getConnection().createStatement();
            String sql = "SELECT * FROM " + DB_TABLE_TASKS
                    + " WHERE UUID = "+ StringHelper.quote(taskId);
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while (rs.next()) {
                String uuid = rs.getString(DB_TASK_ID);
                String owner = rs.getString(DB_TASK_OWNER);
                String name = rs.getString(DB_TASK_NAME);
                String description = rs.getString(DB_TASK_DESCRIPTION);
                Boolean isOpen = rs.getBoolean(DB_TASK_ISOPEN);
                Date date  = StringHelper.toDate(rs.getString(DB_TASK_CREATED));
                task = Optional.of(new Task(uuid, owner, name, description, isOpen, date));
            }
            rs.close();
        } catch (Exception e) {
            // Handle errors
            e.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        } // end finally try
        return task;
    }

    @Override
    public Optional<Task> addTask(UpdateTask updateTask) {
        //System.out.println("Adding task to database...");
        Statement stmt = null;
        try {
            //check user existence
            if(!getUser(updateTask.userId()).isPresent())
                return Optional.empty();
            // Execute a query
            stmt = getConnection().createStatement();
            String uuid = UUID.randomUUID().toString();
            //Generate new task which will add to H2db
            Task newTask = new Task(uuid, updateTask.userId(), updateTask.Name(), updateTask.Description(), updateTask.isOpen(), new Date());

            String sql = "INSERT INTO " + DB_TABLE_TASKS + " VALUES ("
                    + StringHelper.quote(newTask.getId())
                    + ","
                    + StringHelper.quote(newTask.getName())
                    + ","
                    + StringHelper.quote(newTask.getOwner())
                    + ","
                    + StringHelper.quote(newTask.getDescription())
                    + ","
                    + newTask.isOpen().toString().toUpperCase()
                    + ","
                    + StringHelper.quote(newTask.getCreated().toString())
                    + ")";
            stmt.executeUpdate(sql);
            return getTask(uuid);
        } catch (Exception e) {
            // Handle errors for
            e.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } // end finally try
        return Optional.empty();
    }

    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        System.out.println("Updating task in database...");
        Statement stmt = null;
        Optional<Task> task = Optional.empty();
        try {
            // Execute a query
            stmt = getConnection().createStatement();
            String sql = "UPDATE " + DB_TABLE_TASKS + " SET "
                    + DB_TASK_NAME + " = " + StringHelper.quote(update.Name())
                    + ", "
                    + DB_TASK_DESCRIPTION + " = " + StringHelper.quote(update.Description())
                    + ", "
                    + DB_TASK_ISOPEN + " = " + update.isOpen().toString().toUpperCase()
                    + " WHERE UUID = " + StringHelper.quote(taskId);
            stmt.executeUpdate(sql);
            task = getTask(taskId);
        } catch (Exception e) {
            // Handle errors for JDBC
            e.printStackTrace();
        }// Handle errors for Class.forName
        finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }// end try
        } // end finally try
        return task;
    }

    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        //System.out.println("Deleting task...");
        Statement stmt = null;
        boolean isDeleted = false;
        try {
            Task task = getTask(taskId).get();
            if (!task.getOwner().equals(updateTask.userId())){
                Display.haveNotPermission();
                return false;
            }
            // Execute a query
            stmt = getConnection().createStatement();
            String sql = "DELETE  FROM " + DB_TABLE_TASKS
                    + " WHERE " + DB_TASK_ID + " = " + StringHelper.quote(taskId);
            stmt.executeUpdate(sql);
            // Extract data from result set
            if(!getTask(taskId).isPresent()){
                isDeleted =true;
            }
        } catch (Exception e) {
            // Handle errors
            e.printStackTrace();
        }
        finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            } // end try
        } // end finally try
        return isDeleted;
    }

    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        //System.out.println("Request task list from database...");
        Statement stmt = null;
        List<Task> taskList = new ArrayList<>();
        try {
            //  Execute a query
            stmt = getConnection().createStatement();
            String sql;
            if (onlyOpened)
                sql = "SELECT * FROM " + DB_TABLE_TASKS
                        + " WHERE " + DB_TASK_OWNER + " = " + StringHelper.quote(userId)
                        + " AND " + DB_TASK_ISOPEN + " = TRUE";
            else
                sql = "SELECT * FROM " + DB_TABLE_TASKS
                        + " WHERE " + DB_TASK_OWNER + " = " + StringHelper.quote(userId);
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while (rs.next()) {
                String uuid = rs.getString(DB_TASK_ID);
                String owner = rs.getString(DB_TASK_OWNER);
                String name = rs.getString(DB_TASK_NAME);
                String description = rs.getString(DB_TASK_DESCRIPTION);
                Boolean isOpen = rs.getBoolean(DB_TASK_ISOPEN);
                Date date  = StringHelper.toDate(rs.getString(DB_TASK_CREATED));
                taskList.add(new Task(uuid, owner, name, description, isOpen, date));
            }
            rs.close();
        } catch (Exception se) {
            // Handle errors
            se.printStackTrace();
        }
        finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        } // end finally try
        return taskList;
    }

    @Override
    public Optional<User> addUser(UpdateUser update) {
        //System.out.println("Adding new user to database...");
        Statement stmt = null;
        try {
            // Execute a query
            stmt = getConnection().createStatement();
            String sql = "INSERT INTO " + DB_TABLE_ACCOUNTS + " VALUES ("
                    + StringHelper.quote(update.getId())
                    + ","
                    + StringHelper.quote(update.getSurname())
                    + ","
                    + StringHelper.quote(new Date().toString())
                    + ")";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            // Handle errors
            e.printStackTrace();
        }
        finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } // end finally try
        return getUser(update.getId());
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
        //System.out.println("Request user data from database...");
        Statement stmt = null;
        User user = null;
        try {
            // Execute a query
            stmt = getConnection().createStatement();
            String sql = "SELECT * FROM " + DB_TABLE_ACCOUNTS
                    + " WHERE LOGIN = " + StringHelper.quote(userId);
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while (rs.next()) {
                String name = rs.getString(DB_USER_NAME);
                String surname = rs.getString(DB_USER_SURNAME);
                Date date = StringHelper.toDate(rs.getString(DB_USER_CREATED));
                user = new User(name, surname, date);
            }
            rs.close();
        } catch (Exception e) {
            // Handle errors
            e.printStackTrace();
        }//
        finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } // end finally try
        return Optional.ofNullable(user);
    }
}
