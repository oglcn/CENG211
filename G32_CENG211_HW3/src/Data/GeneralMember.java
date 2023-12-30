package Data;

public class GeneralMember extends Customer {
    public GeneralMember(BorrowableItem borrowableItem){
        super(borrowableItem,false);}
    public double Discount(){return 0;}
    }

