package registrationandlogin;

import java.util.*;
import javax.swing.*;


public class Main {
       
    //Arrays below to store the information of tasks
    private static ArrayList <String> Developers = new ArrayList<>();
    private static ArrayList <String> TaskNames = new ArrayList<>();
    private static ArrayList <String> TaskIDs = new ArrayList<>();
    private static ArrayList <Integer> TaskDurations = new ArrayList<>();
    private static ArrayList <String> TaskStatuses = new ArrayList<>();
    
    
    
    //start of main method
    public static void main(String[] args) {
        
       
        RegistrationAndLogin login = new RegistrationAndLogin();
        Task[] tasks = null; //this is the array to store tasks
        
        
        
        // Display initial options using JOptionPane
         while (true) {
            // Display the menu and capture the user's input
            String UserInput = JOptionPane.showInputDialog("Hi! Welcome to your Kanban board!\n"
                + "Please select an option:\n"
                + "1. Register\n"
                + "2. Login\n"
                + "3. Quit");
            
            // Check if the user pressed Cancel or entered an empty input
                if (UserInput == null || UserInput.trim().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty. Please enter a number between 1 and 3.");
                    continue; // Go back to the start of the loop
                }
            
            
            // Parse the input
            int UserChoice;
            try {
                UserChoice = Integer.parseInt(UserInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 3.");
                continue; // Go back to the start of the loop
            }
        
         //case statement to give the user an option to register, login or quit
         switch (UserChoice) 
         {
            case 1: //case 1 is the registration process
     
                String FirstName = JOptionPane.showInputDialog("Please enter your first name:");
                String LastName = JOptionPane.showInputDialog("Please enter your last name:");
                String Username = JOptionPane.showInputDialog("Please enter your username:");
                String Password = JOptionPane.showInputDialog("Please enter your password:");
        
                login.AccountRegistration(FirstName, LastName, Username, Password);
                JOptionPane.showMessageDialog(null, "Registration Successful! You may now login to your account.");
            break;
        
            case 2: //case 2 is the login process
                String CapturedUsername = JOptionPane.showInputDialog("Please enter your username:");
                String CapturedPassword = JOptionPane.showInputDialog("Please enter your password:");
                
                if (login.Login(CapturedUsername, CapturedPassword))
                {
                    JOptionPane.showMessageDialog(null, "Login Succesful! Welcome to EasyKanban!");
                    
                    
                    int option;
                    int totalTasks = 0;
                    
                    do
                    {
                        option = Integer.parseInt(JOptionPane.showInputDialog(
                        "Please choose an option: \n" +
                        "1. Add tasks \n" +
                        "2. Show report \n" +
                        "3. Quit"
                        ));
                        
                        switch (option)
                        { 
                            case 1: //add tasks
                                totalTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do  you want to add?"));
                                tasks = new Task [totalTasks];
                                
                                for (int i = 0; i < totalTasks; i++)
                                {
                                    tasks[i] = createTask(i); //calling the createTask method
                                }
                                break;
                                
                            case 2: //show report
                                if (TaskNames.isEmpty())
                                {
                                    JOptionPane.showMessageDialog(null, "There are no tasks to show. Please start by adding a task.");
                                }
                                else
                                {
                                    ViewReport();
                                }
                                break;
                                
                            case 3: //quit
                                JOptionPane.showMessageDialog(null, "Exiting the app. Goodbye!");
                                break;
                                
                            default: 
                                JOptionPane.showMessageDialog(null, "Invalid Option. Please try again");                                    
                        }
                    } 
                    while (option != 3); //loop executes until user chooses 'quit'
                    
                    if (tasks != null && tasks.length > 0)
                    {
                       JOptionPane.showMessageDialog(null, "TotalHours across all tasks: " + tasks[0].returnTotalHours(tasks));
                    }
                
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "Login attempt unsuccessful. Plesae try again.");
                }
                break;
                
            case 3: //terminate or quit the program
                JOptionPane.showMessageDialog(null, "Aww... sad to see you go. Goodbye!");
                System.exit(0);
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Invalid option. Please select either 1 to Register, 2 to Login, or 3 to Quit. ");
                break;        
        
    }
    }//end of main method
    }
    
