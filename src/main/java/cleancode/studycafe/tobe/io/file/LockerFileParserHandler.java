package cleancode.studycafe.tobe.io.file;

import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;

import java.util.List;

public interface LockerFileParserHandler {

    List<StudyCafeLockerTicket> parse(List<String> lines);

}
