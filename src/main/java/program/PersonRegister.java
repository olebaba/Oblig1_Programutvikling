package program;

import program.Exceptions.*;

import java.time.LocalDate;

public class PersonRegister extends Person{
    public PersonRegister(String navn, LocalDate dato, String epost, String telefonnummer)
            throws java.io.IOException {
        super(navn, dato, epost, telefonnummer);
    }
}
