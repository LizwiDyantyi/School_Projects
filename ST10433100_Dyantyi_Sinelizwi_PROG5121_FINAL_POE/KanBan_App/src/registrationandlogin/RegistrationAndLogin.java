package registrationandlogin;

import java.util.*;
import javax.swing.JOptionPane;
import java.util.Map;
import java.util.HashMap;


public class RegistrationAndLogin {


      private Map<String, String> users = new HashMap<>(); //this stroes the users credentials

    //internal class to validate/check the username
    public boolean isUsernameValid (String Username)
    {
        return Username.length() <= 5 && Username.contains ("_");
    }
    
    //internal class to validate the password using regex
    public boolean isPasswordValid (String Password)
    {
        //^: Starting regex string
        //(?=.* [A-Z]): contains uppercase
        //(?=.* [0-9]): contains a number
        //(?=.* [!@#$%^&*~`?]): contains a special character
        //. (8,): length is 8 characters long
        //$: end of string
        
        String PasswordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*~`?]).{8,}$";
        return Password.matches(PasswordPattern);
        
    }
    
    
    //account registration method
    public void AccountRegistration (String FirstName, String LastName, String Username, String Password)
    {
        boolean isUsernameValid = isUsernameValid(Username);
        boolean isPasswordValid = isPasswordValid(Password);
        
        if (isUsernameValid && isPasswordValid)
        {
            users.put(Username, Password);//stores the data
            System.out.println("Registration Successful!");
            System.out.println("Username and Password captured correctly.");
            
             // Debugging line to confirm users are stored correctly
             System.out.println("Registered Users: " + users);
        }
        
        else
        {
            if (!isUsernameValid)
            {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore,"
                    + "and is no more than five (5) characters in length.");
        }
        
        if (!isPasswordValid)
        {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, "
                    + "a capital letter, a number, and a special character.");
        }
        }
        
    }//end of first class
    
    
    //Login Method
    public boolean Login(String username, String password) {
        // Validate the user's credentials
        if (users.containsKey(username)) {
            return users.get(username).equals(password);
        }
        return false; // Username not found
    }

   
 
    }
    
    
