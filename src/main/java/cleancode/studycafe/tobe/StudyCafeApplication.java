package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeHandlerFactory;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine();
        studyCafePassMachine.run();
    }

}
