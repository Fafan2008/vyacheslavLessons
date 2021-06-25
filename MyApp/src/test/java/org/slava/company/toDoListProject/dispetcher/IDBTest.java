package org.slava.company.toDoListProject.dispetcher;

import org.junit.*;
import org.slava.company.toDoListProject.components.entities.task.Task;
import org.slava.company.toDoListProject.components.entities.task.UpdateTask;
import org.slava.company.toDoListProject.components.entities.user.UpdateUser;
import org.slava.company.toDoListProject.components.entities.user.User;
import org.slava.company.toDoListProject.components.repositories.dispetcher.IDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public abstract class IDBTest {
    /*static*/ IDB idb;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public abstract IDB createIDB();

    @Before
    public void init(){
        idb = createIDB();
    }
    @After
    public void deinit(){
        idb.clearAll();
        idb.deinitialization();
    }
    // Важно! Метода AfterClass и BeforeClass могут быть только static из-за этого idb становится тоже static.
    // Более того если мы хотим всего один раз подключиться метода createIDB() тоже должен стать static из-за этого не получается седалть все по красоте.
//    @AfterClass
//    public static void endClass() {
//        idb.deinitialization();
//    }
    @Test
    public void addAndGetUserTest(){
        Optional<User> userOpt = idb.addUser(new UpdateUser("user", "surname"));
        assertEquals("user", userOpt.isPresent() ? userOpt.get().getId() : "empty");
        assertEquals("surname", userOpt.isPresent() ? userOpt.get().getSurname() : "empty");
        assertEquals(userOpt.get(), idb.getUser("user").get());
    }
    @Test
    public void addAndGetUserTaskTest(){
        assertTrue(idb.addUser(new UpdateUser("user", "surname")).isPresent());
        Optional<Task> taskOpt = idb.addTask(new UpdateTask("user", "taskName","taskDescription", true));
        List<Task> tasks = idb.getTaskList("user");
        assertEquals(1, tasks.size());
        String id = tasks.get(0).getUuid();
        assertEquals(taskOpt.get(), idb.getTask(id).get());
    }
    @Test
    public void updateUserNameAndSurnameTest(){
        Optional<User> oldUserOpt = idb.addUser(new UpdateUser("user", "surname"));
        UpdateUser updateUser = new UpdateUser("newName", "newSurname");
        Optional<User> newUserOpt = idb.updateUser("user", updateUser);
        assertNotEquals(oldUserOpt.get(), newUserOpt.get());
        assertTrue(idb.getUser("user").isEmpty());
        assertEquals(newUserOpt.get(), idb.getUser("newName").get());
    }
    @Test
    public void checkRelatedTasksAfterChangingUserNameTest(){
        Optional<User> oldUserOpt = idb.addUser(new UpdateUser("user", "surname"));
        assertTrue(idb.addTask(new UpdateTask("user", "taskName1","taskDescription1", true)).isPresent());
        assertTrue(idb.addTask(new UpdateTask("user", "taskName2","taskDescription2", true)).isPresent());
        assertEquals(2, idb.getTaskList("user").size());
        Optional<User> newUserOpt = idb.updateUser("user", new UpdateUser("newName", "newSurname"));
        assertTrue(newUserOpt.isPresent());
        assertFalse(idb.getUser(oldUserOpt.get().getId()).isPresent());
        assertEquals(0, idb.getTaskList("user").size());
        assertEquals(2, idb.getTaskList("newName").size());
        assertTrue(idb.getUser("user").isEmpty());
        assertTrue(idb.getUser("newName").isPresent());
    }
    @Test
    public void updateTaskNameTest(){
        assertTrue(idb.addUser(new UpdateUser("user", "surname")).isPresent());
        Optional<Task> taskOpt = idb.addTask(new UpdateTask("user", "taskName","taskDescription", true));
        idb.updateTask(taskOpt.get().getUuid(), new UpdateTask(taskOpt.get().getOwner(), "newTaskName", taskOpt.get().getDescription(), taskOpt.get().isOpen()));
        assertEquals("newTaskName", idb.getTask(taskOpt.get().getUuid()).get().getName());
        assertEquals("taskDescription", idb.getTask(taskOpt.get().getUuid()).get().getDescription());
        assertEquals("user", idb.getTask(taskOpt.get().getUuid()).get().getOwner());
    }
    @Test
    public void updateTaskDescriptionTest(){
        assertTrue(idb.addUser(new UpdateUser("user", "surname")).isPresent());
        Optional<Task> taskOpt = idb.addTask(new UpdateTask("user", "taskName","taskDescription", true));
        idb.updateTask(taskOpt.get().getUuid(), new UpdateTask(taskOpt.get().getOwner(), taskOpt.get().getName(), "newTaskDescription", taskOpt.get().isOpen()));
        assertEquals("newTaskDescription", idb.getTask(taskOpt.get().getUuid()).get().getDescription());
        assertEquals("taskName", idb.getTask(taskOpt.get().getUuid()).get().getName());
        assertEquals("user", idb.getTask(taskOpt.get().getUuid()).get().getOwner());
    }
    @Test
    public void updateTaskOwnerTest(){
        User user1 = idb.addUser(new UpdateUser("user1", "surname1")).get();
        User user2 = idb.addUser(new UpdateUser("user2", "surname2")).get();
        Task task1 = idb.addTask(new UpdateTask("user1", "taskName1","description1", true)).get();
        idb.addTask(new UpdateTask("user1", "taskName2", "description2", true)).get();
        assertEquals(2, idb.getTaskList(user1.getId()).size());
        assertEquals(0, idb.getTaskList(user2.getId()).size());
        idb.updateTask(task1.getUuid(), new UpdateTask(user2.getId(), task1.getName(), task1.getDescription(), task1.isOpen()));
        assertEquals(1, idb.getTaskList(user1.getId()).size());
        assertEquals(1, idb.getTaskList(user2.getId()).size());
        assertEquals(user2.getId(), idb.getTaskList(user2.getId()).get(0).getOwner());
        assertEquals(user1.getId(), idb.getTaskList(user1.getId()).get(0).getOwner());
    }
}