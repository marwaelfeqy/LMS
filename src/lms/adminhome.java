/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toshipa
 */
public class adminhome extends javax.swing.JFrame {

    String host;
    Connection con;
    String SQL;
    Statement stmt;
    ResultSet rs ;
    ArrayList<user> teachersList= new ArrayList<>();
    ArrayList<user> studentsList= new ArrayList<>();
    ArrayList<course> coursesList= new ArrayList<>();
    int laststudentID,lastteacherID,lastcourseID;
    
    
    public void courses_List()
    {
        course c;
        try{
        coursesList.removeAll(coursesList);
            user u;
            PreparedStatement stmt=con.prepareStatement("select * from course"); 
            ResultSet rs=stmt.executeQuery();        
            while(rs.next())
            {
                lastcourseID = rs.getInt("course_id");
                c=new course(rs.getInt("course_id"),rs.getInt("teacher_id"),rs.getInt("assistant_id"),rs.getString("name"),rs.getInt("maxnum"));
                coursesList.add(c);
            }
            System.out.println("the last updated id is "+lastcourseID);
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
           System.out.print("coursesList error");

        
        }}
    
    public void startdb()
    {
         try{
             con =DriverManager.getConnection("jdbc:mysql://localhost:3306/system","root","root"); 
        }
                       
        catch(Exception e)
        {
           System.out.print(e.getMessage());
        }

    }
    public void users_List()
    {
        
        
        try
        {
        startdb();
        teachersList.removeAll(teachersList);
            user u;
            PreparedStatement stmt=con.prepareStatement("select * from teacher"); 
            ResultSet rs=stmt.executeQuery();        
            while(rs.next())
            {
                lastteacherID = rs.getInt("teacher_id");
                u=new user(rs.getInt("teacher_id"),rs.getString("name"),rs.getString("email"),"teacher","");
                teachersList.add(u);
            }
            System.out.println("the last updated id is "+lastteacherID);
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
           System.out.print("teacherlist error");

        }
        try
        {
        studentsList.removeAll(studentsList);
            user u;
            PreparedStatement stmt=con.prepareStatement("select * from student"); 
            ResultSet rs=stmt.executeQuery();        
            while(rs.next())
            {
                laststudentID = rs.getInt("student_id");
                u=new user(rs.getInt("student_id"),rs.getString("name"),rs.getString("email"),"student","");
                studentsList.add(u);
            }
            System.out.println("the last updated id is "+laststudentID);
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
           System.out.print("teacherlist error");

        }
        
    }
    
    
    public void show_user()
    {
        DefaultTableModel model = (DefaultTableModel)users_table.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<teachersList.size();i++)
        {
            row[0] = teachersList.get(i).getId();
            row[1] = teachersList.get(i).getName();
            row[2] = teachersList.get(i).getEmail();
            row[3] = "teacher";
            model.addRow(row);
        }
        for(int i=0;i<studentsList.size();i++)
        {
            row[0] = studentsList.get(i).getId();
            row[1] = studentsList.get(i).getName();
            row[2] = studentsList.get(i).getEmail();
            row[3] = "students";
            model.addRow(row);
        }
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
     public boolean show_students()     
    {
        DefaultTableModel model = (DefaultTableModel)users_table1.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<studentsList.size();i++)
        {   try{
               if(studentsList.get(i).getType().equals("student"))
                {
                    row[0] = studentsList.get(i).getId();
                    row[1] = studentsList.get(i).getName();
                    row[2] = studentsList.get(i).getEmail();
                    row[3] = "student";
                    model.addRow(row);
                }
            }
               catch (Exception e)
            {
                System.out.println(e.getMessage());
                return false;
            }


        }
        return true;

    }
    public void show_teacher()
    {
        
        DefaultTableModel model = (DefaultTableModel)users_table2.getModel();
        Object[] row = new Object[4];
        
        for(int i=0;i<teachersList.size();i++)
        {   try{
               if(teachersList.get(i).getType().equals("teacher"))
                {
                    row[0] = teachersList.get(i).getId();
                    row[1] = teachersList.get(i).getName();
                    row[2] = teachersList.get(i).getEmail();
                    row[3] = "teacher";
                    model.addRow(row);
                }
            }
               catch (Exception e)
            {
                System.out.println(e.getMessage());
            }


        }
        
    }
    public void  show_assistant()
    {
        /*
        DefaultTableModel model = (DefaultTableModel)users_table3.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<usersList.size();i++)
        {
            try{
               if(usersList.get(i).getType().equals("assistant"))
                {
                    row[0] = usersList.get(i).getId();
                    row[1] = usersList.get(i).getName();
                    row[2] = usersList.get(i).getEmail();
                    row[3] = usersList.get(i).getType();
                    model.addRow(row);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }


        }
   */ 
    }
    
