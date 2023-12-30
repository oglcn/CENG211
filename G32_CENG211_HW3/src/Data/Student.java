package Data;

public class Student extends Customer {
    public Student(BorrowableItem borrowableItem,boolean hasScholarship){
        super(borrowableItem,hasScholarship);}

    public double Discount(){
        if(super.hasScholarship){
            return (double) (super.calculateBorrowingCharge() * 3) /10;
        }
        else return (double) super.calculateBorrowingCharge() /5;
    }
}
