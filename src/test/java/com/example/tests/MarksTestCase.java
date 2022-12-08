package com.example.tests;

import com.example.AppManager;
import com.example.models.AccountData;
import com.example.models.MarkData;
import com.example.parsers.MarkTestDataParser;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarksTestCase extends AuthBase {

    public static Stream<Arguments> parseMarks() throws ParserConfigurationException, IOException, SAXException {
        MarkTestDataParser markTestDataParser = new MarkTestDataParser();
        List<MarkData> markData = markTestDataParser.parseData();
        ArrayList<Arguments> arguments = new ArrayList<>();
        for (MarkData mark: markData) {
            arguments.add(Arguments.of(mark));
        }
        return arguments.stream();
    }

    @ParameterizedTest
    @MethodSource("parseMarks")
    public void testCreateMark(MarkData input) throws Exception {
        app.navigation.openHomePage();
        Thread.sleep(1000);

        app.navigation.openMarksPage();
        app.marks.postMark(input);
        MarkData createdMarkData = app.marks.getCreatedMarkData();
        Assertions.assertTrue(createdMarkData.getText().contains(input.getText()));
    }
}
