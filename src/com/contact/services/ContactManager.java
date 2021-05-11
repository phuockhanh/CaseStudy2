package com.contact.services;

import com.contact.contactDB.ContactDatabase;
import com.contact.model.Contact;
import java.io.*;

public class ContactManager {
    private ContactDatabase ctb = new ContactDatabase();
    public void add(Contact ct){
        ctb.add(ct);
    }
    public boolean remove(String phone){
        return ctb.remove(phone);
    }
    public boolean edit(String phone){
        return ctb.edit(phone);
    }
    public void search(String phone){
        ctb.search(phone);
    }
    public void sort(int x){
        ctb.sort(x);
    }
    public void display(){
        ctb.display();
    }
    public void saveFile() throws IOException{
        ctb.saveFile();
    }
    public void readFile() throws IOException{
        ctb.readFile();
    }
    public boolean checkPhone(String phone){
        return ctb.checkPhone(phone);
    }
    public boolean checkEmail(String email){
        return ctb.checkEmail(email);
    }
    public boolean checkDate(String date){
        return ctb.checkDate(date);
    }
    public boolean checkGender(String gender){
        return ctb.checkGender(gender);
    }
    public boolean checkName(String name){
        return ctb.checkName(name);
    }
}
