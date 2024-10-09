package cleancode.studycafe.tobe.order;

public enum OrderProcessStatus {

    IN_PROGRESS("주문 중"),
    COMPLETE("주문 완료")
    ;

    private final String description;

    OrderProcessStatus(String description) {
        this.description = description;
    }
}
