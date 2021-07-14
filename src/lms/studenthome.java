/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toshipa
 */
public class studenthome extends javax.swing.JFrame {
    PreparedStatement stmt;
    user student=null;
    Connection con_course,con_enroll,con;
    String SQL_course,SQL_enroll,SQL;
    ResultSet rs_course,rs_enroll,rs ;
    ArrayList<course> coursesList= new ArrayList<>();
    ArrayList<enroll> enrollList= new ArrayList<>();
    ArrayList<user> usersList= new ArrayList<>();

public void start_db()
{
    
    usersList.removeAll(usersList);
    enrollList.removeAll(usersList);
    coursesList.removeAll(usersList);
    try{
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/system","root","root");  
        
                       
        stmt=con.prepareStatement("select c.course_id ,t.name ,c.assistant_id,c.name,c.maxnum "
                                                        + " from course as c ,teacher as t "
                                                        + " where t.teacher_id = c.teacher_id and c.course_id not in (select course_id from enroll where student_id=(?))");
        stmt.setInt(1,student.getId()); 
        rs_course=stmt.executeQuery();
            
        
        course u;
        while(rs_course.next())
            {
                u=new course(rs_course.getInt("c.course_id"),rs_course.getString("t.name"),rs_course.getInt("c.assistant_id"),rs_course.getString("c.name"),rs_course.getInt("c.maxnum"));
                coursesList.add(u);
            }
        
       
        stmt=con.prepareStatement("select c.course_ID ,c.name,t.name ,e.fgrade,e.mgrade "
                                                        + " from teacher as t , course as c ,enroll as e "
                                                        + " where t.teacher_id=c.teacher_id and e.student_id=(?) and e.course_id= c.course_id ");
        stmt.setInt(1,student.getId()); 
        rs_enroll=stmt.executeQuery();
            
        
        enroll e;
        while(rs_enroll.next())
            {
                e=new enroll(rs_enroll.getInt("c.course_ID"),rs_enroll.getString("c.name"),rs_enroll.getString("t.name"),rs_enroll.getInt("e.fgrade"),rs_enroll.getInt("e.mgrade"));
                enrollList.add(e);
                System.out.println(rs_enroll.getInt("course_ID"));
            }
            
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("start_courses_database");

    }

}
    
public void show_courses()
    {
        DefaultTableModel model = (DefaultTableModel)coursestable.getModel();
        Object[] row = new Object[5];
         try
        {

        for(int i=0;i<coursesList.size();i++)
        {
            row[0] = coursesList.get(i).getId();
            row[2] = coursesList.get(i).tname;
            row[3] = coursesList.get(i).getAssistantid();
            row[1] = coursesList.get(i).getCourse();
            row[4] = coursesList.get(i).getMax();
            model.addRow(row);
            
        }
        }
         catch(Exception e)
        {
           System.out.println(e.getMessage());
           System.out.println("users_courses_database");

        }
    }
    public void show_mycourses()
    {
        DefaultTableModel model = (DefaultTableModel)coursestable1.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<enrollList.size();i++)
        {       int x=enrollList.get(i).getFgrade();
                if (x==-1)
                    row[3] ="";
                else
                    row[3] =enrollList.get(i).getFgrade();
                x=enrollList.get(i).getMgrade();
                if (x==-1)
                    row[2] ="";
                else
                    row[2] =enrollList.get(i).getFgrade();
                row[1] = enrollList.get(i).tname;
                row[0] = enrollList.get(i).cname;
            System.out.println(enrollList.get(i).cname) ;   
            model.addRow(row);
        }    
            
    }
    

    public studenthome() {}
    public studenthome(user a) {
        student =new user(a);
        start_db();
        initComponents();
        hidepanel();
        show_courses();
        show_mycourses();
        
        
        jPanelleft.setVisible(true);
    }
    public void hidepanel()
    {
        jLabel1.setText("");
        jPanelright.setVisible(false);
        jPanel2.setVisible(false);
        
        jPanel4.setVisible(false);
    }
