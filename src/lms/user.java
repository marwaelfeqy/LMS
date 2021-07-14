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
public class user {
    int id;
String name,type,email, password;
user(int id, String name,String email,String type,  String password)
{   this.name=name;
    this.email=email;
    this.id=id;
    this.password=password;
    this.type=type;
    
}
user(user a)
{
    this.name=a.name;
    this.email=a.email;
    this.id=a.id;
    this.password=a.password;
    this.type=a.type;
}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public boolean equals(user a) {
        if (a == null)
            return false;
        if (this.id != a.id || !this.email.equals(a.email)|| !this.name.equals(a.name)||!this.password.equals(a.password)||!this.type.equals(a.type))
            return false;
        return true;
    }
    
}
