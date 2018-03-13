package com.musala.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    // Defines Number Format for this PhoneBook
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
            if (validateNumber(strArr[1])) {
                this.store.put(strArr[0], formatNumber(strArr[1]));
            }
            else
                System.out.println("An Invalid Phone Number had been skipped");
        }
    }

    public void addEntry(String name, String phone) {
        this.store.put(name, phone);
    }

    public void removeEntry(String name) {
        if (store.containsKey(name)) {
            store.remove(name);
        }
    }

    public String getNumber(String name) {
        return store.containsKey(name)?store.get(name):null;
    }

    public TreeMap<String, String> getStored() {
        return store;
    }

    public String formatNumber(String number) {
        if (validateNumber(number))
            if (number.charAt(0)!='+')
                return (number.charAt(1) == '0')?"+" + number.substring(2):"+" + number.substring(1);
            else
                return number;

        return null;
    }

    public boolean validateNumber(String number) {
        return number.matches(phoneFormat);
    }
}
