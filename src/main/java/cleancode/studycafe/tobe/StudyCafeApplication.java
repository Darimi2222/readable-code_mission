package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.file.*;

public class StudyCafeApplication {

    public static void main(String[] args) {
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        FileReadHandler fileReadHandler = new CSVFileReadHandler();
        PassFileParserHandler passFileParserHandler = new CSVPassFileParserHandler();
        LockerTicketFileParserHandler lockerTicketFileParserHandler = new CSVLockerTicketParserHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, fileReadHandler, passFileParserHandler, lockerTicketFileParserHandler);
        studyCafePassMachine.run();
    }

}
