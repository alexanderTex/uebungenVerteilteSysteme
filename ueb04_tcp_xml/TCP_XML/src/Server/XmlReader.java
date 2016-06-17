package Server;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Class for parsing incoming XML-Strings
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 */
public class XmlReader {
    private XMLInputFactory factory;

    public XmlReader() {
        factory = XMLInputFactory.newInstance();
    }

    /**
     * Parsing a XML nad returns an actual class of a Class which imlements IPerson
     * @param xml
     *          XML which will be parsed
     * @return
     *          The Class which is represented by the XML
     */
    public IPerson read(String xml) {
    	IPerson person = null;

    	try {
            // Cursor for iterating through the XML-String
	        XMLEventReader eventReader =
	            factory.createXMLEventReader(
	                new StreamSource(
	                    new ByteArrayInputStream(
	                        xml.getBytes(StandardCharsets.UTF_8))));

            // As long there is a next Element
	        while(eventReader.hasNext()) {
	            XMLEvent event = eventReader.nextEvent();

	            switch(event.getEventType()) {
                // An opening-tag
            	case XMLStreamConstants.START_ELEMENT:
            		StartElement startElement = event.asStartElement();

            		switch(startElement.getName().getLocalPart()) {
                    // Check if it is a Student or a Prof
            		case "Student":
        				person = new Student();
        				break;
            		case "Dozent":
            			person = new Dozent();
            			break;
                    // Check, which attribut to fill
            		case "Surname":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				person.setSurname(event.asCharacters().getData());
            			break;
            		case "Lastname":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				person.setLastname(event.asCharacters().getData());
            			break;
            		case "Strasse":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				person.setStrasse(event.asCharacters().getData());
            			break;
            		case "Hausnummer":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				person.setHausnummer(event.asCharacters().getData());
            			break;
            		case "Postleitzahl":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				person.setPostleitzahl(Integer.parseInt(event.asCharacters().getData()));
            			break;
            		case "Stadt":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				person.setStadt(event.asCharacters().getData());
            			break;
            		case "Matrikelnummer":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				((Student)person).setMatNr(event.asCharacters().getData());
            			break;
            		case "Studienrichtung":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				((Student)person).setStudienrichtung(event.asCharacters().getData());
            			break;
            		case "Fachsemester":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				((Student)person).setFachsemester(Integer.parseInt(event.asCharacters().getData()));
            			break;
            		case "Personalnummer":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				((Dozent)person).setPersNr((event.asCharacters().getData()));
            			break;
            		case "Fachbereich":
            			event = eventReader.nextEvent();

            			if (event.getEventType() == XMLStreamConstants.CHARACTERS)
            				((Dozent)person).setFachbereich((event.asCharacters().getData()));
            			break;
            		}
            		break;
            	case XMLStreamConstants.END_DOCUMENT:
            		return person;
	            }
	        }
    	} catch(XMLStreamException e) {
    		System.out.println("XMLStreamReader: " + e.getMessage());
    	}
        return null;
    }
}
