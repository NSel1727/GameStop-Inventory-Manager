/*
    Group Members: Noah Seligson, Andy Gelfo, Evan Daniel

    GameStop Inventory Manager
 */

import java.util.List;

public class Application {
    public static void main (String[] args)
    {
        try
        {
            List<Product> list = new Manager().makeList();

            Output.generateOutputFile(list, Output.getFile());

            System.out.println("----------------------");
            System.out.println("Generating Output File");
            System.out.println("----------------------");

            Thread.sleep(3000);

            System.out.println("\nThank you for using this application!");
        } catch (InterruptedException e) {
            System.out.println("An error has occurred");
        }
    }
}
