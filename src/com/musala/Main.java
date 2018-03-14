package com.musala;

import com.musala.classes.PhoneBook;
import com.musala.classes.PhoneBookEntry;

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
        System.out.println("4- Make Call");
        System.out.println("5- List by Calls");
        System.out.println("0- Exit");
    }

    private static int actionHandler() {
        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 1: list();
                break;

            case 2: add();
                break;

            case 3: remove();
                break;

            case 4: call();
                break;

            case 5: top5();
                break;
        }

        return action;
    }

    private static void list() {
        for (PhoneBookEntry entry : phoneBook.getStored())
            System.out.println(entry.getName() + " => " + entry.getPhone() + ", " + entry.getCalls());
    }

    private static void add() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Number: ");
        String phone = scanner.nextLine();
        phoneBook.addEntry(name, phone);
    }

    private static void remove() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        phoneBook.removeEntry(name);
    }

    private static void call() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        phoneBook.makeCall(name);
    }

    private static void top5() {
        for (PhoneBookEntry entry : phoneBook.getTop5())
            System.out.println(entry.getName() + " => " + entry.getPhone() + "," + entry.getCalls());
    }
}
