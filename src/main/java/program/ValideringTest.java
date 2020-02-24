package program;

import org.junit.jupiter.api.Test;
import program.Exceptions.InvalidAgeException;

import java.io.IOException;
import java.time.LocalDate;

class ValideringTest {
    PersonRegister person = new PersonRegister("ole", LocalDate.now(),
            "minmail@main.com", "98765432");

    ValideringTest() throws IOException {
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
    void validerDato() {
        if(!Validering.validerDato(person.getDato())) System.out.println("Feil år!");
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