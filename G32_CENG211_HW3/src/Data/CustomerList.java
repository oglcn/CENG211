package Data;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    private final List<Customer> customerList;

    // Constructor
    public CustomerList() {
        this.customerList = new ArrayList<>();
    }
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }
    public void printCustomers() {
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }}
    // Search for an item by title
    public void searchByTitle(String title) {
        for (Customer customer : customerList) {
            if (customer.borrowableItem.searchByTitle(title)) {
                System.out.println("Found item:");
                System.out.println(customer.toString());
                return;
            }
        }
        System.out.println("Item with title '" + title + "' does not exist.");
    }

    // Search for an item by title and item type
    public  void searchByTitleAndItemType(String title, String itemType) {
        for (Customer customer : customerList) {
            if (customer.borrowableItem.searchByTitleAndItemType(title, itemType)) {
                System.out.println("Found item:");
                System.out.println(customer.toString());
                return;
            }
        }
        System.out.println("Item with title '" + title + "' and item type '" + itemType + "' does not exist.");
    }

}
