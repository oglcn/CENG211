package Data;

abstract public class Customer implements Borrowing{
    protected BorrowableItem borrowableItem;
    protected boolean hasScholarship;
    public Customer(BorrowableItem borrowableItem,boolean hasScholarship){
        this.borrowableItem = borrowableItem;
        this.hasScholarship=hasScholarship;
    }
    public abstract double Discount();
    public double TotalPrice(){
        return calculateBorrowingCharge()+calculateLateCharge()-Discount();
    }
    public String toString(){
        return borrowableItem.toString()+ "Exceed: "+isExceed()+" Total Price: $"+TotalPrice();
    }
    public int calculateBorrowingCharge() {
        return borrowableItem.BORROWING_CHARGE * borrowableItem.getPriority() * borrowableItem.getNumberOfBorrowingDays();
    }
    public int calculateLateCharge() {
        return isLate() ? borrowableItem.PENALTY : 0;
    }
    public boolean isLate() {
        return borrowableItem.getNumberOfBorrowingDays() > borrowableItem.BORROWING_DAY_LIMIT;
    }
    public String isExceed(){if(isLate()){return "Exceeds";}
    else return "Not Exceeds";}

}