    //createTask method (which asks for users to insert task details
    public static Task createTask (int taskNumber)
    {
        String taskName = JOptionPane.showInputDialog("Please type in the task name: ");
        String taskDescription = JOptionPane.showInputDialog("Plesae enter the Task Description: ");
        
        while (taskDescription.length() > 50)
        {
            taskDescription = JOptionPane.showInputDialog("Please enter the task description: (please limit it to 50 characters- this includes the spaces.");
        }
        
        String developerDetails = JOptionPane.showInputDialog("Please enter the developers's details (first and last name");
        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the Task Duration in hours: "));
        
        String[] statuses = {"To Do", "Done", "Doing"};
        String taskStatus = (String) JOptionPane.showInputDialog(null, "Plesae select task Status", "Task Status",JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
        
        int taskNumberFixed = TaskNames.size() +1;
        Task task = new Task(taskName, taskNumber, taskStatus, taskDescription, developerDetails, taskDuration);
        JOptionPane.showMessageDialog(null, task.printTaskDetails());
        
        return task;
        
    }
    

    
    
    
    //manageTask method
    private static void ManageTasks()
    {
        while (true)
        {
            String Option = JOptionPane.showInputDialog("""
                                                        Task Management: 
                                                        1. Add Task
                                                         2. View Report
                                                         3. Delete Task
                                                         4. Search
                                                         5. Quit""");
            
            if (Option==null) break;
            int Choice = Integer.parseInt(Option);
            switch (Choice) 
            {
                case 1:
                    AddTask();
                    break;
                    
                case 2:
                    ViewReport();
                    break;
                    
                case 3:
                    DeleteTask();
                    break;
                    
                case 4:
                    SearchTask();
                    break;
                    
                case 5:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Ooops! Invalid choice. Please try again, only type in a number from 1 to 5.");
            }
            
        }
    }
     
    
    private static void AddTask()
    {
        String TaskName = JOptionPane.showInputDialog("Please enter the Task Name: ");
        String TaskDescription = JOptionPane.showInputDialog("Please enter the description of your task: ");
        String DeveloperName = JOptionPane.showInputDialog("Please enter the Developer Name: ");
        int Duration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the task duration [in hours]: "));
        String Status = JOptionPane.showInputDialog("Please enter the task status ['To Do', 'Doing', or 'Done]: ");
        
        Task task = new Task(TaskName, TaskNames.size() +1, Status, TaskDescription, DeveloperName, Duration);
        
        //populating the arrays below
        Developers.add(DeveloperName);
        TaskNames.add(TaskName);
        TaskIDs.add(task.createTaskID());
        TaskDurations.add(Duration);
        TaskStatuses.add(Status);
        
        JOptionPane.showMessageDialog(null, "Task added successfully!");
    }
    
    private static void ViewReport()
    {
            
            if(TaskNames.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No tasks available to display. Please add tasks first.");
                return;
            }
   
    
        StringBuilder report = new StringBuilder("Task report:\n");
        for (int i = 0; i < TaskNames.size(); i++)
        {
            report.append("Task ").append(i+1).append(": ")
                  .append("Name: ").append(TaskNames.get(i)).append(", ")
                  .append("Developer: ").append(Developers.get(i)).append(", ")
                  .append("Status: ").append(TaskStatuses.get(i)).append(", ")
                  .append("Duration: ").append(TaskDurations.get(i)).append(" hours\n");
        }
        
        JOptionPane.showMessageDialog(null, report.toString());
    }
    
    
    private static void DeleteTask()
    {
        String TaskName = JOptionPane.showInputDialog("Please enter the Task Name that you wish to DELETE: ");
        int Index = TaskNames.indexOf(TaskName);
        
        if (Index>=0)
        {
            Developers.remove(Index);
            TaskNames.remove(Index);
            TaskIDs.remove(Index);
            TaskDurations.remove(Index);
            TaskStatuses.remove(Index);
            
            JOptionPane.showMessageDialog(null, "The task has been successfully deleted!");
        }
        
        else
        {
            JOptionPane.showMessageDialog(null, "Ooops! Task notfound. Please try again and check spelling of Task Name.");
        }
    }
    
    
    private static void SearchTask()
    {
        String TaskName = JOptionPane.showInputDialog("Please enter the task name you are searching for:");
        int Index = TaskNames.indexOf(TaskName);
        
        if (Index>=0)
        {
            JOptionPane.showMessageDialog(null, """
                                                Task Details:
                                                Name: """ + TaskNames.get(Index) + "\n" +
                    "Task ID: " + TaskIDs.get(Index) + "\n" +
                    "Developer: " + Developers.get(Index) + "\n" +
                    "Status: " + TaskStatuses.get(Index) + "\n" +
                    "Duration: " + TaskDurations.get(Index) + " hours");
        }
        
        else 
        {
            JOptionPane.showMessageDialog(null, "Ooops! Task not found. Please re-enter the task name.");
        }
    }
    
    
    
}
