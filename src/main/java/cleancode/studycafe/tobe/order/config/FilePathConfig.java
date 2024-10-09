package cleancode.studycafe.tobe.order.config;

public final class FilePathConfig {
    public static final String PASS_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    public static final String LOCKER_LIST_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    private FilePathConfig() {
        throw new UnsupportedOperationException("유틸리티 클래스로 인스턴스화 할 수 없습니다.");
    }

}
