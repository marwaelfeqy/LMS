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
public class studenthomeTest {
    
    public studenthomeTest() {
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
     * Test of show_courses method, of class studenthome.
     */
    @Test
    public void testShow_courses() {
        System.out.println("show_courses");
        
        user a = new user(106,",arwa","mernamahmoud","mernamahmoud","student");
        studenthome instance = new studenthome(a);
        instance.show_courses();
    }

    /**
     * Test of show_mycourses method, of class studenthome.
     */
    @Test
    public void testShow_mycourses() {
        System.out.println("show_mycourses");
        
        user a = new user(106,",arwa","mernamahmoud","mernamahmoud","student");
        studenthome instance = new studenthome(a);
        instance.show_mycourses();
    }

    /**
     * Test of hidepanel method, of class studenthome.
     */
    @Test
    public void testHidepanel() {
        System.out.println("hidepanel");
        user a = new user(106,",arwa","mernamahmoud","mernamahmoud","student");
        studenthome instance = new studenthome(a);
        instance.hidepanel();
    }

    /**
     * Test of updateTables method, of class studenthome.
     */
    @Test
    public void testUpdateTables() {
        System.out.println("updateTables");
        user a = new user(106,",arwa","mernamahmoud","mernamahmoud","student");
        studenthome instance = new studenthome(a);
        instance.updateTables();
    }

    /**
     * Test of main method, of class studenthome.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        studenthome.main(args);
    }
    
}
