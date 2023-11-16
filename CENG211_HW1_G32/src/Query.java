public class Query {

    private TransactionManagement transactionManagement;
    private SalaryManagement salaryManagement;

    public Query(TransactionManagement transactionManagement, SalaryManagement salaryManagement) {
        this.transactionManagement = transactionManagement;
        this.salaryManagement = salaryManagement;
    }

    /**
     * Finds the highest-total-price transaction.
     * 
     * @return The transaction with the highest total price.
     */
    public Transaction highestTotalPriceTransaction() {
        Transaction highest = null;

        for (Transaction[] assistantTransactions : transactionManagement.getTransactions()) {
            for (Transaction transaction : assistantTransactions) {
                if (highest == null || transaction.getTotalPrice() > highest.getTotalPrice()) {
                    highest = transaction;
                }
            }
        }
        return highest;
    }

    /**
     * Finds the most expensive product in the lowest-price transaction.
     * 
     * @return The most expensive product.
     */
    public Product mostExpensiveProductInLowestTransaction() {
        Transaction lowest = null;

        for (Transaction[] assistantTransactions : transactionManagement.getTransactions()) {
            for (Transaction transaction : assistantTransactions) {
                if (lowest == null || transaction.getTotalPrice() < lowest.getTotalPrice()) {
                    lowest = transaction;
                }
            }
        }


        Product[] products = lowest.getProducts();
        Product mostExpensive = products[0];

        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive;
    }

    /**
     * Finds the transaction with the lowest transaction fee.
     * 
     * @return The transaction with the lowest transaction fee.
     */
    public Transaction lowestTransactionFee() {
        Transaction lowestFeeTransaction = null;

        for (Transaction[] assistantTransactions : transactionManagement.getTransactions()) {
            for (Transaction transaction : assistantTransactions) {
                if (lowestFeeTransaction == null || transaction.getTransactionFee() < lowestFeeTransaction.getTransactionFee()) {
                    lowestFeeTransaction = transaction;
                }
            }
        }
        return lowestFeeTransaction;
    }
    


    /**
     * Finds the highest-salary shop assistant.
     * 
     * @return The shop assistant with the highest salary.
     */
    public ShopAssistant highestSalaryShopAssistant(ShopAssistant[] shopAssistants) {
        ShopAssistant highestSalaryAssistant = null;
        double highestSalary = 0;

        for (ShopAssistant assistant : shopAssistants) {
            double salary = salaryManagement.calculateMonthlySalary(assistant.getID());
            if (salary > highestSalary) {
                highestSalary = salary;
                highestSalaryAssistant = assistant;
            }
        }

        return highestSalaryAssistant;
    }

    /**
     * Calculates the total revenue from all transactions.
     * 
     * @return Total revenue.
     */
    public double totalRevenue() {
        double revenue = 0;

        for (Transaction[] assistantTransactions : transactionManagement.getTransactions()) {
            for (Transaction transaction : assistantTransactions) {
                revenue += transaction.getTotalPrice() + transaction.getTransactionFee();
            }
        }
        return revenue;
    }

    /**
     * Calculates the total profit after deducting all shop assistants' salaries.
     * 
     * @return Total profit.
     */
    public double totalProfit(ShopAssistant[] shopAssistants) {
        double totalSalaries = 0;
        for (ShopAssistant assistant : shopAssistants) {

            totalSalaries += salaryManagement.calculateMonthlySalary(assistant.getID());
        }


        return totalRevenue() - totalSalaries;
    }

}
