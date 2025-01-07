package registrationandlogin;
import javax.swing.*;


public class Task {
    
    private String taskName;
    private int taskNumber;
    private String taskStatus;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    
    //static arrays to store task data
    public static String[] Developers = new String[4];
    public static String[] TaskNames = new String[4];
    public static String[] TaskIDs= new String[4];
    public static int[] TaskDurations = new int[4];
    public static String[] TaskStatuses = new String[4];    
    
    
    public Task(String taskName, int taskNumber, String taskStatus, String taskDescription, String developerDetails, int taskDuration)
    {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        
        //store task information in arrays
        Developers[taskNumber -1] = developerDetails;
        TaskNames[taskNumber -1] = taskName;
        TaskIDs[taskNumber-1] = createTaskID();
        TaskDurations[taskNumber-1] = taskDuration;
        TaskStatuses[taskNumber-1] = taskStatus;
    }
    
    public boolean checkTaskDescription()
    {
        return this.taskDescription.length() <= 50;
    }
    
    public String createTaskID()
    {
        String FirstTwoLetters = taskName.substring(0, 2).toUpperCase();
        String LastThreeLetters = developerDetails.split(" ")[1].substring(0, 3).toUpperCase();
        return FirstTwoLetters + ":" +taskNumber + ":" +  LastThreeLetters;
    }
    
    public String printTaskDetails()
    {
        return "Task ID: " + createTaskID() + "\n" +                
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Status: " + taskStatus + "\n" +
               "Developer Status: " + developerDetails + "\n" +
               "Task Description: " + taskDescription + "\n " +
               "Task Duration: " + taskDuration + " hours";
                
    }
    
    public int getTaskDuration()
    {
        return this.taskDuration;
    }
    
