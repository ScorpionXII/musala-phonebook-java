package com.musala.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class PhoneBook {
    // Defines Phone Numbers format for this PhoneBook
    private String phoneFormat = "^((0){1,2}|(\\+))(359){1}((87)|(88)|(89)){1}[2-9]{1}[0-9]{6}$";

    private ArrayList<PhoneBookEntry> store;

    private PriorityQueue<PhoneBookEntry> top5;

    public PhoneBook() {
        this.store = new ArrayList<PhoneBookEntry>();
        this.top5 = new PriorityQueue<>(5, (PhoneBookEntry entryA, PhoneBookEntry entryB) -> entryB.getCalls() - entryA.getCalls());
    }

    public void load(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String pairLine = null;

        while ((pairLine = reader.readLine()) != null) {
            String strArr[] = pairLine.split(",");
            addEntry(strArr[0], strArr[1]);
        }
    }

    public void addEntry(String name, String phone) {
        if (validatePhone(phone))
            this.store.add(new PhoneBookEntry(name, formatPhone(phone)));
        else
            System.out.println("You entered wrong format Phone!");

        this.sortByName();
    }

    public void removeEntry(String name) {
        store.removeIf((PhoneBookEntry entry) -> entry.getName() == name);
    }

    public String getPhone(String name) {
        for (PhoneBookEntry entry : store)
            if (entry.getName()==name)
                return entry.getPhone();
        return null;
    }

    public String formatPhone(String phone) {
        if (validatePhone(phone))
            if (phone.charAt(0) != '+')
                return (phone.charAt(1) == '0') ? "+" + phone.substring(2) : "+" + phone.substring(1);
            else
                return phone;

        return null;
    }

    public boolean validatePhone(String phone) {
        return phone.matches(phoneFormat);
    }

    public void makeCall(String name) {
        for (PhoneBookEntry entry : store) {
            if(entry.getName().equals(name)) {

                if (top5.contains(entry))
                    top5.remove(entry);

                entry.call();
                updateTop5(entry);
                break;
            }
        }

    }

    private void sortByName() {
        store.sort((PhoneBookEntry entryA, PhoneBookEntry entryB) -> entryA.getName().compareTo(entryB.getName()));
    }

    private void updateTop5(PhoneBookEntry entry) {
        top5.add(entry);
        if (top5.size()>5)
            top5.poll();
    }

    public ArrayList<PhoneBookEntry> getStored() {
        return store;
    }

    public PriorityQueue<PhoneBookEntry> getTop5() {
        return top5;
    }
}
