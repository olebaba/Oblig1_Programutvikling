package program;

import org.junit.jupiter.api.Test;
import program.Exceptions.InvalidAgeException;

class ValideringTest {
    PersonRegister person = new PersonRegister("ole", 20, 2, 27, 1999,
            "minmail@main.com", "98765432");

    ValideringTest() throws InvalidAgeException {
    }

    @org.junit.jupiter.api.Test
    void validerAlder() {
        if(!Validering.validerAlder(person.getAlder())) System.out.println("Feil alder!");
    }

    @org.junit.jupiter.api.Test
    void validerNavn() {
        if(!Validering.validerNavn(person.getNavn())) System.out.println("Feil navn!");
    }

    @Test
    void validerFodselsmnd() {
        if(!Validering.validerFodselsmnd(person.getFødselsmnd())) System.out.println("Feil mnd!");
    }

    @Test
    void validerFodselsdag() {
        if(!Validering.validerFodselsdag(person.getFødselsdag())) System.out.println("Feil dag!");
    }

    @Test
    void validerFodselsar() {
        if(!Validering.validerFodselsar(person.getFødselsår())) System.out.println("Feil år!");
    }

    @Test
    void validerEpost() {
        if(!Validering.validerEpost(person.getEpost())) System.out.println("Feil epost!");;
    }


    @Test
    void validerTlf() {
        if(!Validering.validerTlf(person.getTelefonnummer())) System.out.println("Feil nummer!");
    }
}