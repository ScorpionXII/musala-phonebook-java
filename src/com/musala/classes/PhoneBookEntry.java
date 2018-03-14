package com.musala.classes;

public class PhoneBookEntry {
    private String name;
    private String phone;
    private int calls;

    public PhoneBookEntry(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Different signature constructor to include calls
    public PhoneBookEntry(String name, String phone, int calls) {
        this(name, phone);
        this.calls = calls;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getCalls() {
        return calls;
    }

    public void call() {
        this.calls+=1;
    }
}