public void updateTables()
    {
        teachersList.removeAll(teachersList);
        studentsList.removeAll(studentsList);
        users_table.setModel(new DefaultTableModel(null,new String[]{"ID","Name","Email","Type"}));
        users_table1.setModel(new DefaultTableModel(null,new String[]{"ID","Name","Email","Type"}));
        users_table2.setModel(new DefaultTableModel(null,new String[]{"ID","Name","Email","Type"}));
        users_table3.setModel(new DefaultTableModel(null,new String[]{"ID","Name","Email","Type"}));
        coursestable.setModel(new DefaultTableModel(null,new String[]{"ID","Course Name","Teacher ID","Assistant ID","Max Number"}));
        
        
        users_List();
        courses_List();
        show_courses();
        show_user();
        show_students();
        show_teacher();
        show_assistant();
    
    }

public void hide_table()
    {
        jTextField1.setText("");
        foundl.setText("");
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel13.setVisible(false);
        jPanel7.setVisible(false);
        jPanel_newacc.setVisible(false);
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jLabel11.setText("");
        jLabel6.setText("");
    }    
   

     
public adminhome() {

    initComponents();
    startdb();
    updateTables();
    jPanel12.setVisible(true);
    jPanel6.setVisible(false);
    jPanel10.setVisible(false);
    jPanel8.setVisible(false);
    jRadioButton1.setSelected(true);
    hide_table();
    
   customize();

}
public void customize()
{
    users_table.setOpaque(false);
    ((DefaultTableCellRenderer)users_table.getDefaultRenderer(Object.class)).setOpaque(false);
    jScrollPane2.setOpaque(false);
    jScrollPane2.getViewport().setOpaque(false);
    users_table.setShowGrid(false); 
    
    users_table1.setShowGrid(false);
    users_table1.setOpaque(false);
    ((DefaultTableCellRenderer)users_table1.getDefaultRenderer(Object.class)).setOpaque(false);
    jScrollPane3.setOpaque(false);
    jScrollPane3.getViewport().setOpaque(false);
    users_table1.setShowGrid(false); 
    
    users_table2.setOpaque(false);
    ((DefaultTableCellRenderer)users_table2.getDefaultRenderer(Object.class)).setOpaque(false);
    jScrollPane4.setOpaque(false);
    jScrollPane4.getViewport().setOpaque(false);
    users_table2.setShowGrid(false); 
    
    users_table3.setOpaque(false);
    ((DefaultTableCellRenderer)users_table3.getDefaultRenderer(Object.class)).setOpaque(false);
    jScrollPane5.setOpaque(false);
    jScrollPane5.getViewport().setOpaque(false);
    users_table3.setShowGrid(false); 
    
    coursestable.setOpaque(false);
    ((DefaultTableCellRenderer)coursestable.getDefaultRenderer(Object.class)).setOpaque(false);
    jScrollPane6.setOpaque(false);
    jScrollPane6.getViewport().setOpaque(false);
    coursestable.setShowGrid(false); 
}
    

