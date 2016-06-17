package Server;

import java.io.Serializable;

public class Student implements Serializable, IPerson {
	private static final long serialVersionUID = 2217459913440211079L;
	private String surname;
    private String lastname;
    private String strasse;
    private String hausnummer;
    private int postleitzahl;
    private String stadt;
    private String matNr;
    private String studienrichtung;
    private int fachsemester;

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

    public String getMatNr() {
        return matNr;
    }

    public void setMatNr(String matNr) {
        this.matNr = matNr;
    }

    public String getStudienrichtung() {
        return studienrichtung;
    }

    public void setStudienrichtung(String studienrichtung) {
        this.studienrichtung = studienrichtung;
    }

    public int getFachsemester() {
        return fachsemester;
    }

    public void setFachsemester(int fachsemester) {
        this.fachsemester = fachsemester;
    }
    
    public String toString() {
		return this.getClass().getName() + ": " 
				+ getSurname() + " " 
				+ getLastname() + " "
				+ getMatNr() + " "
				+ getStudienrichtung() + " "
				+ getFachsemester();
    }
}
