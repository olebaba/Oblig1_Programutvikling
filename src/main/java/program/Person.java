package program;

import program.Exceptions.InvalidAgeException;

public class Person {

    private String navn;
    private int alder;
    private int fødselsdag;
    private int fødselsmnd;
    private int fødselsår;
    private String epost;
    private String telefonnummer;

    public Person(String navn, int alder, int fødselsdag, int fødselsmnd, int fødselsår, String epost, String telefonnummer) throws InvalidAgeException {
        this.navn = navn;
        setAlder(alder);
        this.fødselsdag = fødselsdag;
        this.fødselsmnd = fødselsmnd;
        this.fødselsår = fødselsår;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) throws InvalidAgeException {
        if(!Validering.validerAlder(alder)){
            throw new InvalidAgeException("Feil alder");
        }
        this.alder = alder;
    }

    public String getNavn() {
        return navn;
    }

    public int getFødselsdag() {
        return fødselsdag;
    }

    public int getFødselsmnd() {
        return fødselsmnd;
    }

    public int getFødselsår() {
        return fødselsår;
    }

    public String getEpost() {
        return epost;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }
}