@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel12 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        users_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        users_table1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        users_table2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        users_table3 = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        coursestable = new javax.swing.JTable();
        jPanel_newacc = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        foundl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Agency FB", 1, 12)); // NOI18N

        jPanel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel12.setLayout(null);

        jPanel5.setOpaque(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Users");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("New Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jButton1)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel5);
        jPanel5.setBounds(0, 0, 175, 431);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.CardLayout());

        jPanel1.setOpaque(false);

        jScrollPane2.setBorder(null);
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane2.setOpaque(false);

        users_table.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        users_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Type"
            }
        ));
        users_table.setFocusTraversalPolicyProvider(true);
        users_table.setOpaque(false);
        users_table.setRowHeight(22);
        users_table.setRowMargin(6);
        jScrollPane2.setViewportView(users_table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel1, "card2");

        jPanel2.setOpaque(false);

        jScrollPane3.setBorder(null);
        jScrollPane3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane3.setOpaque(false);

        users_table1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        users_table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Type"
            }
        ));
        users_table1.setOpaque(false);
        users_table1.setRowHeight(22);
        users_table1.setRowMargin(6);
        jScrollPane3.setViewportView(users_table1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel2, "card3");

        jPanel3.setOpaque(false);

        jScrollPane4.setBorder(null);
        jScrollPane4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane4.setOpaque(false);

        users_table2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        users_table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Type"
            }
        ));
        users_table2.setOpaque(false);
        users_table2.setRowHeight(22);
        users_table2.setRowMargin(6);
        jScrollPane4.setViewportView(users_table2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel6.add(jPanel3, "card4");

        jPanel4.setOpaque(false);

        jScrollPane5.setBorder(null);
        jScrollPane5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane5.setOpaque(false);

        users_table3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        users_table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Type"
            }
        ));
        users_table3.setOpaque(false);
        users_table3.setRowHeight(22);
        users_table3.setRowMargin(6);
        jScrollPane5.setViewportView(users_table3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel4, "card5");

        jPanel13.setOpaque(false);

        jScrollPane6.setBorder(null);
        jScrollPane6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane6.setOpaque(false);

        coursestable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coursestable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Type"
            }
        ));
        coursestable.setOpaque(false);
        coursestable.setRowHeight(22);
        coursestable.setRowMargin(6);
        jScrollPane6.setViewportView(coursestable);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel13, "card5");

        jPanel_newacc.setOpaque(false);

        jPanel11.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Name");

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Email");

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Type");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton1.setText("Student");
        jRadioButton1.setOpaque(false);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton2.setText("Assistant");
        jRadioButton2.setOpaque(false);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton3.setText("Teacher");
        jRadioButton3.setOpaque(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Password");

        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 255, 0));
        jLabel11.setText(" ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField8)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(38, 38, 38)
                                .addComponent(jRadioButton1)
                                .addGap(20, 20, 20)
                                .addComponent(jRadioButton3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(jRadioButton2))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(41, 41, 41)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jLabel11))
        );

        javax.swing.GroupLayout jPanel_newaccLayout = new javax.swing.GroupLayout(jPanel_newacc);
        jPanel_newacc.setLayout(jPanel_newaccLayout);
        jPanel_newaccLayout.setHorizontalGroup(
            jPanel_newaccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(jPanel_newaccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_newaccLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel_newaccLayout.setVerticalGroup(
            jPanel_newaccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
            .addGroup(jPanel_newaccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_newaccLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel_newacc, "card2");

        jPanel7.setOpaque(false);

        jPanel8.setOpaque(false);

        jLabel2.setText("ID");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Name");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Email");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Type");

        jPanel10.setOpaque(false);

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(48, 48, 48))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(51, 51, 51)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setOpaque(false);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Enter User ID");

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        foundl.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(foundl, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(30, 30, 30))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(foundl, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, "card6");

        jPanel12.add(jPanel6);
        jPanel6.setBounds(175, 0, 540, 381);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lms/adm.jpg"))); // NOI18N
        jPanel12.add(jLabel12);
        jLabel12.setBounds(0, 0, 730, 420);

        jMenu1.setText("View");

        jMenuItem1.setText("Users");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Student");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Teachers");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Assistants");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Courses");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        hide_table();
        jPanel1.setVisible(true);
        jPanel6.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        hide_table();
        jPanel2.setVisible(true);
         jPanel6.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        hide_table();
        jPanel3.setVisible(true);
        jPanel6.setVisible(true);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        hide_table();
        jPanel4.setVisible(true);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        foundl.setText("");
        hide_table();
        jPanel8.setVisible(false);
        jPanel7.setVisible(true);
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       try
       {
            int id = Integer.parseInt(jTextField2.getText());
            String n=jTextField3.getText();
            String e=jTextField4.getText();
            String t=jTextField5.getText();
            foundl.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jPanel8.setVisible(false);
            PreparedStatement stmt=null;
            System.out.println(t);
            if(t.equals("student"))
                stmt=con.prepareStatement("update student set name=? ,email=? where student_id=(?)");
            if(t.equals("teacher"))
                stmt=con.prepareStatement("update teacher set name=? ,email=? where teacher_id=(?)");
            stmt.setString(1,n);//1 specifies the first parameter in the query i.e. name  
            stmt.setString(2,e);
            stmt.setInt(3,id);  

            int i=stmt.executeUpdate();  
            System.out.println(i+" records updated");
            foundl.setText("");
        updateTables();
                      
       }
       catch(Exception e)
        {
            
           System.out.println(e.getMessage());
           System.out.println("error here");

        }
       
    }//GEN-LAST:event_jButton4ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try
        {
            int search_id =Integer.parseInt(jTextField1.getText());
            int found =0;
            String t="";
            PreparedStatement stmt=con.prepareStatement("select * from student where student_id=?");
            stmt.setInt(1,search_id); 
            ResultSet rs=stmt.executeQuery();  
            if(rs.next())
            {
                found=1;
                t="student";
            }
            else{
                stmt=con.prepareStatement("select * from teacher where teacher_id=?");
                stmt.setInt(1,search_id); 
                rs=stmt.executeQuery();  
                if(rs.next())
                {
                    found=1;
                    t="teacher";
                }
            
            }
            System.out.println(search_id);
            
            if(found==1){
                    jTextField5.setEditable(false);
                    jTextField2.setEditable(false);
                    jPanel8.setVisible(true);
                    foundl.setText("Found");
                    foundl.setForeground(Color.green);
                    if(t.equals("teacher"))
                    jTextField2.setText(rs.getInt("teacher_id")+"");
                    if(t.equals("student"))
                    jTextField2.setText(rs.getInt("student_id")+"");
                    jTextField3.setText(rs.getString("name"));
                    jTextField4.setText(rs.getString("email"));
                    jTextField5.setText(t);
                    
                    jPanel10.setVisible(true);
                                        
            }
            if(found==0)
            {
                foundl.setText("Not Found");
                foundl.setForeground(Color.red);
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jPanel10.setVisible(false);

            }
        }
        catch(Exception e)
        {
            
           System.out.print(e.getMessage());

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     try
       {
        
            PreparedStatement stmt=null;
            String t=jTextField5.getText();
            int id=Integer.parseInt(jTextField2.getText());
            if(t.equals("student"))
                stmt=con.prepareStatement("delete from student where student_id=?");  
            if(t.equals("teacher"))
                stmt=con.prepareStatement("delete from teacher where teacher_id=?");  
        stmt.setInt(1,id); 
        int i=stmt.executeUpdate();  
        System.out.println(i+" records deleted");
        jButton1.doClick();
        foundl.setText("Deleted");
        foundl.setForeground(Color.red);
        
        updateTables();
       }
       catch(SQLException e)
        {
            
           System.out.println(e.getMessage());

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        hide_table();
        jPanel6.setVisible(true);
        jPanel_newacc.setVisible(true);
        jLabel6.setText("");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String n,e,p,t="";
        jLabel6.setText("");
        jLabel11.setText("");
        try{
            

            n=jTextField7.getText();
            e=jTextField8.getText();
            p=jTextField9.getText();
            if(jRadioButton1.isSelected())
            t="student";
            if(jRadioButton3.isSelected())
            t="teacher";
            if(jRadioButton2.isSelected())
            t="assistant";
            
            if(n.length()>1 && e.length()>1 && !emailExists(e)&& p.length()>1&&validEmail(e))
            {   int found=0;
                if(t.equals("teacher"))
                {
                    lastteacherID++;
                    System.out.println("new id= "+lastteacherID);
                    PreparedStatement ps=con.prepareStatement("INSERT INTO teacher (teacher_ID,Name,Email,password)values(?,?,?,?)");
                    ps.setInt(1, lastteacherID);
                    ps.setString(2,n);
                    ps.setString(3,e);
                    ps.setString(4,p);
                    int updated= ps.executeUpdate();
                    if(updated>0)
                    {
                        updateTables();
                        System.out.println("updated");
                        jTextField7.setText("");
                        jTextField8.setText("");
                        jTextField9.setText("");
                        jLabel11.setText("Saved");
                    }

                }
                else if(t.equals("student"))
                {System.out.println("here ");               
                    laststudentID++;
                    System.out.println("new id= "+laststudentID);
                    PreparedStatement ps=con.prepareStatement("INSERT INTO student (student_ID,Name,Email,password)values(?,?,?,?)");
                    ps.setInt(1, laststudentID);
                    ps.setString(2,n);
                    ps.setString(3,e);
                    ps.setString(4,p);
                    int updated= ps.executeUpdate();
                    if(updated>0)
                    {
                        updateTables();
                        System.out.println("updated");
                        jTextField7.setText("");
                        jTextField8.setText("");
                        jTextField9.setText("");
                        jLabel11.setText("Saved");
                    }

                }
            }
            else
            {
                jLabel6.setText("Invalid Data");
            }
        }
        catch (Exception ee)
        {
            System.out.println(ee.getMessage());
            System.out.println("new account error");
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        hide_table();
        jPanel13.setVisible(true);
         jPanel6.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                user a = new user(1,"admin","root","root","");
                adminhome x =new adminhome();
                x.setVisible(true);
            }
        });
    }
    public boolean validEmail(String e){
        return (e.contains("@gmail.com") && e.length()>9);
    }
    public boolean emailExists(String e){
         try{
            boolean found=false;
            PreparedStatement stmt=con.prepareStatement("select * from teacher WHERE email=?");
            stmt.setString(1,e);  
            ResultSet rs=stmt.executeQuery();   
            while(rs.next()){  
              found =true;  
            }
            stmt=con.prepareStatement("select * from student WHERE email=?");
            stmt.setString(1,e);  
            rs=stmt.executeQuery();   

            while(rs.next()){  
              found =true;  
            }
         if (e.equals(""))
         {
            found=true;
         }
         return found;
         }
         catch(Exception ex){System.out.println(ex.toString());return true;}
        
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTable coursestable;
    private javax.swing.JLabel foundl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_newacc;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable users_table;
    private javax.swing.JTable users_table1;
    private javax.swing.JTable users_table2;
    private javax.swing.JTable users_table3;
    // End of variables declaration//GEN-END:variables
}
