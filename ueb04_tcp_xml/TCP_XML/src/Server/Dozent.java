package Server;

import java.io.Serializable;

/**
 * Represents Profs
 * @author  Sascha Bussian, 549087
 *          Alexander Luedke, 548965
 * @version 1.0
 */
public class Dozent implements Serializable, IPerson {
	private static final long serialVersionUID = 3835600414353335826L;
	private String surname;
    private String lastname;
    private String strasse;
    private String hausnummer;
    private int postleitzahl;
    private String stadt;
    private String persNr;
    private String fachbereich;

	/* Setter + Getter */

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPersNr() {
        return persNr;
    }

    public void setPersNr(String persNr) {
        this.persNr = persNr;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }

    public String getFachbereich() {
        return fachbereich;
    }

    public String toString() {
		return this.getClass().getName() + ": "
				+ getSurname() + " "
				+ getLastname() + " "
				+ getPersNr() + " "
				+ getFachbereich();
    }
}
