package program;

import program.Exceptions.*;

public class Person {

    private String navn;
    private int alder;
    private int fodselsdag;
    private int fodselsmnd;
    private int fodselsar;
    private String epost;
    private String telefonnummer;

    public Person(String navn, int alder, int fodselsdag, int fodselsmnd, int fodselsar, String epost, String telefonnummer) throws InvalidAgeException, InvalidEmailException, InvalidTlfException, InvalidDateException, InvalidNameException {
        setNavn(navn);
        setAlder(alder);
        setFodselsdag(fodselsdag);
        setFodselsmnd(fodselsmnd);
        setFodselsar(fodselsar);
        setEpost(epost);
        setTelefonnummer(telefonnummer);    }

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

    public void setNavn(String navn) throws InvalidNameException {
        if(!Validering.validerNavn(navn)){
            throw new InvalidNameException("navn fungerer ikke");
        }
        this.navn = navn;
    }

    public int getFodselsdag() {
        return fodselsdag;
    }

    public void setFodselsdag(int fodselsdag) throws InvalidDateException {
        if(!Validering.validerFodselsdag(fodselsdag)){
            throw new InvalidDateException("fodseldag fungerer ikke");
        }
        this.fodselsdag = fodselsdag;
    }

    public int getFodselsmnd() {
        return fodselsmnd;
    }

    public void setFodselsmnd(int fodselsmnd) throws InvalidDateException {
        if(!Validering.validerFodselsmnd(fodselsmnd)){
            throw new InvalidDateException("fodselmnd fungerer ikke");
        }
        this.fodselsmnd = fodselsmnd;
    }

    public int getFodselsar() {
        return fodselsar;
    }

    public void setFodselsar(int fodselsar) throws InvalidDateException {
        if(!Validering.validerFodselsar(fodselsar)){
            throw new InvalidDateException("fodselår fungerer ikke");
        }
        this.fodselsar = fodselsar;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) throws InvalidEmailException {
        if(!Validering.validerEpost(epost)){
            throw new InvalidEmailException("Dette går ikke!");
        }
        this.epost = epost;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String Telefonnummer) throws InvalidTlfException {
        if(!Validering.validerTlf(telefonnummer)){
            throw new InvalidTlfException("tlf går ikke!");
        }
        this.telefonnummer = telefonnummer;
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
