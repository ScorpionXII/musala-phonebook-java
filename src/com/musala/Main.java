package com.musala;

import com.musala.classes.PhoneBook;

import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
	// write your code here

        TreeMap<String, String> maptest = new TreeMap<String, String>();

        maptest.put("Heriberto", "123456");
        maptest.put("Carlos", "9862515");
        maptest.put("Heriberto Angel", "4522452");

        for (Map.Entry<String, String> entry : maptest.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        PhoneBook pb = new PhoneBook();
        try {
            pb.load("./resources/textInput.txt");
        } catch (Exception e) {
            System.out.print(e.toString());
        }

        System.out.println(pb.formatNumber("00359882000000"));

        System.out.println(pb.validateNumber("+359881000000"));

        System.out.println(pb.getStored().entrySet().toString());

        System.out.print("Test it!");
    }
}
