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
public class adminhomeTest {
    
    public adminhomeTest() {
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
     * Test of courses_List method, of class adminhome.
     */
    @Test
    public void testCourses_List() {
        System.out.println("courses_List");
        adminhome instance = new adminhome();
        instance.courses_List();
    }

    /**
     * Test of startdb method, of class adminhome.
     */
    @Test
    public void testStartdb() {
        System.out.println("startdb");
        adminhome instance = new adminhome();
        instance.startdb();
    }

    /**
     * Test of users_List method, of class adminhome.
     */
    @Test
    public void testUsers_List() {
        System.out.println("users_List");
        adminhome instance = new adminhome();
        instance.users_List();
    }

    /**
     * Test of show_user method, of class adminhome.
     */
    //@Test
    public void testShow_user() {
        System.out.println("show_user");
        adminhome instance = new adminhome();
        instance.show_user();
    }

    /**
     * Test of show_students method, of class adminhome.
     */
    @Test
    public void testShow_students() {
        System.out.println("show_student");
        adminhome instance = new adminhome();
        boolean result =instance.show_students();
        assertEquals(true,result);
    }

    /**
     * Test of updateTables method, of class adminhome.
     */
    //@Test
    public void testUpdateTables() {
        System.out.println("updateTables");
        adminhome instance = new adminhome();
        instance.updateTables();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hide_table method, of class adminhome.
     */
    @Test
    public void testHide_table() {
        System.out.println("hide_table");
        adminhome instance = new adminhome();
        instance.hide_table();
    }

    /**
     * Test of customize method, of class adminhome.
     */
    @Test
    public void testCustomize() {
        System.out.println("customize");
        adminhome instance = new adminhome();
        instance.customize();
    }

    /**
     * Test of emailExists method, of class adminhome.
     */
    @Test
    public void testEmailExists_Teatcher() {
        System.out.println("emailExists");
        String e = "tarekallam@gmail.com";
        adminhome instance = new adminhome();
        boolean expResult = true;
        boolean result = instance.emailExists(e);
        assertEquals(expResult, result);
    }
    @Test
    public void testEmailExists_Student() {
        System.out.println("emailExists");
        String e = "ranaahmed@gmail.com";
        adminhome instance = new adminhome();
        boolean expResult = true;
        boolean result = instance.emailExists(e);
        assertEquals(expResult, result);
    }
    @Test
    public void testEmailExists_New() {
        System.out.println("emailExists");
        String e = "rahmaahmed@gmail.com";
        adminhome instance = new adminhome();
        boolean expResult = false;
        boolean result = instance.emailExists(e);
        assertEquals(expResult, result);
    }
    @Test
    public void testEmailExists_Empty() {
        System.out.println("emailExists");
        String e = "";
        adminhome instance = new adminhome();
        boolean expResult = true;
        boolean result = instance.emailExists(e);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void validEmailtest_Empty() {
        System.out.println("emailExists");
        String e = "";
        adminhome instance = new adminhome();
        boolean expResult = false;
        boolean result = instance.validEmail(e);
        assertEquals(expResult, result);
    }
    @Test
    public void validEmailtest_invalid() {
        System.out.println("emailExists");
        String e = "amrmohamed@gmail";
        adminhome instance = new adminhome();
        boolean expResult = false;
        boolean result = instance.validEmail(e);
        assertEquals(expResult, result);
    }
     @Test
    public void validEmailtest_valid() {
        System.out.println("emailExists");
        String e = "amrmohamed@gmail.com";
        adminhome instance = new adminhome();
        boolean expResult = true;
        boolean result = instance.validEmail(e);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of main method, of class adminhome.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        adminhome.main(args);
    }

    
    
}
