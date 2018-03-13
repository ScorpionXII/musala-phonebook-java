package com.musala.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public class PhoneBook {

    // Defines Phone Numbers format for this PhoneBook
    private String phoneFormat = "^((0){1,2}|(\\+))(359){1}((87)|(88)|(89)){1}[2-9]{1}[0-9]{6}$";

    private TreeMap<String, String> store;

    public PhoneBook() {
        this.store = new TreeMap<String, String>();
    }

    public void load(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String pairLine = null;

        while ((pairLine = reader.readLine())!=null) {
            String strArr[] = pairLine.split(",");
            if (validatePhone(strArr[1])) {
                this.store.put(strArr[0], formatPhone(strArr[1]));
            }
            else
                System.out.println("An Invalid Phone Number had been skipped!");
        }
    }

    public void addEntry(String name, String phone) {
        if (validatePhone(phone))
            this.store.put(name, formatPhone(phone));
        else
            System.out.println("You entered wrong format Phone!");
    }

    public void removeEntry(String name) {
        if (store.containsKey(name)) {
            store.remove(name);
        }
        else
            System.out.println("Entry not found!");
    }

    public String getPhone(String name) {
        return store.containsKey(name)?store.get(name):null;
    }

    public TreeMap<String, String> getStored() {
        return store;
    }

    public String formatPhone(String phone) {
        if (validatePhone(phone))
            if (phone.charAt(0)!='+')
                return (phone.charAt(1) == '0')?"+" + phone.substring(2):"+" + phone.substring(1);
            else
                return phone;

        return null;
    }

    public boolean validatePhone(String phone) {
        return phone.matches(phoneFormat);
    }
}
