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
    //static final String JDBC_DRIVER = "org.h2.Driver";
    //static final String DB_PATH = /*"jdbc:h2:file:" + */Paths.get("", "dbH2").toAbsolutePath().toString();
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

    // Важно! Оба члена класса пришлось объявить статик (я не очень люблю статик объекты, можно ли обыграть иначе?).
    static Connection m_con = null;
    static Properties mProperties;

    public DBH2(Properties properties) {
        Statement stmt = null;
        mProperties = properties;
        try {
            // Check if table DB_TABLE_ACCOUNTS exist
            ResultSet res = getConnection().getMetaData().getTables(null, null, DB_TABLE_ACCOUNTS, null);
            boolean tableExists = false;
            if (res.next())
                tableExists = true;
            //Execute a query for table DB_TABLE_ACCOUNTS:
            if (!tableExists) {
                System.out.println("Creating table " + DB_TABLE_ACCOUNTS + " in given database...");
                stmt = getConnection().createStatement();
                String sql = "CREATE TABLE " + DB_TABLE_ACCOUNTS
                        + " ("
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
            se.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }
    public static Connection getConnection() {
        if (m_con != null) return m_con;
        // get db, user, pass from settings file
        String db = mProperties.getProperty("db_name");;
        String user ="";
        String pass ="";
        return getConnection(db, user, pass);
    }
    private static Connection getConnection(String db_name,String user_name,String password) {
        try
        {
            String jdbc_driver = mProperties.getProperty("jdbc_driver");
            String driver_manager = mProperties.getProperty("driver_manager_property");
            //Class.forName("com.mysql.jdbc.Driver");
            //m_con = DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+user_name+"&password="+password);
            Class.forName(jdbc_driver);
            Display.connectingToDB();
            String path = Paths.get("", "").toAbsolutePath().toString() + "\\";
            m_con = DriverManager.getConnection(driver_manager + path + db_name);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return m_con;
    }
    @Override
    public Optional<Task> getTask(String taskId) {
        PreparedStatement prpStmt = null;
        Optional<Task> task = Optional.empty();
        try {
            String sql = "SELECT * FROM " + DB_TABLE_TASKS + " WHERE UUID = ?";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1, taskId);
            ResultSet rs = prpStmt.executeQuery();
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
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return task;
    }
    @Override
    public Optional<Task> addTask(UpdateTask updateTask) {
        PreparedStatement stmt = null;
        try {
            //check user existence
            if(!getUser(updateTask.getOwnerId()).isPresent())
                return Optional.empty();
            //Generate new task which will add to H2db
            String uuid = UUID.randomUUID().toString();
            Task newTask = new Task(uuid, updateTask.getOwnerId(), updateTask.getName(), updateTask.getDescription(), updateTask.isOpen(), new Date());
            //Prepare sql request.
            String sql = "insert into " + DB_TABLE_TASKS + " values (?,?,?,?,?,?)";
            stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, newTask.getUuid());
            stmt.setString(2, newTask.getName());
            stmt.setString(3, newTask.getOwner());
            stmt.setString(4, newTask.getDescription());
            stmt.setString(5, newTask.isOpen().toString().toUpperCase());
            stmt.setString(6, newTask.getCreated().toString());
            // Execute a query
            stmt.executeUpdate();

            return getTask(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
    @Override
    public Optional<Task> updateTask(String taskId, UpdateTask update) {
        PreparedStatement prpStmt = null;
        Optional<Task> task = Optional.empty();
        try {
            // Prepare the query
            String sql = "UPDATE "
                    + DB_TABLE_TASKS
                    + " SET "
                    + DB_TASK_NAME + "=?,"
                    + DB_TASK_OWNER + "=?,"
                    + DB_TASK_DESCRIPTION + "=?,"
                    + DB_TASK_ISOPEN + "=?"
                    + " WHERE "
                    + DB_TASK_ID + "=?";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1, update.getName());
            prpStmt.setString(2, update.getOwnerId());
            prpStmt.setString(3, update.getDescription());
            prpStmt.setBoolean(4, update.isOpen());
            prpStmt.setString(5, taskId);
            // Execute update query
            prpStmt.executeUpdate();
            // Return updated task.
            task = getTask(taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return task;
    }
    @Override
    public boolean deleteTask(String taskId, UpdateTask updateTask) {
        PreparedStatement prpStmt = null;
        try {
            Task task = getTask(taskId).get();
            if (!task.getOwner().equals(updateTask.getOwnerId())){
                Display.haveNotPermission();
                return false;
            }
            String sql = "DELETE  FROM " + DB_TABLE_TASKS + " WHERE " + DB_TASK_ID + " = ?";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1, taskId);
            prpStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Return true if not exist
        return getTask(taskId).isEmpty();
    }
    @Override
    public List<Task> getTaskList(String userId, boolean onlyOpened) {
        PreparedStatement prpStmt = null;
        List<Task> taskList = new ArrayList<>();
        try {
            String sql;
            if (onlyOpened)
                sql = "SELECT * FROM " + DB_TABLE_TASKS + " WHERE " + DB_TASK_OWNER + " = ? AND " + DB_TASK_ISOPEN + " = TRUE";
            else
                sql = "SELECT * FROM " + DB_TABLE_TASKS + " WHERE " + DB_TASK_OWNER + " = ?";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1, userId);
            ResultSet rs = prpStmt.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return taskList;
    }
    @Override
    public Optional<User> addUser(UpdateUser update) {
        if(getUser(update.getId()).isPresent())
            return Optional.empty();
        PreparedStatement prpStmt = null;
        try {
            String sql = "insert into " + DB_TABLE_ACCOUNTS + " values ( ?, ?, ? )";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1, update.getId());
            prpStmt.setString(2, update.getSurname());
            prpStmt.setString(3, new Date().toString());
            prpStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getUser(update.getId());
    }
    @Override
    public Optional<User> updateUser(String userID, UpdateUser update) {
        PreparedStatement prpStmt = null;
        Optional<User> user = Optional.empty();
        try {
            String sql ="UPDATE "
                    + DB_TABLE_ACCOUNTS
                    + " SET "
                    + DB_USER_NAME + "=?, "
                    + DB_USER_SURNAME + "=? "
                    + " WHERE "
                    + DB_USER_NAME + "=?";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1, update.getId());
            prpStmt.setString(2, update.getSurname());
            prpStmt.setString(3, userID);
            prpStmt.executeUpdate();
            prpStmt.close();
            if(!userID.equals(update.getId())){
                List<Task> taskList = getTaskList(userID);
                taskList.forEach(task -> updateTask(task.getUuid(), new UpdateTask(update.getId(), task.getName(), task.getDescription(), task.isOpen())));
            }
            user = getUser(update.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    @Override
    public boolean deleteUser(String id) {
        return false;
    }
    @Override
    public Optional<User> getUser(String userId) {
        PreparedStatement prpStmt = null;
        User user = null;
        try {
            String sql = "SELECT * FROM " + DB_TABLE_ACCOUNTS + " WHERE LOGIN = ?";
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.setString(1,userId);
            ResultSet rs = prpStmt.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                String name = rs.getString(DB_USER_NAME);
                String surname = rs.getString(DB_USER_SURNAME);
                Date date = StringHelper.toDate(rs.getString(DB_USER_CREATED));
                user = new User(name, surname, date);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void clearAll() {
        PreparedStatement prpStmt = null;
        try {
            String sql = "DELETE  FROM " + DB_TABLE_TASKS;
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.executeUpdate();
            sql = "DELETE  FROM " + DB_TABLE_ACCOUNTS;
            prpStmt = getConnection().prepareStatement(sql);
            prpStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Clean-up environment
        finally {
            try {
                if (prpStmt != null)
                    prpStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deinitialization() {
        try {
            m_con.close();
            m_con = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
