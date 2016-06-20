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

/**
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 */
public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Validator validator;
    Socket cSocket;

    // File in which the Personobjects are saved
    final File FILE = new File("persons.ser");

    List<IPerson> persons;

    public Connection(Socket cSocket) {
        try {
            this.cSocket = cSocket;
            in = new DataInputStream(cSocket.getInputStream());
            out = new DataOutputStream(cSocket.getOutputStream());

            // Setup Validator
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("Server/StudentDozent.xsd"));
            validator = schema.newValidator();

            // Start the Thread
            this.start();
        } catch(SAXException e) {
            System.out.println("SAX:" + e.getMessage());
        } catch(IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    /**
     * Saves the Personlist to File
     */
    private void savePersons() throws IOException {
        // If the File don't exists create it
    	if (!FILE.exists())
    		FILE.createNewFile();
        // Save
    	try (final ObjectOutputStream out = new ObjectOutputStream(
    			new BufferedOutputStream(new FileOutputStream(FILE)))) {
    		out.writeObject(persons);
    	}
    }

    /**
     * Load the Personlist from File
     */
    @SuppressWarnings("unchecked")
	private void loadPersons() throws IOException, ClassNotFoundException {
        // If the File exists, Load it
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
            // First load
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
                // Read Client-Input
                 data = in.readUTF();
                 // Validate that Input if it not is "print"
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
                    // Add the read person to the list and save the list afterwards
                    if (persons == null)
                    	persons = new ArrayList<IPerson>();
                    persons.add(reader.read(data));
                    savePersons();
                } else if (data.equals("print")) {
                    // Gives the Client the repr. of the list
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
        } while (!done); // While there was wrong input or a "print"-Call

        try {
            cSocket.close();
        } catch(IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }
}
