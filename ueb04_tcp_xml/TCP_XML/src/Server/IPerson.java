package Server;

/**
 * Implimented by Persons
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 */
public interface IPerson {
    /* Setter + Getter */
    String getSurname();
    void setSurname(String surname);
    String getLastname();
    void setLastname(String lastname);
    String getStrasse();
    void setStrasse(String strasse);
    String getHausnummer();
    void setHausnummer(String hausnummer);
    int getPostleitzahl();
    void setPostleitzahl(int postleitzahl);
    String getStadt();
    void setStadt(String stadt);
}
