package program;

import program.Exceptions.InvalidAgeException;
import program.Exceptions.InvalidEmailException;

public class Person {

    private String navn;
    private int alder;
    private int fodselsdag;
    private int fodselsmnd;
    private int fodselsar;
    private String epost;
    private String telefonnummer;

    public Person(String navn, int alder, int fodselsdag, int fodselsmnd, int fodselsar, String epost, String telefonnummer) throws InvalidAgeException {
        this.navn = navn;
        setAlder(alder);
        this.fodselsdag = fodselsdag;
        this.fodselsmnd = fodselsmnd;
        this.fodselsar = fodselsar;
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

    public int getFodselsdag() {
        return fodselsdag;
    }

    public int getFodselsmnd() {
        return fodselsmnd;
    }

    public int getFodselsar() {
        return fodselsar;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) throws InvalidEmailException {
        if(!Validering.validerEpost(epost)){
            throw new InvalidEmailException("Dette går ikke!");
        }
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    @Override
    public String toString() {
        return "Person{" +
                "navn='" + navn + '\'' +
                ", alder=" + alder +
                ", fodselsdag=" + fodselsdag +
                ", fodselsmnd=" + fodselsmnd +
                ", fodselsar=" + fodselsar +
                ", epost='" + epost + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                '}';
    }
}
