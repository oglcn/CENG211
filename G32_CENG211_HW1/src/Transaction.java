public class Transaction {
    
    private int ID;  // Unique identifier for the transaction
    private Product[] products = new Product[3];  // Array to store 3 products that constitute a transaction
    private double totalPrice;  // Total price of all products in the transaction
    private double transactionFee;  // Transaction fee charged based on the total price
    private int[] amounts;  // Array to store quantities of the respective products in the transaction
    
    /**
     * Constructor to initialize a Transaction with its ID, products, and their amounts.
     * 
     * @param ID        Unique ID for the transaction
     * @param products  Array of products in the transaction
     * @param amounts   Quantities of the respective products in the transaction
     */
    public Transaction(int ID, Product[] products, int[] amounts) {
        // Ensure that the number of products and their amounts are exactly 3
        if (products.length != 3 || amounts.length != 3) {
            throw new IllegalArgumentException("Transaction must contain exactly 3 products");
        }
        
        this.ID = ID;
        System.arraycopy(products, 0, this.products, 0, products.length);
        this.amounts = amounts;
        
        // Calculate the total price and transaction fee based on the provided products and amounts
        this.totalPrice = calculateTotalPrice(amounts);
        this.transactionFee = calculateTransactionFee();
    }

    /**
     * Helper method to calculate the total price of the transaction.
     * This method considers the price of each product and its quantity.
     * 
     * @param amounts   Quantities of the respective products
     * @return          The total price of the transaction
     */
    private double calculateTotalPrice(int[] amounts) {
        double total = 0;
        int i = 0;
        for (Product product : products) {
            total += (product.getPrice() * amounts[i]); 
            i++;
        }
        return total;
    }

    /**
     * Helper method to determine the transaction fee based on the total price.
     * The transaction fee is a percentage of the total price which varies based on predefined slabs.
     * 
     * @return The transaction fee based on the total price
     */
    private double calculateTransactionFee() {
        if (totalPrice <= 499) {
            return 0.01 * totalPrice;  // 1% of totalPrice
        } else if (totalPrice <= 799) {
            return 0.03 * totalPrice;  // 3% of totalPrice
        } else if (totalPrice <= 999) {
            return 0.05 * totalPrice;  // 5% of totalPrice
        } else {
            return 0.09 * totalPrice;  // 9% of totalPrice
        }
    }

    // Below are getters for the instance variables

    public int getID() {
        return ID;
    }

    public Product[] getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    /**
     * Override the toString method to return a formatted string representation of the Transaction.
     * This method provides details about the transaction, the products involved, their quantities, total price, and transaction fee.
     * 
     * @return A string representation of the transaction
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Transaction ID: ").append(this.ID).append("\n");
        sb.append("Products in Transaction:\n");

        for (int i = 0; i < this.products.length; i++) {
            sb.append("\tProduct ID: ").append(products[i].getID());
            sb.append(", Name: ").append(products[i].getProductName());
            sb.append(", Price: ").append(String.format("%.2f", products[i].getPrice())).append(" TL");
            sb.append(", Amount: ").append(amounts[i]).append("\n"); 
        }

        sb.append("Total Price: ").append(String.format("%.2f", this.totalPrice)).append(" TL\n");
        sb.append("Transaction Fee: ").append(String.format("%.2f", this.transactionFee)).append(" TL\n");

        return sb.toString();
    }
}
