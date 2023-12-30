import java.util.Random;

public class TransactionManagement {
    private int transactionCount = 0;  // Counter to keep track of total number of transactions
    
    // A 2D array to hold transactions for each shop assistant
    private Transaction[][] transactions;
    private int productCount;  // Total number of products available
    private Product[] allProducts = null;  // Reference to all available products
    private SalaryManagement salaryManagement = null;  // Instance to manage salaries

    /**
     * Constructor to initialize TransactionManagement with given parameters.
     *
     * @param numAssistants Number of shop assistants
     * @param transactionsPerAssistant Maximum number of transactions per assistant
     * @param allProducts Array of all available products
     * @param salaryManagement Instance to manage salaries based on transactions
     */
    public TransactionManagement(int numAssistants, int transactionsPerAssistant, Product[] allProducts, SalaryManagement salaryManagement) {
        this.transactions = new Transaction[numAssistants][transactionsPerAssistant];
        this.productCount = allProducts.length;
        this.allProducts = allProducts;
        this.salaryManagement = salaryManagement;
    }

    /**
     * Private method to add a transaction for a specific shop assistant.
     *
     * @param shopAssistantIndex Index of the shop assistant
     * @param transactionIndex   Index of the transaction for the assistant
     * @param transaction        The transaction to be added
     */
    private void addTransaction(int shopAssistantIndex, int transactionIndex, Transaction transaction) {
        // Register the transaction in the SalaryManagement instance
        salaryManagement.registerTransaction(shopAssistantIndex, transaction.getTotalPrice());
        // Store the transaction in the 2D array
        transactions[shopAssistantIndex][transactionIndex] = transaction;
    }

    /**
     * Get all transactions associated with a specific shop assistant.
     *
     * @param shopAssistant The shop assistant whose transactions are to be fetched
     * @return An array of transactions for the given shop assistant
     */
    public Transaction[] getTransactionsForAssistant(ShopAssistant shopAssistant) {
        int index = shopAssistant.getID(); // Assuming IDs are used as indices for simplicity
        return transactions[index];
    }

    /**
     * Calculate the total revenue generated from a set of transactions.
     *
     * @param transactions An array of transactions
     * @return The total revenue from those transactions
     */
    public double getTotalRevenue(Transaction[] transactions) {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getTotalPrice();
        }
        return total;
    }

    /**
     * Get the entire set of transactions for all shop assistants.
     *
     * @return A 2D array containing all transactions
     */
    public Transaction[][] getTransactions() {
        return transactions;
    }

    /**
     * Fetch a specific transaction of a shop assistant based on indices.
     *
     * @param shopAssistantIndex Index of the shop assistant
     * @param transactionIndex   Index of the transaction for the assistant
     * @return The desired transaction
     */
    public Transaction getTransaction(int shopAssistantIndex, int transactionIndex) {
        return transactions[shopAssistantIndex][transactionIndex];
    }

    /**
     * Generate and add a random transaction for a shop assistant.
     * This involves selecting random products in random amounts.
     *
     * @param shopAssistantIndex Index of the shop assistant
     * @param transactionIndex   Index for the new transaction
     */
    public void addRandomTransaction(int shopAssistantIndex, int transactionIndex) {
        Random rnd = new Random();
        Product[] productsInTransaction = new Product[3];
        int[] amounts = new int[3];

        // Randomly choose products and assign them to the transaction array
        for (int i = 0; i < productsInTransaction.length; i++) {
            int productId = rnd.nextInt(productCount);
            productsInTransaction[i] = allProducts[productId];
            
            amounts[i] = rnd.nextInt(1,11);  // Random amount between 1 and 10
        }

        // Create a new transaction object with a unique ID and the randomly picked products
        Transaction transaction = new Transaction(transactionCount, productsInTransaction, amounts);
        transactionCount++;

        // Store this new transaction in the 2D array
        addTransaction(shopAssistantIndex, transactionIndex, transaction);
    }
}
