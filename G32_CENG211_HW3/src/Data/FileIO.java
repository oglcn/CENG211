package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * A class for reading data from a file and creating a CustomerList.
 */
public class FileIO {

    /**
     * Reads data from the specified file path and creates a CustomerList.
     *
     * @param filePath The path of the file to be read.
     * @return A CustomerList containing the read data.
     */
    public static CustomerList readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            StringTokenizer stringTokenizer = null;
            String itemCode = "";
            String title = "";
            String priority = "";
            String itemType = "";
            String author = "";
            String publisher = "";
            String customerType = "";
            String startBorrow = "";
            String endBorrow = "";

            CustomerList customerList = new CustomerList();
            BorrowableItem item;
            Customer customer;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Tokenize each line using ';' as the delimiter
                stringTokenizer = new StringTokenizer(line, ";");
                // Process each token
                while (stringTokenizer.hasMoreTokens()) {
                    itemCode = stringTokenizer.nextToken();
                    title = stringTokenizer.nextToken();
                    priority = stringTokenizer.nextToken();
                    itemType = stringTokenizer.nextToken();
                    author = stringTokenizer.nextToken();
                    publisher = stringTokenizer.nextToken();
                    customerType = stringTokenizer.nextToken();
                    startBorrow = stringTokenizer.nextToken();
                    endBorrow = stringTokenizer.nextToken();
                }

                // Create a BorrowableItem based on itemType
                if (Objects.equals(itemType, "book")) {
                    item = new Book(itemCode, title, priority, author, publisher, startBorrow, endBorrow);
                } else {
                    item = new Magazine(itemCode, title, priority, author, publisher, startBorrow, endBorrow);
                }

                // Create a Customer based on customerType
                if (Objects.equals(customerType, "general")) {
                    customer = new GeneralMember(item);
                } else if (Objects.equals(customerType, "studentWOScholar")) {
                    customer = new Student(item, false);
                } else {
                    customer = new Student(item, true);
                }

                // Add the created Customer to the CustomerList
                customerList.addCustomer(customer);
            }
            reader.close();
            return customerList;

        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println(e.getLocalizedMessage());
            return null;

        } catch (IOException e) {
            // Handle IO exception
            System.out.println(e.getLocalizedMessage());
            return null;

        } catch (ParseException e) {
            // Wrap ParseException in a RuntimeException
            throw new RuntimeException(e);
        }
    }
}
