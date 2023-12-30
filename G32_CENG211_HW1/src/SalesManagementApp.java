public class SalesManagementApp {

    private static final String PRODUCTS_CSV = "products.csv";
    private static final String SHOP_ASSISTANTS_CSV = "shopAssistants.csv";
    private static final int TRANSACTIONS_PER_ASSISTANT = 15;

    public static void main(String[] args) {
        // Initialize FileIO to read the data
        FileIO fileIO = new FileIO();

        // Load products and shop assistants
        Product[] products = fileIO.loadProducts(PRODUCTS_CSV);
        ShopAssistant[] shopAssistants = fileIO.loadShopAssistants(SHOP_ASSISTANTS_CSV);

        // Initialize TransactionManagement and SalaryManagement
        SalaryManagement salaryManagement = new SalaryManagement(shopAssistants);
        TransactionManagement transactionManagement = new TransactionManagement(
        				shopAssistants.length, 
        				TRANSACTIONS_PER_ASSISTANT, 
        				products, 
        				salaryManagement);

        // Generate random transactions
        for (int i = 0; i < shopAssistants.length; i++) {
            for (int j = 0; j < TRANSACTIONS_PER_ASSISTANT; j++) {  	
            	transactionManagement.addRandomTransaction(i,j);            	
            }
        }

        // Initialize the Query class
        Query query = new Query(transactionManagement, salaryManagement);

        // Answer and display results of the queries
        System.out.println("1. Highest-total-price transaction:\n" + query.highestTotalPriceTransaction());
        System.out.println("2. Most expensive product in the lowest-price transaction:\n" + query.mostExpensiveProductInLowestTransaction());
        System.out.println("3. Lowest transaction fee:\n" + query.lowestTransactionFee());
        System.out.println("4. Highest-salary shop assistant:\n" + query.highestSalaryShopAssistant(shopAssistants));
        System.out.println("5. Total revenue from 1500 transactions:\n" + String.format("%.2f",query.totalRevenue()) + " TL\n");
        System.out.println("6. Total profit after salaries:\n" + String.format("%.2f",query.totalProfit(shopAssistants)) + " TL\n");
    }
}
