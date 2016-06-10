import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlReader {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new PartyHandler();
            saxParser.parse(new File("test.xml"), handler);
        } catch (ParserConfigurationException e) {
            System.out.println("Parser: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAX: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File: " + e.getMessage());
        }
    }
}

class PartyHandler extends DefaultHandler {
    //Student student;
    //Dozent dozent;

    @Override
    public void startDocument() {}

    @Override
    public void endDocument() {}

    @Override
    public void startElement(String namespace, String localName,
                             String qualiName, Attributes attr) {
        System.out.println(qualiName);
    }
}
