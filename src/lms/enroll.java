/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

/**
 *
 * @author toshipa
 */
public class enroll {
    String cname="",tname="";
int studentid,courseid,fgrade,mgrade,tid;
enroll(int courseid, int studentid,int fgrade,int mgrade)
{   this.courseid=courseid;
    this.studentid=studentid;
    this.fgrade=fgrade;
    this.mgrade=mgrade;
}
enroll(int courseid, String cname,String tname,int fgrade,int mgrade)
{   this.courseid=courseid;
    this.cname=cname;
    this.tname=tname;
    this.fgrade=fgrade;
    this.mgrade=mgrade;
}

    public int getStudentid() {
        return studentid;
    }

    public int getCourseid() {
        return courseid;
    }

    public int getFgrade() {
        return fgrade;
    }

    public int getMgrade() {
        return mgrade;
    }

}
