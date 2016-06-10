import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import javax.xml.XMLConstants;
import javax.xml.validation.*;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Validator validator;
    Socket cSocket;

    public Connection(Socket cSocket) {
        try {
            this.cSocket = cSocket;
            in = new DataInputStream(cSocket.getInputStream());
            out = new DataOutputStream(cSocket.getOutputStream());

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("StudentDozent.xsd"));
            validator = schema.newValidator();

            this.start();
        } catch(SAXException e) {
            System.out.println("SAX:" + e.getMessage());
        } catch(IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        boolean done = true;

        do {
            String data = "";
            done = true;
            try {
                 data = in.readUTF();
                 validator.validate(new StreamSource(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8))));
                 data = "";
            } catch(SAXException e) {
                data = e.getMessage();
                done = false;
            } catch(EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch(IOException e) {
                System.out.println("IO:" + e.getMessage());
            }

            try {
                if (data.equals(""))
                    data = "ok";
                out.writeUTF(data);
            } catch(EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch(IOException e) {
                System.out.println("IO:" + e.getMessage());
            }
        } while (!done);

        try {
            cSocket.close();
        } catch(IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }
}
