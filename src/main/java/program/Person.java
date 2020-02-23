package program;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import program.Exceptions.*;

public class Person {

    private SimpleStringProperty navn = new SimpleStringProperty();
    private SimpleIntegerProperty alder = new SimpleIntegerProperty();
    private SimpleIntegerProperty fodselsdag = new SimpleIntegerProperty();
    private SimpleIntegerProperty fodselsmnd = new SimpleIntegerProperty();
    private SimpleIntegerProperty fodselsar = new SimpleIntegerProperty();
    private SimpleStringProperty epost = new SimpleStringProperty();
    private SimpleStringProperty telefonnummer = new SimpleStringProperty();

    public Person(String navn, int alder, int fodselsdag, int fodselsmnd, int fodselsar, String epost,
                  String telefonnummer) throws InvalidAgeException, InvalidEmailException, InvalidTlfException,
            InvalidDateException, InvalidNameException {
        setNavn(navn);
        setAlder(alder);
        setFodselsdag(fodselsdag);
        setFodselsmnd(fodselsmnd);
        setFodselsar(fodselsar);
        setEpost(epost);
        setTelefonnummer(telefonnummer);    }

    public int getAlder() {
        return alder.getValue();
    }

    public void setAlder(int alder) throws InvalidAgeException {
        if(!Validering.validerAlder(alder)){
            throw new InvalidAgeException("Feil alder");
        }
        this.alder.set(alder);
    }

    public String getNavn() {
        return navn.getValue();
    }

    public void setNavn(String navn) throws InvalidNameException {
        if(!Validering.validerNavn(navn)){
            throw new InvalidNameException("navn fungerer ikke");
        }
        this.navn.set(navn);
    }

    public int getFodselsdag() {
        return fodselsdag.getValue();
    }

    public void setFodselsdag(int fodselsdag) throws InvalidDateException {
        if(!Validering.validerFodselsdag(fodselsdag)){
            throw new InvalidDateException("fodseldag fungerer ikke");
        }
        this.fodselsdag.set(fodselsdag);
    }

    public int getFodselsmnd() {
        return fodselsmnd.getValue();
    }

    public void setFodselsmnd(int fodselsmnd) throws InvalidDateException {
        if(!Validering.validerFodselsmnd(fodselsmnd)){
            throw new InvalidDateException("fodselmnd fungerer ikke");
        }
        this.fodselsmnd.set(fodselsmnd);
    }

    public int getFodselsar() {
        return fodselsar.getValue();
    }

    public void setFodselsar(int fodselsar) throws InvalidDateException {
        if(!Validering.validerFodselsar(fodselsar)){
            throw new InvalidDateException("fodselår fungerer ikke");
        }
        this.fodselsar.set(fodselsar);
    }

    public String getEpost() {
        return epost.getValue();
    }

    public void setEpost(String epost) throws InvalidEmailException {
        if(!Validering.validerEpost(epost)){
            throw new InvalidEmailException("Dette går ikke!");
        }
        this.epost.set(epost);
    }

    public String getTelefonnummer() {
        return telefonnummer.getValue();
    }

    public SimpleStringProperty telefonnummerProperty(){return this.telefonnummer;}

    public void setTelefonnummer(String Telefonnummer) throws InvalidTlfException {
        if(!Validering.validerTlf(Telefonnummer)){
            throw new InvalidTlfException("tlf går ikke!");
        }
        this.telefonnummer.set(Telefonnummer);
    }

    @Override
    public String toString() {
        return "Person{" +
                "navn='" + navn.getValue() + '\'' +
                ", alder=" + alder.getValue() +
                ", fodselsdag=" + fodselsdag.getValue() +
                ", fodselsmnd=" + fodselsmnd.getValue() +
                ", fodselsar=" + fodselsar.getValue() +
                ", epost='" + epost.getValue() + '\'' +
                ", telefonnummer='" + telefonnummer.getValue() + '\'' +
                '}';
    }
}
