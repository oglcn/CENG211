/**
 * Represents a product with a unique identifier, name, and price.
 */
public class Product {

    // Unique identifier for the product
    private int ID;
    
    // Name of the product
    private String productName;
    
    // Price of the product
    private double price;

    /**
     * Constructor to initialize a product with its ID, name, and price.
     *
     * @param ID          Unique identifier for the product
     * @param productName Name of the product
     * @param price       Price of the product
     */
    public Product(int ID, String productName, double price) {
        this.ID = ID;
        this.productName = productName;
        this.price = price;
    }

    // Below are getters and setters for the instance variables

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Sets the price for the product.
     * Ensures that the given price is non-negative.
     *
     * @param price New price value
     */
    public void setPrice(double price) {
        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    /**
     * Override the toString method to return a formatted string representation of the Product.
     * Provides details about the product's ID, name, and price.
     * 
     * @return A string representation of the product
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Product ID: ").append(this.ID).append("\n");
        sb.append("Product Name: ").append(this.productName).append("\n");
        sb.append("Price: ").append(String.format("%.2f",this.price)).append(" TL\n");

        return sb.toString();
    }

}
