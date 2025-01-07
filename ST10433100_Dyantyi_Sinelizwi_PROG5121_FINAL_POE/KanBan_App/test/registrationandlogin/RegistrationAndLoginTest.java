/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package registrationandlogin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_lab
 */
public class RegistrationAndLoginTest {
    
    RegistrationAndLogin login = new RegistrationAndLogin();
   
    
    public RegistrationAndLoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testIsUsernameValid() 
    {
        assertTrue("“Welcome <user first name> ,<user last name>. It is great to see you.", login.isUsernameValid("kyl_1"));
    }

    @Test
    public void testIsUsernameInvalid() 
    {
        assertFalse("""
                    Username is not correctly formatted, please 
                    ensure that your username contains an 
                    underscore and is no more than 5 characters in 
                    length""", login.isUsernameValid("kyle!!!!!!!"));
    }
    
    
    @Test
    public void testIsPasswordValid() 
    {
        assertTrue("Password successfully captured", login.isPasswordValid("Ch&&sec@ke99!"));
    }
    
    
    @Test
    public void testIsPasswordInvalid() 
    {
        assertFalse("""
                    Password is not correctly formatted, please 
                    ensure that the password contains at least 8 
                    characters, a capital letter, a number and a 
                    special character.""", login.isPasswordValid("password"));
    }

    
    @Test
    public void LoginWithCorrectCredentials ()
    {
        login.AccountRegistration("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!");
        
       
        
    }
    
    
    @Test
    public void testAccountRegistration() 
    {
        
    }

    
    @Test
    public void testMain()
    {
    
    }
    
}
