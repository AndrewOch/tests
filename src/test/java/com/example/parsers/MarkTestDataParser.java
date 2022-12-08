package com.example.parsers;

import com.example.models.MarkData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkTestDataParser {

    private final static String PATH = "/Users/mac/Desktop/KR-exported-java-junit-maven-project (1)/src/test/resources/testData/marks.xml";

    public List<MarkData> parseData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(PATH));
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        NodeList nList = document.getElementsByTagName("mark");

        ArrayList<MarkData> marks = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            System.out.println();
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;

                String markId = eElement.getAttribute("id");
                String markText = eElement.getElementsByTagName("text").item(0).getTextContent();

                System.out.println("Mark id : "    + markId);
                System.out.println("Text : "   + markText);
                MarkData markData = new MarkData(markText);
                marks.add(markData);
            }
        }
        return marks;
    }
}
