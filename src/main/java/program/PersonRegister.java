package program;

import program.Exceptions.InvalidAgeException;

public class PersonRegister extends Person{
    public PersonRegister(String navn, int alder, int fødselsmnd, int fødselsdag, int fødselsår, String epost, String telefonnummer) throws InvalidAgeException {
        super(navn, alder, fødselsmnd, fødselsdag, fødselsår, epost, telefonnummer);
    }
}
