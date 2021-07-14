/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toshipa
 */
public class teacherhome extends javax.swing.JFrame {
    String host;
    Connection con;
    String SQL;
    Statement stmt;
    ResultSet rs ;
    ArrayList<user> studentsList= new ArrayList<>();
    ArrayList<course> coursesList= new ArrayList<>();
    int lastcourseID=0;
    user teacher=null;
    Connection con_enroll,con_users;
    String SQL_enroll,SQL_users;
    Statement stmt_enroll,stmt_users;
    ResultSet rs_enroll,rs_users;
    ArrayList<enroll> enrollList= new ArrayList<>();
    ArrayList<user> usersList= new ArrayList<>();
    int search_id=-1;
    
    
    public void start_courses_db()
    {
         try
        {
        studentstable1.setModel(new DefaultTableModel(null,new String[]{"Student ID","Student Name","Course","Midterm Grades","Final Grades"}));
        coursestable.setModel(new DefaultTableModel(null,new String[]{"ID","Course Name","Teacher ID","Assistant ID","Max Number"}));
        
        coursesList.removeAll(coursesList);
        studentsList.removeAll(studentsList);
        usersList.removeAll(usersList);    
        enrollList.removeAll(enrollList);
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/system","root","root");        
        enroll e;
        courses_List();
        show_courses();  
        enroll_List();
        student_list();               
        }
        catch(Exception e)
        {
           System.out.print(e.getMessage());
           System.out.print("start_courses_database");

        }
         
    }
    public void student_list()
    {  
       /* try
        {
            PreparedStatement stmt=con.prepareStatement("select DISTINCT s.student_id , s.name,s.email"
                                                        + " from enroll as e,student as s , course as c "
                                                        + "where s.student_id = e.student_id and c.course_id=e.course_id and c.teacher_id=(?)");
            stmt.setInt(1,teacher.getId()); 
            ResultSet rs_users=stmt.executeQuery(); 
            user user;
            while(rs_users.next())
            {
                user=new user(rs_users.getInt("s.student_ID"),rs_users.getString("s.name"),rs_users.getString("s.email"),"student","");
                usersList.add(user);
            }
        }
        catch(Exception e)
        {
           System.out.print(e.getMessage());

        }*/}
    
