package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Settings {
    private final static String PATH = "/Users/mac/Desktop/KR-exported-java-junit-maven-project (1)/src/test/resources/settings.xml";
    private String baseURL;
    private String login;
    private String password;

    private static final ThreadLocal<Settings> settings = new ThreadLocal<>();

    public static Settings getInstance()
    {
        if (settings.get() == null)
        {
            Settings newInstance = new Settings();
            settings.set(newInstance);
        }
        return settings.get();
    }

    private Settings() {
        try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(PATH));
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        baseURL = document.getElementsByTagName("baseURL").item(0).getTextContent();
        login = document.getElementsByTagName("login").item(0).getTextContent();
        password = document.getElementsByTagName("password").item(0).getTextContent();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
