package Data;

/**
 * An interface representing objects that can be borrowed, with methods related to borrowing and charges.
 */
public interface Borrowing {
    /**
     * Calculates the borrowing charge.
     *
     * @return The calculated borrowing charge.
     */
    int calculateBorrowingCharge();

    /**
     * Checks if the borrowing is late.
     *
     * @return true if the borrowing is late, false otherwise.
     */
    boolean isLate();

    /**
     * Calculates the late charge.
     *
     * @return The calculated late charge.
     */
    int calculateLateCharge();

    /**
     * Calculates the total price.
     *
     * @return The calculated total price.
     */
    double TotalPrice();

    /**
     * Calculates the discount.
     *
     * @return The calculated discount.
     */
    double Discount();
}
