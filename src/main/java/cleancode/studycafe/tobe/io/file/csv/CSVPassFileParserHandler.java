package cleancode.studycafe.tobe.io.file.csv;

import cleancode.studycafe.tobe.io.file.PassFileParserHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.ArrayList;
import java.util.List;

public class CSVPassFileParserHandler implements PassFileParserHandler {
    @Override
    public List<StudyCafePass> parse(List<String> lines) {
        List<StudyCafePass> studyCafePasses = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(",");
            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);
            double discountRate = Double.parseDouble(values[3]);

            StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
            studyCafePasses.add(studyCafePass);
        }

        return studyCafePasses;
    }
}
