import javax.xml.stream.*;
import javax.xml.events.*;
import java.io.*;

public class XmlReader {
    private XMLInputFactory factory;

    public XmlReader() {
        factory = XMLInputFactory.newInstance();
    }

    public object read(String xml) {
        XMLEventReader eventReader =
            factory.createXMLEventReader(
                new StreamSource(
                    new ByteArrayInputStream(
                        xml.getBytes(StandardCharsets.UTF_8))));

        IPerson person;

        while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch(event.getEventType()) {
            }
        }
    }
}
