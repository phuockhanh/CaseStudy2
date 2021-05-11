package com.contact.presentation;
import com.contact.model.Contact;
import com.contact.services.ContactManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static ContactManager ctm = new ContactManager();
    static Scanner sc = new Scanner(System.in);
    public static void createMenu(){
        System.out.println("-----DIRECTORY MANAGEMENT PROGRAM-----\n"+
                "1. See list contact \n"+
                "2. Add new contact \n"+
                "3. Update contact \n"+
                "4. Delete contact \n"+
                "5. Sort list by Name or Group\n"+
                "6. Search Phone/Name from list \n"+
                "7. Read File \n"+
                "8. Save File \n"+
                "9. Exit \n"+
                "Select Function: "
                );
    }
    public static void add(){
        String phone;
        while (true){
            System.out.println("Input Phone to Add: ");
            phone = sc.nextLine();
            if(ctm.checkPhone(phone)){
                break;
            }
        }
        System.out.println("Input Group to Add: ");
        String group = sc.nextLine();
        String name;
        while (true){
            System.out.println("Input Name to Add: ");
            name = sc.nextLine();
               if(ctm.checkName(name)){
                    break;
                }
        }
        String gender;
        while (true){
            System.out.println("Input Gender to Add: ");
            gender = sc.nextLine();
            if(ctm.checkGender(gender)){
                break;
            }
        }
        System.out.println("Input Address to Add: ");
        String address = sc.nextLine();
        String dob;
        while (true){
            System.out.println("Input Date of Birth to Add: ");
            dob = sc.nextLine();
            if(ctm.checkDate(dob)){
                break;
            }
        }
        String email;
        while (true){
            System.out.println("Input Email to Add: ");
            email = sc.nextLine();
            if(ctm.checkEmail(email)){
                break;
            }
        }
        Contact ct = new Contact(phone,group,name,gender,address,dob,email);
        ctm.add(ct);
    }
    public static void edit(){
        System.out.println("Input Phone to Edit: ");
        while (true){
            String phone = sc.nextLine();
            if(ctm.edit(phone)){
                break;
            }
            String y = sc.nextLine();
            if (y.equals("")) {
                break;
            }
        }
    }
    public static void remove(){
        System.out.println("Input Phone to Remove: ");
        String phone = sc.nextLine();
        if(!ctm.remove(phone)){
            System.out.println("Not Found");
        }
        else{
            System.out.println("Remove Phone : "+phone+ " to Complete !!");
        }
    }
    public static void main(String[] args) {
        while (true){
            createMenu();
            while (!sc.hasNextInt()){
                System.out.println("Error format, please enter again !!");
                createMenu();
                sc.nextLine();
            }
            int x = sc.nextInt();
            sc.nextLine();
            outloop:
            switch (x){
                case 1:{
                    ctm.display();
                    break;
                }
                case 2:{
                    add();
                    break;
                }
                case 3:{
                    edit();
                    break;
                }
                case 4:{
                    remove();
                    break;
                }
                case 5:{
                    System.out.println("1. Sort list by Name\n"+
                                       "2. Sort list by Group\n"+
                                       "Enter number : ");
                    while (!sc.hasNextInt()){
                        break outloop;
                    }
                    int y = sc.nextInt();
                    ctm.sort(y);
                    break;
                }
                case 6:{
                    System.out.println("Input Phone/Name to Search: ");
                    String phone = sc.nextLine();
                    System.out.println("The System is Searching, please wait !!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ctm.search(phone);
                    break;
                }
                case 7:{
                    System.out.println("Warning !! , File to Delete All contact !!, Y/N delete ? ");
                    String y = sc.nextLine();
                    if(y.equals("Y")||y.equals("y")){
                        try {
                            ctm.readFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 8:{
                    System.out.println("Warning !! , File to Delete All contact !!, Y/N delete ? ");
                    String y = sc.nextLine();
                    if(y.equals("Y")){
                        try {
                            ctm.saveFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 9:{
                    System.out.println("Exit");
                    System.exit(0);
                }
                default:{
                    System.out.println("Error formating, continue ?? Y/N");
                    String y = sc.nextLine();
                    if(y.equals("Y") || y.equals("y")){
                        createMenu();
                    }
                    else {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
