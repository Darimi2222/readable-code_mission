package cleancode.studycafe.tobe.config;

import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.file.FileReadHandler;
import cleancode.studycafe.tobe.io.file.LockerFileParserHandler;
import cleancode.studycafe.tobe.io.file.PassFileParserHandler;
import cleancode.studycafe.tobe.io.file.csv.CSVFileReadHandler;
import cleancode.studycafe.tobe.io.file.csv.CSVLockerParserHandler;
import cleancode.studycafe.tobe.io.file.csv.CSVPassFileParserHandler;

public class StudyCafeHandlerFactory {

    private final InputHandler inputHandler = new ConsoleInputHandler();
    private final OutputHandler outputHandler = new ConsoleOutputHandler();
    private final FileReadHandler fileReadHandler = new CSVFileReadHandler();
    private final PassFileParserHandler passFileParserHandler = new CSVPassFileParserHandler();
    private final LockerFileParserHandler lockerFileParserHandler = new CSVLockerParserHandler();

    public static InputHandler createInputHandler(){
        return new ConsoleInputHandler();
    }

    public static OutputHandler createOutputHandler(){
        return new ConsoleOutputHandler();
    }

    public static FileReadHandler createFileReadHandler(){
        return new CSVFileReadHandler();
    }

    public static PassFileParserHandler createPassFileParserHandler(){
        return new CSVPassFileParserHandler();
    }

    public static LockerFileParserHandler createLockerFileParserHandler(){
        return new CSVLockerParserHandler();
    }
}
