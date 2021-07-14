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
public class teacherhomeTest {
    
    public teacherhomeTest() {
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
     * Test of start_courses_db method, of class teacherhome.
     */
    @Test
    public void testStart_courses_db() {
        System.out.println("start_courses_db");
        teacherhome instance = new teacherhome();
        instance.start_courses_db();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of student_list method, of class teacherhome.
     */
    @Test
    public void testStudent_list() {
        System.out.println("student_list");
        teacherhome instance = new teacherhome();
        instance.student_list();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of enroll_List method, of class teacherhome.
     */
    @Test
    public void testEnroll_List() {
        System.out.println("enroll_List");
        teacherhome instance = new teacherhome();
        instance.enroll_List();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of courses_List method, of class teacherhome.
     */
    @Test
    public void testCourses_List() {
        System.out.println("courses_List");
        teacherhome instance = new teacherhome();
        instance.courses_List();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    

    
    
    @Test
    public void testSearchcourse_negative() {
        System.out.println("searchcourse");
        user a = new user(5000,"mernamahmoud","mernamahmoud","mernamahmoud","teacher");
        teacherhome instance = new teacherhome(a);
        boolean expResult = false;
        boolean result = instance.searchcourse(-1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    @Test
    public void testSearchcourse_NotExists() {
        System.out.println("searchcourse");
        user a = new user(5000,"mernamahmoud","mernamahmoud","mernamahmoud","teacher");
        teacherhome instance = new teacherhome(a);
        boolean expResult = false;
        boolean result = instance.searchcourse(90);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    @Test
    public void testSearchcourse_Exists() {
        System.out.println("searchcourse");
        user a = new user(5000,"mernamahmoud","mernamahmoud","mernamahmoud","teacher");
        teacherhome instance = new teacherhome(a);
        boolean expResult = false;
        boolean result = instance.searchcourse(4);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    /**
     * Test of updateTables method, of class teacherhome.
     */
    @Test
    public void testUpdateTables() {
        System.out.println("updateTables");
        teacherhome instance = new teacherhome();
        instance.updateTables();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of main method, of class teacherhome.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        teacherhome.main(args);
    }

    /**
     * Test of teacherhome method, of class teacherhome.
     */
    @Test
    public void testTeacherhome() {
        System.out.println("teacherhome");
        user a = new user(55915,"mernamahmoud","mernamahmoud","mernamahmoud","teacher");
        teacherhome instance = new teacherhome(a);
        instance.teacherhome(a);
    }
    
}
