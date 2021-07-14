/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toshipa
 */
public class loginTest {
    
    public loginTest() {
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

    /**
     * Test of verify_admin method, of class login.
     */
    //@Test
    public void testVerify_Empty() {
        System.out.println("verify_admin");
        String enterd_email = "";
        String enterd_password = "";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_admin(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    @Test
    public void testVerify_InvalidData() {
        System.out.println("verify_admin");
        String enterd_email = "tet";
        String enterd_password = "ror";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_admin(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    @Test
    public void testVerify_admin_WrongPassword() {
        System.out.println("verify_admin");
        String enterd_email = "root";
        String enterd_password = "t";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_admin(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    @Test
    public void testVerify_admin_EmptyPassword() {
        System.out.println("verify_admin");
        String enterd_email = "root";
        String enterd_password = "";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_admin(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    @Test
    public void testVerify_EmptyEmail() {
        System.out.println("verify_admin");
        String enterd_email = "";
        String enterd_password = "root";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_admin(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    public void testVerify_admin_ValiedData() {
        System.out.println("verify_admin");
        String enterd_email = "root";
        String enterd_password = "root";
        login instance = new login();
        boolean expResult = true;
        boolean result = instance.verify_admin(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }

    /**
     * Test of verify_student method, of class login.
     */
    @Test
    public void testVerify_student_NotExists() {
        System.out.println("verify_student");
        String enterd_email = "";
        String enterd_password = "";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_student(enterd_email, enterd_password);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testVerify_student_WrongPassword() {
        System.out.println("verify_student");
        String enterd_email = "ranaahmed@gmail.com";
        String enterd_password = "";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_student(enterd_email, enterd_password);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testVerify_student_CorrectPassword() {
        System.out.println("verify_student");
        String enterd_email = "ranaahmed@gmail.com";
        String enterd_password = "1234";
        login instance = new login();
        boolean expResult = true;
        boolean result = instance.verify_student(enterd_email, enterd_password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of verify_teacher method, of class login.
     */
    @Test
    public void testVerify_teacher_NotExists() {
        System.out.println("verify_teacher");
        String enterd_email = "amr@here";
        String enterd_password = "";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_teacher(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    @Test
    public void testVerify_teacher_WrongPassword() {
        System.out.println("verify_teacher");
        String enterd_email = "tarekallam@gmail.com";
        String enterd_password = "8998";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.verify_teacher(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }
    @Test
    public void testVerify_teacher_CorrectPassword() {
        System.out.println("verify_teacher");
        String enterd_email = "tarekallam@gmail.com";
        String enterd_password = "1234";
        login instance = new login();
        boolean expResult = true;
        boolean result = instance.verify_teacher(enterd_email, enterd_password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeacherData method, of class login.
     */
    @Test
    public void testGetTeacherData_NotExists() {
        System.out.println("getTeacherData");
        String enterd_email = "";
        login instance = new login();
        user expResult = null;
        user result = instance.getTeacherData(enterd_email);
        assertEquals(expResult, result);
    }
    /**
     * Test of getTeatchertData method, of class login.
     * whitebox 
     * statement 9/10
     * loop 1/2
     */
    @Test
    public void testGetTeacherData_Exists() {
        System.out.println("getTeacherData");
        String enterd_email = "tarekallam@gmail.com";
        login instance = new login();
        user expResult = new user(5002,"tarek allam","tarekallam@gmail.com","teacher","1234");
        
        user result = instance.getTeacherData(enterd_email);
        boolean eq=result.equals(expResult);
        assertEquals(true, eq);
        
        
    }
    @Test
    public void testGetTeacherData_WrongPassword() {
        System.out.println("getTeacherData");
        String enterd_email = "tarekallam@gmail.com";
        login instance = new login();
        user expResult = new user(5002,"tarek allam","tarekallam@gmail.com","teacher","8888");
        
        user result = instance.getTeacherData(enterd_email);
        boolean eq=result.equals(expResult);
        assertEquals(false, eq);
        
        
    }

    /**
     * Test of getStudentData method, of class login.
     * whitebox 
     * statement 8/10
     * loop 1/2
     */
    @Test
    public void testGetStudentData_NotExists() {
        System.out.println("getStudentData");
        String enterd_email = "";
        login instance = new login();
        user expResult = null;
        user result = instance.getStudentData(enterd_email);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getStudentData method, of class login.
     * whitebox 
     * statement 9/10
     * loop 1/2
     */
    @Test
    public void testGetStudentData_Exists() {
        System.out.println("getStudentData");
        String enterd_email = "ranaahmed@gmail.com";
        login instance = new login();
        user expResult = new user(102,"rana ahmed","ranaahmed@gmail.com","student","1234");
        
        user result = instance.getStudentData(enterd_email);
        boolean eq=result.equals(expResult);
        assertEquals(true, eq);
        
        
    }
    @Test
    public void WrongPassword() {
        System.out.println("getStudentData");
        String enterd_email = "ranaahmed@gmail.com";
        login instance = new login();
        user expResult = new user(102,"rana ahmed","ranaahmed@gmail.com","student","8888");
        
        user result = instance.getStudentData(enterd_email);
        boolean eq=result.equals(expResult);
        assertEquals(false, eq);
        
        
    }
            
    /**
     * Test of main method, of class login.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        login.main(args);
    }
    
}
