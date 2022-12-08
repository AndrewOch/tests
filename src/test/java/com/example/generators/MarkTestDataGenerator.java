package com.example.generators;

import com.example.models.MarkData;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class MarkTestDataGenerator {

    private final static String PATH = "/Users/mac/Desktop/KR-exported-java-junit-maven-project (1)/src/test/resources/testData/marks.xml";
    private final int generatedDataCount = 50;
    private final Faker faker = new Faker();

    public static void main(String[] args) {
        MarkTestDataGenerator markTestDataGenerator = new MarkTestDataGenerator();
        markTestDataGenerator.createXML();
    }

    private MarkData generateMarkData() {
        Name name = faker.name();
        return new MarkData(name.title());
    }

    private void createXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("markDataArray");
            doc.appendChild(rootElement);

            for (int i = 0; i < generatedDataCount; i++) {
                MarkData markData = generateMarkData();

                Element markElem = doc.createElement("mark");
                rootElement.appendChild(markElem);

                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(i));
                markElem.setAttributeNode(attr);

                Element text = doc.createElement("text");
                text.appendChild(doc.createTextNode(markData.getText()));
                markElem.appendChild(text);
            }

            //write the content into xml file
            TransformerFactory transformerFactory =  TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File(PATH));
            transformer.transform(source, result);

            System.out.println("Done");

        }catch(ParserConfigurationException | TransformerException pce){
            pce.printStackTrace();
        }
    }
}
