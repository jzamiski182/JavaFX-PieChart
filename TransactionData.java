import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TransactionData
{
    // total number of categories
    private final int CATEGORIES = 6;
    // enums are public static final by default
    enum Categories { RESTAURANTS, TRANSPORTATION, HEALTH, SHOPPING, INSURANCE, GROCERIES };
    // array to store the total number of instances for each category
    private int[] counts;
    // stores the total number of transactions read from the CSV file
    private int total;

    public TransactionData()
    {
        total = 0;
        counts = new int[CATEGORIES];
    }

    public void countCategories(String filePath)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] values;

            while ((line = reader.readLine()) != null) {
                total++;
                values = line.split(",");

                String category = values[values.length - 1];
                switch (category) {
                    case "Restaurants & Dining":
                        counts[Categories.RESTAURANTS.ordinal()]++;
                        break;
                    case "Transportation":
                        counts[Categories.TRANSPORTATION.ordinal()]++;
                        break;
                    case "Health":
                        counts[Categories.HEALTH.ordinal()]++;
                        break;
                    case "Shopping & Entertainment":
                        counts[Categories.SHOPPING.ordinal()]++;
                        break;
                    case "Insurance":
                        counts[Categories.INSURANCE.ordinal()]++;
                        break;
                    case "Groceries":
                        counts[Categories.GROCERIES.ordinal()]++;
                        break;
                }
            }
        }
        catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public int[] getCategoryCounts()
    {
        return counts;
    }

    public int getTotal()
    {
        return total;
    }
}
