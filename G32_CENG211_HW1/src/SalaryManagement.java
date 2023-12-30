/**
 * The SalaryManagement class is responsible for handling the salaries
 * and commissions for shop assistants based on the transactions they process.
 */
public class SalaryManagement {

    // An array containing all the shop assistants in the system.
    private ShopAssistant[] shopAssistants;

    // Constants for determining the commission rate.
    private static final double LOW_COMMISSION_RATE = 0.01;  // Default commission rate.
    private static final double HIGH_COMMISSION_RATE = 0.03; // Commission rate for assistants exceeding the revenue threshold.
    private static final double REVENUE_THRESHOLD = 7500.0;  // Revenue threshold to determine commission rate.

    /**
     * Constructor to initialize the SalaryManagement object with the provided shop assistants.
     *
     * @param shopAssistants An array of shop assistants in the system.
     */
    public SalaryManagement(ShopAssistant[] shopAssistants) {
        this.shopAssistants = shopAssistants;
    }
    
    /**
     * Registers a transaction for a shop assistant by adding the transaction amount
     * to their total revenue.
     *
     * @param assistantID The ID of the shop assistant handling the transaction.
     * @param price The total price of the transaction.
     */
    public void registerTransaction(int assistantID, double price) {
    	shopAssistants[assistantID].addToRevenue(price);
    }

    /**
     * Calculate the commission rate based on the total revenue from the transactions the shop assistant handled.
     * 
     * @param totalRevenue Total revenue from the transactions.
     * @return Commission rate.
     */
    private double getCommissionRate(double totalRevenue) {
        return totalRevenue > REVENUE_THRESHOLD ? HIGH_COMMISSION_RATE : LOW_COMMISSION_RATE;
    }

    /**
     * Calculate the monthly salary for a shop assistant.
     * 
     * @param shopAssistant The shop assistant for whom the salary is to be calculated.
     * @param totalRevenue Total revenue from the transactions the shop assistant handled.
     * @return The total monthly salary.
     */
    public double calculateMonthlySalary(int assistantID) {
    	ShopAssistant assistant = shopAssistants[assistantID];
        double monthlyBasis = (assistant.getWeeklySalaryBasis() * 4);  // Assuming 4 weeks in a month
        double commission = assistant.getTotalRevenue() * getCommissionRate(assistant.getTotalRevenue());
        assistant.setCommission(commission);
        assistant.setSalary(monthlyBasis + commission);
        return monthlyBasis + commission;
    }
    
}
