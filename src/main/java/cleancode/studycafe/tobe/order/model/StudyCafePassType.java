package cleancode.studycafe.tobe.order.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권", LockerRule.INACTIVE),
    WEEKLY("주 단위 이용권", LockerRule.INACTIVE),
    FIXED("1인 고정석", LockerRule.ACTIVE);

    private final String description;
    private final LockerRule lockerRule;


    StudyCafePassType(String description, LockerRule lockerRule) {
        this.description = description;
        this.lockerRule = lockerRule;
    }

    public boolean doesNotUseLocker() {
        return lockerRule == LockerRule.INACTIVE;
    }
}
