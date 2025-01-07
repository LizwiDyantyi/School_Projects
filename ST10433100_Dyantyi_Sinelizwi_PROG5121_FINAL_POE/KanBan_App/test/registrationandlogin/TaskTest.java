package registrationandlogin;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TaskTest {

    @Test
    public void testCreateTaskID() {
        Task task = new Task("Create Login", 1, "To Do", "Description", "Mike Smith", 5);
        assertEquals("CR:1:SMI", task.createTaskID());
    }

    @Test
    public void testTaskDurationCalculation() {
        Task[] tasks = {
                new Task("Create Login", 1, "To Do", "Description", "Mike Smith", 5),
                new Task("Create Add Features", 2, "Doing", "Description", "Edward Harrison", 8),
                new Task("Create Reports", 3, "Done", "Description", "Samantha Paulson", 2),
        };
        assertEquals(15, tasks[0].returnTotalHours(tasks));
    }

    @Test
    public void testValidTaskDescription() {
        Task task = new Task("Test", 1, "To Do", "Short Description", "Developer Name", 1);
        assertTrue(task.checkTaskDescription());
    }
}


