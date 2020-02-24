package program;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import program.Exceptions.*;

import java.time.LocalDate;

public class Person {

    private SimpleStringProperty navn = new SimpleStringProperty();
    private SimpleIntegerProperty alder = new SimpleIntegerProperty();
    private SimpleStringProperty dato = new SimpleStringProperty();
    private SimpleStringProperty epost = new SimpleStringProperty();
    private SimpleStringProperty telefonnummer = new SimpleStringProperty();

    public Person(String navn, LocalDate dato, String epost,
                  String telefonnummer) throws InvalidAgeException, InvalidEmailException, InvalidTlfException,
            InvalidDateException, InvalidNameException {
        setNavn(navn);
        setAlder(dato);
        setDato(dato);
        setEpost(epost);
        setTelefonnummer(telefonnummer);    }

    public int getAlder() {
        return alder.getValue();
    }

    public void setAlder(LocalDate dato) throws InvalidAgeException {
        int alder = (LocalDate.now().getYear()-dato.getYear());
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

    public LocalDate getDato() {
        return LocalDate.parse(dato.get());
    }

    public void setDato(LocalDate dato) throws InvalidDateException {
        if(!Validering.validerDato(dato)){
            throw new InvalidDateException("Dato fungerer ikke");
        }
        this.dato.set(String.valueOf(dato));
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
                ", dato=" + dato.getValue() +
                ", epost='" + epost.getValue() + '\'' +
                ", telefonnummer='" + telefonnummer.getValue() + '\'' +
                '}';
    }
}