     public void enroll_List()
    {  
        try
        {
            PreparedStatement stmt=con.prepareStatement("select DISTINCT e.student_id , e.course_id,e.mgrade,e.fgrade from enroll as e , course as c where c.course_id=e.course_id and c.teacher_id=(?)");
            stmt.setInt(1,teacher.getId()); 
            ResultSet rs_enroll=stmt.executeQuery(); 
            enroll e;
            while(rs_enroll.next())
            {
                {   
                    if(lastcourseID<rs_enroll.getInt("course_ID"))
                        lastcourseID=rs_enroll.getInt("course_ID");
                e=new enroll(rs_enroll.getInt("course_ID"),rs_enroll.getInt("student_id"),rs_enroll.getInt("fgrade"),rs_enroll.getInt("mgrade"));
                enrollList.add(e);
                System.out.println(rs_enroll.getInt("course_ID"));
                }
            }
            System.out.println("the last updated id is "+lastcourseID);
        }
        catch(Exception e)
        {
           System.out.print(e.getMessage());

        }}
    public void courses_List()
    {
        
        
        try
        {
            PreparedStatement stmt=con.prepareStatement("select * from course"); 
            ResultSet rs=stmt.executeQuery(); 
            course u;
            while(rs.next())
            {
                if(rs.getInt("course_id")>lastcourseID)
                lastcourseID = rs.getInt("course_id");
                if(rs.getInt("teacher_id")!= teacher.getId())
                {
                    continue;
                }
                u=new course(rs.getInt("course_ID"),rs.getInt("teacher_id"),rs.getInt("assistant_id"),rs.getString("name"),rs.getInt("maxnum"));
                coursesList.add(u);
                
            }
            System.out.println("the last updated id is "+lastcourseID);
        }
        catch(Exception e)
        {
           System.out.print(e.getMessage());

        }}
    void hidepanels()
    {   search_id=-1;
        jPaneloffercourse.setVisible(false);
        jPanelleft.setVisible(false);
        jPanelright.setVisible(false);
        jPanel2.setVisible(false);
        jScrollPane2.setVisible(false);
        jPanel3.setVisible(false);
        jTextField1.setText("");
        jTextField2.setText("");
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        students.setVisible(false);
        jPanel4.setVisible(false);
    }
    public void show_courses()
    {
        DefaultTableModel model = (DefaultTableModel)coursestable.getModel();
        Object[] row = new Object[5];
        for(int i=0;i<coursesList.size();i++)
        {
            row[0] = coursesList.get(i).getId();
            row[2] = coursesList.get(i).getTeacherid();
            row[3] = coursesList.get(i).getAssistantid();
            row[1] = coursesList.get(i).getCourse();
            row[4] = coursesList.get(i).getMax();
            
            model.addRow(row);
        }
    }
    public void show_students(int id)
    {
        DefaultTableModel model = (DefaultTableModel)studentstable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
         try
        {
            PreparedStatement stmt=con.prepareStatement("select DISTINCT e.student_id , s.name,c.name,e.mgrade,e.fgrade "
                                                        + " from enroll as e, course as c ,student as s"
                                                        + " where s.student_id = e.student_id and c.course_id=e.course_id and c.teacher_id=(?) and c.course_id=(?)");
            stmt.setInt(1,teacher.getId()); 
            stmt.setInt(2,id); 
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
              row[0] = rs.getString("e.student_id");
              row[1] = rs.getString("s.name");
              row[2] = rs.getString("c.name");
              row[3] = rs.getString("e.mgrade");
              row[4] = rs.getString("e.fgrade");
              model.addRow(row);
            }
        }
        catch(Exception e)
        {
           System.out.print(e.getMessage());

        }
        
    }
    public teacherhome() {
        //initComponents();
    }
    public teacherhome(user a) {
        teacher =new user(a);
        initComponents();
        hidepanels();
        jPanelleft.setVisible(true);
        start_courses_db();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelright = new javax.swing.JPanel();
        students = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane_students = new javax.swing.JScrollPane();
        studentstable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPaneloffercourse = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        coursestable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelleft = new javax.swing.JPanel();
        jButtonoffercourse = new javax.swing.JButton();
        jButton2viewstudents = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelright.setLayout(new java.awt.CardLayout());

        jScrollPane_students.setBorder(null);
        jScrollPane_students.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane_students.setOpaque(false);

        studentstable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        studentstable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Course ", "Midterm grade", "Final grade"
            }
        ));
        studentstable1.setFocusTraversalPolicyProvider(true);
        studentstable1.setOpaque(false);
        studentstable1.setRowHeight(22);
        studentstable1.setRowMargin(6);
        jScrollPane_students.setViewportView(studentstable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane_students, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane_students, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel9.setText("Course ID");

        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(55, 55, 55)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout studentsLayout = new javax.swing.GroupLayout(students);
        students.setLayout(studentsLayout);
        studentsLayout.setHorizontalGroup(
            studentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(studentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentsLayout.createSequentialGroup()
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        studentsLayout.setVerticalGroup(
            studentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentsLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelright.add(students, "card2");

        jLabel1.setText("Course Name");

        jLabel2.setText("Course Maximum Number");

        jButton1.setText("Offer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneloffercourseLayout = new javax.swing.GroupLayout(jPaneloffercourse);
        jPaneloffercourse.setLayout(jPaneloffercourseLayout);
        jPaneloffercourseLayout.setHorizontalGroup(
            jPaneloffercourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneloffercourseLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPaneloffercourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneloffercourseLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneloffercourseLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                        .addGroup(jPaneloffercourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(46, 46, 46))
        );
        jPaneloffercourseLayout.setVerticalGroup(
            jPaneloffercourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneloffercourseLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPaneloffercourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPaneloffercourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        jPanelright.add(jPaneloffercourse, "card2");

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(687, 350));

        jScrollPane2.setBorder(null);
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane2.setOpaque(false);

        coursestable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coursestable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Course Name", "Teacher ID", "Assistant ID", "Max Number"
            }
        ));
        coursestable.setFocusTraversalPolicyProvider(true);
        coursestable.setOpaque(false);
        coursestable.setRowHeight(22);
        coursestable.setRowMargin(6);
        jScrollPane2.setViewportView(coursestable);
        if (coursestable.getColumnModel().getColumnCount() > 0) {
            coursestable.getColumnModel().getColumn(0).setResizable(false);
            coursestable.getColumnModel().getColumn(0).setHeaderValue("ID");
        }

        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("Course ID");

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Corse Name");

        jLabel5.setText("Assistant");

        jLabel6.setText("Max Number");

        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanelright.add(jPanel2, "card2");

        jButtonoffercourse.setText("Offer a Course");
        jButtonoffercourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonoffercourseActionPerformed(evt);
            }
        });

        jButton2viewstudents.setText("Students");
        jButton2viewstudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2viewstudentsActionPerformed(evt);
            }
        });

        jButton2.setText("Courses");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelleftLayout = new javax.swing.GroupLayout(jPanelleft);
        jPanelleft.setLayout(jPanelleftLayout);
        jPanelleftLayout.setHorizontalGroup(
            jPanelleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelleftLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2viewstudents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonoffercourse, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanelleftLayout.setVerticalGroup(
            jPanelleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelleftLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton2viewstudents)
                .addGap(47, 47, 47)
                .addComponent(jButton2)
                .addGap(51, 51, 51)
                .addComponent(jButtonoffercourse)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelleft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 669, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(196, Short.MAX_VALUE)
                    .addComponent(jPanelright, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelleft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanelright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonoffercourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonoffercourseActionPerformed
        hidepanels();
        jPanelleft.setVisible(true);
        jPanelright.setVisible(true);
        jPaneloffercourse.setVisible(true);
        
        
    }//GEN-LAST:event_jButtonoffercourseActionPerformed

    private void jButton2viewstudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2viewstudentsActionPerformed
        hidepanels();
        jPanelleft.setVisible(true);
        jPanelright.setVisible(true);
        students.setVisible(true);
        jPanel4.setVisible(true);

    }//GEN-LAST:event_jButton2viewstudentsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try
        {
        String n=jTextField1.getText();
        int maxn=Integer.parseInt(jTextField2.getText());
        PreparedStatement st=con.prepareStatement("insert into course (course_ID,teacher_id,assistant_id,name,maxnum)values(?,?,?,?,?)");
        System.out.println(n);
        System.out.println(maxn);
        lastcourseID++;    
        st.setInt(1, lastcourseID);
        st.setInt(2, teacher.id);
        st.setInt(3, 0);
        st.setString(4,n);
        st.setInt(5, maxn);
         
        
        int updated= st.executeUpdate();
        updateTables();
        jTextField1.setText("");
        jTextField2.setText("");

        
        if(updated>0)
        {
            System.out.println("updated");
            start_courses_db();
        }

    }      
        catch (Exception ee)
        {
            System.out.println(ee.getMessage());
           
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        hidepanels();
        jPanelleft.setVisible(true);
        jPanelright.setVisible(true);
        jPanel2.setVisible(true);
        jScrollPane2.setVisible(true);
        jLabel8.setText("");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jLabel10.setText("");
        jPanel3.setVisible(true);
         searchcourse(search_id);
    }//GEN-LAST:event_jButton4ActionPerformed
    boolean searchcourse(int search_id)
    { try
        {
            search_id =Integer.parseInt(jTextField3.getText());
            int found =0;
            PreparedStatement stmt=con.prepareStatement("select name,assistant_id,maxnum,course_id from course where teacher_id=(?) and course_id=(?)");
            stmt.setInt(1,teacher.id); 
            stmt.setInt(2,search_id); 
            ResultSet rs=stmt.executeQuery(); 
            while(rs.next())
            {
                    found =1;
                    jPanel3.setVisible(true);
                  
                    jTextField4.setText(rs.getString("name"));
                    jTextField5.setText(rs.getString("assistant_id"));
                    jTextField6.setText(rs.getInt("maxnum")+"");
                    System.out.println(rs.getInt("course_id"));
                     
                    return true;
            }
            if(found==0)
            {
                jTextField4.setText("");
                jTextField6.setText("");
                jTextField5.setText("");
                jPanel3.setVisible(false);
                search_id=-1;
                jLabel8.setText("Not Found");
            }
        }
        catch(Exception e)
        {
           System.out.print(e.toString());
           jTextField4.setText("");
                jTextField6.setText("");
                jTextField5.setText("");
                jPanel3.setVisible(false);
                search_id=-1;
                jLabel8.setText("Not Found");
        }
        return false;
    }
    public void updateTables()
    {try{
        coursestable.setModel(new DefaultTableModel(null,new String[]{"ID","Course Name","Teacher ID","Assistant ID","Max Number"}));
        start_courses_db();
    }
    catch(Exception e)
    {}
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try{
            if(searchcourse(search_id))
            {
             
            rs.deleteRow();
            updateTables();
            jTextField4.setText("");
            jTextField6.setText("");
            jTextField5.setText("");
            jTextField3.setText("");
            jPanel3.setVisible(false);
            }
            else
            {
                System.out.println("notfound");
            }
        
       }
       catch(Exception e)
       {
           System.out.println("delete error");
           System.out.println(e.getMessage());
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jPanel5.setVisible(true);
        int search_id =Integer.parseInt(jTextField9.getText());
        int i,found =0;
        for( i=0; i<coursesList.size();i++)
        {
            if (search_id == coursesList.get(i).id)
                found=1;
        }    
            if(found==0)
            {
                jLabel7.setText("Not found");
                jPanel5.setVisible(false);
            }
            else
            {
                System.out.println("here "+search_id);
            show_students(search_id);
            jLabel7.setText("");
            }
            
            
            
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     try
        {
            PreparedStatement stmt;
            String n=jTextField4.getText();
            int mxn = Integer.parseInt(jTextField6.getText());
            int assis=0;
            try{assis = Integer.parseInt(jTextField5.getText());}
            catch (Exception e)
            {
                System.out.println("assistant null");
                //jLabel10.setText("Invalid Data");
            }
            if(search_id!=-1)
            {
            if(assis==0){
            stmt=con.prepareStatement("update course set name=?,maxnum=? where course_id=?");
            stmt.setString(1,n); 
            stmt.setInt(2,mxn);
            stmt.setInt(3,search_id);
            }
            else{
            stmt=con.prepareStatement("update course set name=?,assistant_id=?,maxnum=? where course_id=?");
            stmt.setString(1,n); 
            stmt.setInt(2,assis);
            stmt.setInt(3,mxn);
            stmt.setInt(4,search_id);
            } 
            int done=stmt.executeUpdate(); 
            if(done ==1)
                start_courses_db();
            else
                 jLabel10.setText("Invalid Data");
            
           }
        }
        catch (Exception e)
        {
            jLabel7.setText("Invalid Data");
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(teacherhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                user a = new user(55915,"mernamahmoud","mernamahmoud","mernamahmoud","teacher");
                teacherhome x =new teacherhome(a);
                x.setVisible(true);
            }
        });
    }
    public void teacherhome(user a) {
                teacherhome x =new teacherhome(a);
                x.setVisible(true);
            }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable coursestable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton2viewstudents;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonoffercourse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelleft;
    private javax.swing.JPanel jPaneloffercourse;
    private javax.swing.JPanel jPanelright;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_students;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel students;
    private javax.swing.JTable studentstable1;
    // End of variables declaration//GEN-END:variables
}