public void updateTables()
    {
        coursesList.removeAll(coursesList);
        enrollList.removeAll(enrollList);
        coursestable.setModel(new DefaultTableModel(null,new String[]{"ID","Course Name","Teacher Name","Assistant ID","Max Number"}));
        
        coursestable1.setModel(new DefaultTableModel(null,new String[]{"Course Name","Teacher Name","Midterm Grade","Final Grade"}));
        try{
        rs_course.close();
        rs_enroll.close();
        }
        catch(Exception e)
        {
            System.out.println("Errorhere");
        }
        start_db();
        show_courses();
        show_mycourses();
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanelright = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        coursestable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        coursestable1 = new javax.swing.JTable();
        jPanelleft = new javax.swing.JPanel();
        jButton2viewstudents = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(null);

        jPanel1.setOpaque(false);

        jPanelright.setOpaque(false);
        jPanelright.setPreferredSize(new java.awt.Dimension(687, 400));
        jPanelright.setLayout(new java.awt.CardLayout());

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(687, 350));

        jScrollPane2.setBorder(null);
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        coursestable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coursestable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Course Name", "Teacher Name", "Assistant ID", "Max Number"
            }
        ));
        coursestable.setFocusTraversalPolicyProvider(true);
        coursestable.setRowHeight(22);
        coursestable.setRowMargin(6);
        jScrollPane2.setViewportView(coursestable);

        jPanel3.setOpaque(false);

        jLabel4.setText("Course ID");

        jButton1.setText("Enroll");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jButton1)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelright.add(jPanel2, "card2");

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(687, 350));

        jScrollPane3.setBorder(null);
        jScrollPane3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        coursestable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coursestable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Name", "Teacher Name", "Midterml Grade", "Final Grade"
            }
        ));
        coursestable1.setFocusTraversalPolicyProvider(true);
        coursestable1.setRowHeight(22);
        coursestable1.setRowMargin(6);
        jScrollPane3.setViewportView(coursestable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jPanelright.add(jPanel4, "card2");

        jPanelleft.setOpaque(false);

        jButton2viewstudents.setText("My Courses");
        jButton2viewstudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2viewstudentsActionPerformed(evt);
            }
        });

        jButton2.setText("Available Courses");
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
                .addContainerGap()
                .addGroup(jPanelleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2viewstudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanelleftLayout.setVerticalGroup(
            jPanelleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelleftLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jButton2viewstudents)
                .addGap(120, 120, 120)
                .addComponent(jButton2)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelleft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 718, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 203, Short.MAX_VALUE)
                    .addComponent(jPanelright, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelleft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanelright, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel5.add(jPanel1);
        jPanel1.setBounds(0, 0, 900, 431);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lms/a.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(731, 500));
        jLabel2.setMinimumSize(new java.awt.Dimension(731, 500));
        jLabel2.setPreferredSize(new java.awt.Dimension(731, 500));
        jPanel5.add(jLabel2);
        jLabel2.setBounds(0, -45, 970, 520);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2viewstudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2viewstudentsActionPerformed
        hidepanel();
        jPanelright.setVisible(true);
        jPanel4.setVisible(true);
    }//GEN-LAST:event_jButton2viewstudentsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        hidepanel();
        jPanelright.setVisible(true);
        jPanel2.setVisible(true);
        
        
                

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
          jLabel1.setText("");  
        int coid=Integer.parseInt(jTextField1.getText());
        
        stmt=con.prepareStatement("insert into enroll (course_ID,student_id,fgrade,mgrade)values(?,?,?,?)");
        System.out.println(coid);  
        stmt.setInt(1, coid);
        stmt.setInt(2, student.getId());
        stmt.setInt(3, -1);
        stmt.setInt(4,-1);
        int updated= stmt.executeUpdate();
        jTextField1.setText("");
        start_db();

        
        if(updated>0)
        {
            System.out.println("updated");
            
        }
        updateTables();
    }      
        catch (Exception ee)
        {
            jLabel1.setText("Error");
            System.out.println(ee.getMessage());
           
            
        }    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(studenthome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(studenthome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(studenthome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studenthome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 user a = new user(3,"student","student","student","student");
                studenthome x =new studenthome(a);
                x.setVisible(true);
            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable coursestable;
    private javax.swing.JTable coursestable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton2viewstudents;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelleft;
    private javax.swing.JPanel jPanelright;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
