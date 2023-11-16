import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

    /**
     * Count the number of lines in the given file.
     * Useful for determining the size of an array when reading CSV files.
     * 
     * @param filePath Path to the file.
     * @return The number of lines in the file.
     * @throws IOException If an I/O error occurs.
     */
    private int countLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lines = 0;
            while (reader.readLine() != null) lines++;
            return lines;
        }
    }
    
    /**
     * Load products from a CSV file.
     * 
     * @param filePath Path to the CSV file.
     * @return An array of Product objects.
     */
    public Product[] loadProducts(String filePath) {
        Product[] products = null;

        try {
            int numberOfProducts = countLines(filePath);  // Determine the number of products from the file.
            products = new Product[numberOfProducts];

            // Use BufferedReader to read the file line by line.
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");  // Split the line using ";" as the delimiter.
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble((parts[2].trim()).replace(",", "."));
                
                // Create a new Product object and store it in the array.
                products[index++] = new Product(id, name, price);
            }
            reader.close();  // Close the reader to free up resources.

        } catch (IOException e) {
            e.printStackTrace();  // Log any I/O errors.
        }

        return products;
    }

    /**
     * Load shop assistants from a CSV file.
     * 
     * @param filePath Path to the CSV file.
     * @return An array of ShopAssistant objects.
     */
    public ShopAssistant[] loadShopAssistants(String filePath) {
        ShopAssistant[] assistants = null;

        try {
            int numberOfAssistants = countLines(filePath);  // Determine the number of shop assistants from the file.
            assistants = new ShopAssistant[numberOfAssistants];

            // Use BufferedReader to read the file line by line.
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");  // Split the line using ";" as the delimiter.
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String surname = parts[2].trim();
                String phoneNumber = parts[3].trim();
                
                // Create a new ShopAssistant object and store it in the array.
                assistants[index++] = new ShopAssistant(id, name, surname, phoneNumber);
            }
            reader.close();  // Close the reader to free up resources.

        } catch (IOException e) {
            e.printStackTrace();  // Log any I/O errors.
        }

        return assistants;
    }
}
