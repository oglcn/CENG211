import java.util.Random;
public class ShopAssistant {

    private int ID;
    private String name;
    private String surname;
    private String phoneNumber;
    private int seniority; // Assuming that seniority is represented in years
    private double totalRevenue = 0;
    private double totalComission = 0;
    private double totalSalary = 0;
    

    /**
     * Constructor for the ShopAssistant class.
     *
     * @param ID         - Unique identifier for the shop assistant
     * @param name       - First name of the shop assistant
     * @param surname    - Last name of the shop assistant
     * @param phoneNumber- Phone number of the shop assistant
     * @param seniority  - Years of experience or seniority
     */
    public ShopAssistant(int ID, String name, String surname, String phoneNumber) {
    	Random rnd = new Random();
    	
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.seniority = rnd.nextInt(0, 16);
    }

    // Getters and setters for the fields

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        // A simple validation for phone numbers can be added here if needed
        this.phoneNumber = phoneNumber;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        if(seniority < 0) {
            throw new IllegalArgumentException("Seniority cannot be negative");
        }
        this.seniority = seniority;
    }

    /**
     * This method determines the weekly salary based on the seniority of the shop assistant.
     * 
     * @return Weekly Salary Basis as per the seniority.
     */
    public double getWeeklySalaryBasis() {
        if (seniority < 1) {
            return 1500;
        } else if (seniority < 3) {
            return 2000;
        } else if (seniority < 5) {
            return 2500;
        } else {
            return 3000;
        }
    }
    
    public double getTotalRevenue(){
    	return totalRevenue;
    }
    
    public void addToRevenue(double revenue) {
    	totalRevenue += revenue;
    }
    
    
    public double getCommission() {
    	return totalComission;
    }
    
    public void setCommission(double comission) {
    	this.totalComission=comission;
    }
    
    
    public double getSalary() {
    	return totalSalary;
    }
    
    public void setSalary(double salary) {
    	this.totalSalary=salary;
    }

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Shop Assistant ID: ").append(this.ID).append("\n");
    sb.append("Name: ").append(this.name).append("\n");
    sb.append("Surname: ").append(this.surname).append("\n");
    sb.append("Seniority: ").append(this.seniority).append(" years\n");
    sb.append("Weekly Basis Salary: ").append(String.format("%.2f",getWeeklySalaryBasis())).append(" TL\n");
    sb.append("Commission: ").append(String.format("%.2f",getCommission())).append(" TL\n");
    sb.append("Total Salary: ").append(String.format("%.2f",getSalary())).append(" TL\n");

    return sb.toString();
}


}
