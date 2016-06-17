package Server;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.validation.*;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Validator validator;
    Socket cSocket;
    
    final File FILE = new File("persons.ser");
    
    List<IPerson> persons;

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
    
    private void savePersons() throws IOException {
    	if (!FILE.exists())
    		FILE.createNewFile();
    	try (final ObjectOutputStream out = new ObjectOutputStream(
    			new BufferedOutputStream(new FileOutputStream(FILE)))) {
    		out.writeObject(persons);
    	}
    }
    
    @SuppressWarnings("unchecked")
	private void loadPersons() throws IOException, ClassNotFoundException {
    	if (FILE.exists()){
	    	try (final ObjectInputStream in = new ObjectInputStream(
	    			new BufferedInputStream(new FileInputStream(FILE)))) {
	    		persons = (List<IPerson>)in.readObject();
	    	}
    	}
    }

    @Override
    public void run() {
        boolean done = true;
        XmlReader reader = new XmlReader();
        try {
			loadPersons();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        do {
            String data = "", dataERR = "ok";
            done = true;
            try {
                 data = in.readUTF();
                 if (!data.equals("print"))
                	 validator.validate(new StreamSource(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8))));
                 else
                	 done = false;
            } catch(SAXException e) {
            	dataERR = e.getMessage();
                done = false;
            } catch(EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch(IOException e) {
                System.out.println("IO:" + e.getMessage());
            }

            try {
                if (!data.equals("") && !data.equals("print") && dataERR.equals("ok")) {
                    if (persons == null)
                    	persons = new ArrayList<IPerson>();
                    persons.add(reader.read(data));
                    savePersons();
                } else if (data.equals("print")) {
                	StringBuilder strBuilder = new StringBuilder("Liste:\n");
                	for (IPerson p : persons){
                    	strBuilder.append(p);
                    	strBuilder.append("\n");
                	}
                	out.writeUTF(strBuilder.toString());
                }
                if (!data.equals("print"))
                	out.writeUTF(dataERR);
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
