package cleancode.studycafe.tobe.order.config;

import cleancode.studycafe.tobe.order.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.order.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.order.io.InputHandler;
import cleancode.studycafe.tobe.order.io.OutputHandler;
import cleancode.studycafe.tobe.order.io.file.FileReadHandler;
import cleancode.studycafe.tobe.order.io.file.LockerFileParserHandler;
import cleancode.studycafe.tobe.order.io.file.PassFileParserHandler;
import cleancode.studycafe.tobe.order.io.file.csv.CSVFileReadHandler;
import cleancode.studycafe.tobe.order.io.file.csv.CSVLockerParserHandler;
import cleancode.studycafe.tobe.order.io.file.csv.CSVPassFileParserHandler;

public class StudyCafeHandlerFactory {

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
