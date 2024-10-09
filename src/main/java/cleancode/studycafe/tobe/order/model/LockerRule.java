package cleancode.studycafe.tobe.order.model;

public enum LockerRule {
    ACTIVE("사물함 이용 허용"),
    INACTIVE("사물함 이용 불가");

    private final String description;

    LockerRule(String description) {
        this.description = description;
    }
}