    //adding a task and then returning back to JOptionPane menu
    public static void AddTaskWithReturn()
    {
        try
        {
            //collect task details
            String TaskName = JOptionPane.showInputDialog("Please enter the task name: ");
            int TaskNumber = Integer.parseInt(JOptionPane.showInputDialog("Please enter Task Number: "));
            String TaskStatus = JOptionPane.showInputDialog("Please enter Task Status (To-Do, Doing, Done):");
            String TaskDescription = JOptionPane.showInputDialog("Please enter Task Description (maximum 50 characters):");
            String DeveloperDetails = JOptionPane.showInputDialog("Please enter Developer Details (First and Last Name):");
            int TaskDuration = Integer.parseInt(JOptionPane.showInputDialog("Please enter Task Duration (in hours):"));
            
            Task task = new Task(TaskName, TaskNumber, TaskStatus, TaskDescription, DeveloperDetails, TaskDuration);
            
            if (!task.checkTaskDescription())
            {
                JOptionPane.showMessageDialog(null, "The task description entered is too long! Please limit it to50characters- this includes numbers and spaces.");
                return;
            }
            
            JOptionPane.showMessageDialog(null, "Task has been added successfully!\n\n" + task.printTaskDetails());
            
            //returning to main menu
            
            String[] options = {"Add Another Task", "Search For Tasks", "Search For Developer", "Delete a Task", "Show Report", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Please select an option: ", "Task Menu", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            switch (choice) 
            {
                case 0 -> AddTaskWithReturn(); //adds a task
                case 1 -> 
                {
                    String taskName = JOptionPane.showInputDialog ("Please enter the Task Name you want to search for:");
                    searchTaskByName(taskName);
                }
                case 2 -> 
                {
                    String DeveloperName = JOptionPane.showInputDialog("Please enter the Developer's Name you wish to search for:");
                    searchTasksByDeveloper(DeveloperName);
                }
                case 3 ->
                {
                    String TasksToDelete = JOptionPane.showInputDialog("Please enter the task name of the task you wish to Delete:");
                    deleteTaskByName(TasksToDelete);
                }
                case 4-> displayReport();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the appliation. See you soon!");
                default -> JOptionPane.showMessageDialog(null, "Invalid choice!");
            }
           
                        
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    
    public static void displayFinishedTasks()
    {
        for (int i = 0; i < TaskStatuses.length; i++)
        {
            if ("Done".equalsIgnoreCase(TaskStatuses[i]))
            {
                System.out.println(Developers[i] + " - " + TaskNames[i] +  " - " + TaskDurations[i] + " hours");
            }
        }
    }
    
    
    public static void displayLongestDurationTask()
    {
        int maxDuration = 0;
        int maxIndex = 0;
        for (int i = 0; i < TaskDurations.length; i++)
        {
            if (TaskDurations[i] > maxDuration)
            {
                maxDuration = TaskDurations[i];
                maxIndex = i;
            }
        }
        System.out.println("Your Longest Task  is: " + Developers[maxIndex] + " - " + TaskNames[maxIndex] + " - " + TaskDurations[maxIndex] + " hours");
    }
    
    
     public static void searchTaskByName(String taskName)
    {
        for (int i = 0; i < TaskNames.length; i++)
        {
            if (TaskNames[i].equalsIgnoreCase(taskName))
            {
                System.out.println("Task: " + TaskNames[i] + " - Developer: " + Developers[i] + " - Status: " + TaskStatuses[i]);
            }
        }
    }
    
     
    
    public static void searchTasksByDeveloper(String DeveloperName) {
    StringBuilder result = new StringBuilder("Tasks assigned to " + DeveloperName + ":\n");
    boolean found = false;

    for (int i = 0; i < Developers.length; i++) {
        if (Developers[i] != null && Developers[i].equalsIgnoreCase(DeveloperName)) {
            result.append("Task Name: ").append(TaskNames[i]).append("\n")
                  .append("Task Status: ").append(TaskStatuses[i]).append("\n")
                  .append("-----------------------------\n");
            found = true;
        }
    }

    if (found) {
        JOptionPane.showMessageDialog(null, result.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No tasks found for developer: " + DeveloperName);
    }
}

     
     
     
     
     
     public static void deleteTaskByName(String taskName)
    {
        for (int i = 0; i <TaskNames.length; i++)
        {
            if (TaskNames[i].equalsIgnoreCase(taskName))
            {
                TaskNames[i] = null;
                Developers[i] = null;
                TaskDurations[i] = 0;
                TaskStatuses[i] = null;
                TaskIDs[i] = null;
                System.out.println("Task: " + taskName + " successfully deleted.");
                return;
            }
        }
        System.out.println("Task not found.");
    }
     
     
     
    
    
    public int returnTotalHours(Task[] tasks)
    {
        int totalHours =0;
        for (Task task : tasks)
        {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
    
    
    
     public static void displayReport()
    {
        for (int i = 0; i < TaskNames.length; i++)
        {
            if (TaskNames[i] != null)
            {
                System.out.println("Task ID: " + TaskIDs[i]);
                System.out.println("Task Name: " + TaskNames[i]);
                System.out.println("Developer: " + Developers[i]);
                System.out.println("Duration: " + TaskDurations[i] + " hours");
                System.out.println("Status: " + TaskStatuses[i]);
                System.out.println("----------------------------");
            }
        }
    }
     
    public void displayMainMenu()
    {
        try 
        {
            String[] options = {"Add Another Task", "Search for Tasks", "Search for Developer",
                                "Delete a Task", "Show Report", "Exit"};
            
       int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Task Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
       
       switch (choice)
       {
          case 0 -> AddTaskWithReturn(); // Add another task
          
          case 1 ->
          {
              String taskName = JOptionPane.showInputDialog("Enter the Task Name to search:");
                    searchTaskByName(taskName);
          }
          
          case 2 ->
          {
              String developerName = JOptionPane.showInputDialog("Enter the Developer's Name to search:");
                    searchTasksByDeveloper(developerName);
          }
          
          case 3 ->
          {
               String taskToDelete = JOptionPane.showInputDialog("Enter the Task Name to delete:");
                    deleteTaskByName(taskToDelete);
          }
          
          case 4 -> displayReport();
          case 5 -> JOptionPane.showMessageDialog(null, "Exiting the application. See you soon!");
          default -> JOptionPane.showMessageDialog(null, "Invalid choice!");
       }
       
       //returning to the main menu
       if (choice != 5) 
       {
                displayMainMenu();
       }     
       }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " +e.getMessage());
            
        }
       
    }
    
 
            
            
            
            
    
}
