package cleancode.studycafe.tobe.io.file;

import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public interface PassFileParserHandler {

    List<StudyCafePass> parse(List<String> lines);

}
