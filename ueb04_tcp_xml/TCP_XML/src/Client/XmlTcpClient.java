package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.xml.stream.*;

public class XmlTcpClient {
    private static final int SERVER_PORT = 5555;
    private static Scanner scanner;
    private static XMLStreamWriter xmlStreamWriter;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(args[0], SERVER_PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);

            boolean ok = false;

            while (!ok) {
                StringWriter stringWriter = new StringWriter();
                XMLOutputFactory xmlOutFac = XMLOutputFactory.newInstance();
                xmlStreamWriter = xmlOutFac.createXMLStreamWriter(stringWriter);

                int choice = loop();
                
                if (choice != 2) {
                	out.writeUTF(stringWriter.getBuffer().toString());
                } else {
                	out.writeUTF("print");
                }
                stringWriter.close();
                String data = in.readUTF();
                System.out.println("\nReceived: " + data);
                ok = data.equals("ok");
            }
            scanner.close();
            socket.close();
        } catch(UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch(XMLStreamException e) {
            System.out.println("XML:" + e.getMessage());
        }catch(EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch(IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    private static int loop() {
        int choice = printMenu();

        if (choice != 2) {
	        try {
	        	xmlStreamWriter.writeStartDocument();
	            xmlStreamWriter.writeStartElement(choice == 0 ? "Student" : "Dozent");
	
	            System.out.print("\nVorname: ");
	            xmlStreamWriter.writeStartElement("Surname");
	            xmlStreamWriter.writeCharacters(scanner.next());
	            xmlStreamWriter.writeEndElement();
	
	            System.out.print("Nachname: ");
	            xmlStreamWriter.writeStartElement("Lastname");
	            xmlStreamWriter.writeCharacters(scanner.next());
	            xmlStreamWriter.writeEndElement();
	
	            xmlStreamWriter.writeStartElement("Wohnort");
	
	            System.out.print("Strasse: ");
	            xmlStreamWriter.writeStartElement("Strasse");
	            xmlStreamWriter.writeCharacters(scanner.next());
	            xmlStreamWriter.writeEndElement();
	
	            System.out.print("Hausnummer: ");
	            xmlStreamWriter.writeStartElement("Hausnummer");
	            xmlStreamWriter.writeCharacters(scanner.next());
	            xmlStreamWriter.writeEndElement();
	
	            System.out.print("Postleitzahl: ");
	            xmlStreamWriter.writeStartElement("Postleitzahl");
	            xmlStreamWriter.writeCharacters(scanner.next());
	            xmlStreamWriter.writeEndElement();
	
	            System.out.print("Stadt: ");
	            xmlStreamWriter.writeStartElement("Stadt");
	            xmlStreamWriter.writeCharacters(scanner.next());
	            xmlStreamWriter.writeEndElement();
	
	            xmlStreamWriter.writeEndElement();
	
	            switch(choice) {
	                case 0:
	                    System.out.print("Matrikelnummer: ");
	                    xmlStreamWriter.writeStartElement("Matrikelnummer");
	                    xmlStreamWriter.writeCharacters(scanner.next());
	                    xmlStreamWriter.writeEndElement();
	
	                    System.out.print("Studienrichtung: ");
	                    xmlStreamWriter.writeStartElement("Studienrichtung");
	                    xmlStreamWriter.writeCharacters(scanner.next());
	                    xmlStreamWriter.writeEndElement();
	
	                    System.out.print("Fachsemester: ");
	                    xmlStreamWriter.writeStartElement("Fachsemester");
	                    xmlStreamWriter.writeCharacters(scanner.next());
	                    xmlStreamWriter.writeEndElement();
	                    break;
	                case 1:
	                    System.out.print("Personalnummer: ");
	                    xmlStreamWriter.writeStartElement("Personalnummer");
	                    xmlStreamWriter.writeCharacters(scanner.next());
	                    xmlStreamWriter.writeEndElement();
	
	                    System.out.print("Fachbereich: ");
	                    xmlStreamWriter.writeStartElement("Fachbereich");
	                    xmlStreamWriter.writeCharacters(scanner.next());
	                    xmlStreamWriter.writeEndElement();
	                    break;
	            }
	
	            xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndDocument();
	            xmlStreamWriter.flush();
                xmlStreamWriter.close();
	        } catch(XMLStreamException e) {
	            System.out.println("XML:" + e.getMessage());
	        }
        } 
        return choice;
    }

    private static int printMenu() {
        int input;
        do {
            System.out.println("[0] Add Student");
            System.out.println("[1] Add Dozent");
            System.out.println("[2] Print");
            System.out.print("\nChoice: ");
            input = scanner.nextInt();
        } while (!(input > -1 && input < 3));
        return input;
    }
}
