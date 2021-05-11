package com.contact.contactDB;

import com.contact.model.Contact;

import java.io.*;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactDatabase {
    private static Scanner sc = new Scanner(System.in);
    List<Contact> list = new ArrayList<>();
    public void add(Contact ct){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getPhone().equals(ct.getPhone())){
                System.out.println("Error to Add, please check phone");
            }
        }
        ct.setName(formatString(ct.getName()));
        ct.setGroup(formatString(ct.getGroup()));
        ct.setGender(formatString(ct.getGender()));
        ct.setAddress(formatString(ct.getAddress()));
        list.add(ct);
    }
    public void addFileCsv(Contact ct){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getPhone().equals(ct.getPhone())){
                return;
            }
        }
        list.add(ct);
    }
    public boolean remove(String phone){
        if(list.isEmpty()){
            System.out.println("Error!!");
            return false;
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getPhone().equals(phone)){
                list.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean edit(String phone){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getPhone().equals(phone)){
                System.out.println("Input Group to Edit: ");
                String group = sc.nextLine();
                String name;
                while (true){
                    System.out.println("Input Name to Edit: ");
                    name = sc.nextLine();
                    if(checkName(name)){
                        break;
                    }
                }
                String gender;
                while (true) {
                    System.out.println("Input Gender to Edit: ");
                     gender = sc.nextLine();
                     if(checkGender(gender)){
                         break;
                     }
                }
                System.out.println("Input Address to Edit: ");
                String address = sc.nextLine();
                String dob;
                while (true) {
                    System.out.println("Input Date of Birth to Edit: ");
                    dob = sc.nextLine();
                    if (checkDate(dob)) {
                        break;
                    }
                }
                String email;
                while (true){
                    System.out.println("Input Email to Add: ");
                    email = sc.nextLine();
                    if(checkEmail(email)){
                        break;
                    }
                }
                list.get(i).setName(formatString(list.get(i).getName()));
                list.get(i).setGroup(formatString(list.get(i).getGroup()));
                list.get(i).setGender(formatString(list.get(i).getGender()));
                list.get(i).setAddress(formatString(list.get(i).getAddress()));
                list.get(i).setDob(dob);
                list.get(i).setEmail(email);
                System.out.println("Edit Phone: "+phone+" Complete");
                System.out.println(list.get(i).toString());
                return true;
            }
        }
        System.out.println("Invalid Phone, please enter again!!");
        return false;
    }
    public boolean checkEmail(String email){
        String regex = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        return Pattern.matches(regex,email);
    }
    public boolean checkPhone(String phone){
        String regex = "(84|0[3|5|7|8|9])+([0-9]{8})";
        return Pattern.matches(regex,phone);
    }
    public boolean checkName(String name){
        String VIETNAMESE_DIACRITIC_CHARACTERS = "ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";
        Pattern p = Pattern.compile("(?:[" + VIETNAMESE_DIACRITIC_CHARACTERS + "]|[\\p{L} .'-])+$",
                Pattern.CASE_INSENSITIVE |
                        Pattern.UNICODE_CASE);
        Matcher matcher = p.matcher(name);
        return matcher.matches();
    }
    public boolean checkGender(String gender){
        if(koDau(gender).toLowerCase().equals("nam") || koDau(gender).toLowerCase().equals("nu") ||
                gender.toLowerCase().equals("female") || gender.toLowerCase().equals("male")){
            return true;
        }
        return false;
    }
    public boolean checkDate(String date){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public String formatString(String name){
        char[] c = name.trim().replaceAll(" +", " ").toLowerCase().toCharArray();
        for(int i=0;i<c.length;i++){
            c[0] = Character.toUpperCase(c[0]);
            if((int)c[i]==32){
                c[i+1] = Character.toUpperCase(c[i+1]);
            }
        }
        return new String(c);
    }
    public String koDau(String name){
        String kodau = Normalizer.normalize(name,Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(kodau).replaceAll("").toLowerCase().replaceAll("đ", "d");
    }
    public boolean soSanh(String s1, String s2, int saiso){
        int i,j,k,loi;
        if(s1.length()>s2.length()+saiso || s1.length()<s2.length()-saiso){
            return false;
        }
        else{
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            i=j=loi=0;
            while (i<s1.length() && j<s2.length()){
                if(c1[i]!=c2[j]){
                    loi++;
                    for(k=1;k<saiso;k++){
                        if((i+k)<c1.length && c1[i+k]==c2[j]){
                            i+=k;
                            break;
                        }
                        else if((j+k)<c2.length && c2[j+k]==c1[i]){
                            j+=k;
                            break;
                        }
                    }
                }
                i++;
                j++;
            }
            if(loi<=saiso){
                return true;
            }
            return false;
        }
    }
    public void search(String phone){
        int saiso =(int) Math.round(phone.length()*0.3);
        int count=0;
        for(int i=0;i<list.size();i++){
            if(soSanh(list.get(i).getPhone(),phone,saiso)||soSanh(koDau(list.get(i).getName().toLowerCase()),koDau(phone.toLowerCase()),saiso)){
                list.get(i).displayContact();
                count++;
            }
        }
        if(count==0){
            System.out.println("Not Found !!");
        }
    }
    public void sort(int y){
        if(y==1){
            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    String[] str = o1.getName().split(" ");
                    String[] str2 = o2.getName().split(" ");
                    return koDau(str[str.length-1]).compareTo(koDau(str2[str2.length-1]));
                }
            });
        }
        if(y==2){
            Collections.sort(list, new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    return o1.getGroup().compareTo(o2.getGroup());
                }
            });
        }
    }
    public void saveFile() throws IOException {
        File fl = new File("contact.csv");
        if(!fl.exists()){
            fl.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream("contact.csv");
        byte[] b;
        for(int i=0;i<list.size();i++){
            b = list.get(i).toStringCsv().getBytes();
            fos.write(b);
        }
        fos.flush();
    }
    public void readFile() throws IOException{
        File fl = new File("contact.csv");
        if(!fl.exists()){
            fl.createNewFile();
        }
        FileReader fr = new FileReader("contact.csv");
        BufferedReader buf = new BufferedReader(fr);
        String line;
        while ((line=buf.readLine())!=null){
            String[] str = line.split(",");
            Contact ct = new Contact(str[0],str[1],str[2],str[3],str[4],str[5],str[6]);
            addFileCsv(ct);
        }
        buf.close();
        fr.close();
    }
    public void display(){
        if(list.size()==0){
            System.out.println("No information!!");
            return;
        }
        int start =0;
        int x;
        String y="";
        while (y==""){
            if(list.size()-start>5){
                x = 5;
            }
            else{
                x = list.size()-start;
            }
            for(int i=start;i<start+x;i++){
                if(i==0){
                    System.out.println("________________________________________________________________________________");
                    System.out.printf("| %10s | %10s | %20s | %10s | %15s | \n","Phone","Group","Name","Gender","Address");
                }
                list.get(i).displayContact();
                if(i==list.size()-1){
                    System.out.println("________________________________________________________________________________");
                }
            }
            if(x<5 || start+x==list.size()){
                break;
            }
            start+=x;
            System.out.println("Press enter key to continue");
            y = sc.nextLine();
        }
    }
}
