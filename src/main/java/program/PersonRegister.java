package program;

import program.Exceptions.*;

import java.io.Serializable;

public class PersonRegister extends Person implements Serializable {
    public PersonRegister(String navn, int alder, int fodselsmnd, int fodselsdag,
                          int fodselsar, String epost, String telefonnummer) throws java.io.IOException {
        super(navn, alder, fodselsmnd, fodselsdag, fodselsar, epost, telefonnummer);
    }
}
