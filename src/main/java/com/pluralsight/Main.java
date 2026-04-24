package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //created hashmap for the products
        HashMap <String, Inventory> products = new HashMap<String, Inventory>();

        //creating the buffreader to add products from the source file
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                Float price = Float.parseFloat(parts[2]);

                Inventory product = new Inventory(id, name, price);
                products.put(name, product);
            }
            reader.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        //display all products in the inventory
        System.out.println("ID \tProduct Name \t \t\t Price");
        for(Inventory key : products.values()) {

            System.out.printf("%d %s \t %.2f \n",key.getId(),key.getName(),key.getPrice());

        }

        boolean searchAgain = true;

        //a while loop to search product repeatedly
        while (searchAgain) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("What product would you like to search? (Enter X to quit.)");
            String userInput = scanner.nextLine();

            //prints out the product or exits
            if (userInput.equalsIgnoreCase("x")) {
                System.out.println("you have exited the program.");
                searchAgain = false;
            }
            else {
                Inventory print = products.get(userInput);
                System.out.printf("%d %s %.2f \n",print.getId(),print.getName(),print.getPrice());

            }
        }
    }
}
