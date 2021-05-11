package com.contact.model;

public class Contact {
    private String phone,group,name,gender,address,dob,email;

    public Contact(String phone, String group, String name, String gender, String address, String dob, String email) {
        this.phone = phone;
        this.group = group;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void displayContact(){
        System.out.printf("| %10s | %10s | %20s | %10s | %15s | \n",this.phone,this.group,this.name,this.gender,this.address);
    }
    public String toStringCsv(){
        return this.phone+","+this.group+","+this.name+","+this.gender+","+this.address+","+this.dob+","+this.email+"\n";
    }
}
