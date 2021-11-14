import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Manager {
    private double budget;
    private double originalBudget;

    private List<Product> productList = new ArrayList<>();  //the arraylist of games w titles, prices, links
    private List<Integer> indexes = new ArrayList<>(); //indexes of every game we have

    private final Scanner scan = new Scanner(System.in);

    public List<Product> makeList()
    {
        String game;
        String console;

        boolean endProgram = false;

        System.out.println("Welcome to GameStop Inventory Manager!\n");

        System.out.println("How much money do you have in your budget?");

        budget = scan.nextDouble();
        originalBudget = budget;

        scan.nextLine();

        while(!endProgram) {

            System.out.println("\nWhat game are you looking to purchase?");
            game = scan.nextLine();

            System.out.println("What console would you like it on?");
            console = scan.nextLine();

            String link = Website.getLink(game, console);

            double price;

            try {
                price = Website.getPrice(new URL(link));

                if(price > originalBudget)
                {
                    System.out.println("This item cannot be added due to its price");
                    continue;
                }
            } catch (MalformedURLException e) {
                System.out.println("An incorrect URL was inserted");
                continue;
            }

            if(budget - price < 0) {
                System.out.println("\nYou do not have enough money for this game :(");

                if(productList.size() == 0)
                {
                    continue;
                }

                System.out.print("Would you like to put back another game? (Y/y or N/n) ");

                if(scan.nextLine().toLowerCase(Locale.ROOT).equals("y"))
                {
                    int res = 0;

                    while(res == 0)
                    {
                        displayList();
                        if(getIndexes(price) > 0)
                        {
                            deleteProducts(indexes);
                            res = 1;
                        }
                        else
                        {
                            System.out.println("The item still costs too much even if you delete these items");
                        }
                    }
                }
                else
                {
                    continue;
                }
            }

            budget -= price;
            productList.add(new Product(game, price, link)); //Add the game to the arraylist of


            System.out.println("\nGame added to list!");
            System.out.println("Game Price: $" + price);
            System.out.println("Current Budget: $" + Math.round(budget * 100.0) / 100.0);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\nWould you like to continue? (Y/y or N/n)");
            endProgram = scan.nextLine().toLowerCase(Locale.ROOT).equals("n");
        }
        return productList;
    }

    private void displayList()
    {
        int i = 1;

        System.out.println();

        for(Product p : productList)
        {
            System.out.println("#" + i + " " + p.getName() + " " + p.getValue());
            i++;
        }
        System.out.println();
    }

    private int getIndexes(double price)
    {

        System.out.print("Enter the game (By #) you would like to delete separated by spaces: ");
        String deleteThese = scan.nextLine();

        String[] array = deleteThese.split(" ");

        for(String s : array)
        {
            indexes.add(Integer.parseInt(s) - 1);
        }

        int count = 0;

        for(Integer i : indexes)
        {
            count += productList.get(i).getValue();
        }

        if(budget + count < price)
        {
            indexes.clear();
            return 0;
        }
        return 1;
    }

    private void deleteProducts(List<Integer> indexes)
    {
        List<Product> tempList = new ArrayList<>();

        for(Product p : productList)
        {
            tempList.add(p);
        }

        productList.clear();

        int i = 0;

        for(Product p : tempList)
        {
            if(indexes.contains(i))
            {
                budget += p.getValue();
                i++;
                continue;
            }
            productList.add(p);
            i++;
        }
    }

}
