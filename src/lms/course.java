
package lms;

public class course {
    String tname="",aname="";
int teacherid,assistantid,id,max;
String course;
course(int i,int ti,int ai,String c,int max)
{
    this.course=c;
    this.teacherid=ti;
    this.assistantid=ai;
    this.id=i;
    this.max=max;
    
}
course(int i,String ti,int ai,String c,int max)
{
    this.course=c;
    this.tname=ti;
    this.assistantid=ai;
    this.id=i;
    this.max=max;
    
}

    public int getTeacherid() {
        return teacherid;
    }

    public int getAssistantid() {
        return assistantid;
    }

    public int getId() {
        return id;
    }

    public int getMax() {
        return max;
    }

    public String getCourse() {
        return course;
    }
}
