package com.musala;

import com.musala.classes.PhoneBook;

import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static PhoneBook phoneBook;

    public static void main(String[] args) {
        phoneBook = new PhoneBook();

        try {
            phoneBook.load("./resources/textInput.txt");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        do {
            printMenu();
        } while (actionHandler()!=0);

        System.out.print("Done!");
    }

    private static void printMenu() {
        System.out.println("PhoneBook Menu:");
        System.out.println("1- List PhoneBook");
        System.out.println("2- Add Entry");
        System.out.println("3- Remove Entry");
        System.out.println("0- Exit");
    }

    public static int actionHandler() {
        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 1: list();
                break;

            case 2: add();
                break;

            case 3: remove();
        }

        return action;
    }

    public static void list() {
        for (Map.Entry<String, String> entry : phoneBook.getStored().entrySet())
            System.out.println(entry.getKey() + " => " + entry.getValue());
    }

    public static void add() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Number: ");
        String phone = scanner.nextLine();
        phoneBook.addEntry(name, phone);
    }

    public static void remove() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        phoneBook.removeEntry(name);
    }
}
