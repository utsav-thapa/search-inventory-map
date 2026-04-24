package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap <Integer, Inventory> products = new HashMap<Integer, Inventory>();
        try{
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            Float price = Float.parseFloat(parts[2]);

            Inventory product = new Inventory(id, name, price);
            products.put(id, product);
        }
        reader.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        boolean searchAgain = true;

        while (searchAgain) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("What product would you like to search? (Enter X to quit.)");
            int userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput) {
                case('X'):
                    searchAgain = false;
                    break;
                default:
                    Inventory print = products.get(userInput);
                    System.out.println(print.toString());
            }
        }
    }
}
