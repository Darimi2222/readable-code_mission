package cleancode.studycafe.tobe.model;

public class Order {

    private final StudyCafePass studyCafePass;
    private final StudyCafeLockerTicket studyCafeLockerTicket;

    private Order(StudyCafePass studyCafePass, StudyCafeLockerTicket studyCafeLockerTicket) {
        this.studyCafePass = studyCafePass;
        this.studyCafeLockerTicket = studyCafeLockerTicket;
    }

    public static Order of(StudyCafePass studyCafePass, StudyCafeLockerTicket studyCafeLockerTicket){
        return new Order(studyCafePass, studyCafeLockerTicket);
    }

    public StudyCafePass getStudyCafePass() {
        return studyCafePass;
    }

    public StudyCafeLockerTicket getStudyCafeLockerTicket() {
        return studyCafeLockerTicket;
    }

    public int calculateDiscountPrice(){
        return (int) (studyCafePass.getPrice() * studyCafePass.getDiscountRate());
    }

    public int calculatePassPrice(){
        return studyCafePass.getPrice() - calculateDiscountPrice();
    }

    public int calculateLockerPrice(){
        if(studyCafeLockerTicket == null){
            return 0;
        }

        return studyCafeLockerTicket.getPrice();
    }

}
