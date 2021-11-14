import javax.swing.*;
import java.util.*;
import java.io.*;

public class Output {

    public static void generateOutputFile(List<Product> products, File file)
    {
        if(file == null)
        {
            System.out.println("An error has occurred");
        }

        try {
            int i = 1;

            FileWriter writer = new FileWriter(file);

            writer.write("Game List:\n\n");

            for(Product p : products)
            {
                writer.write("Item #" + i + ":\n");
                writer.write("Name: " + p.getName() + "\n");
                writer.write("Price: $" + p.getValue() + "\n");
                writer.write("Link: " + p.getWebsiteLink() + "\n\n");

                i++;
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("An error has occurred");
        }
    }

    public static File getFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Inventory");

        chooser.showSaveDialog(null);

        return new File(chooser.getSelectedFile() + ".txt");
    }
}
