package program;

import java.time.LocalDate;
import java.time.Year;

public class Validering {

    public static String validerPerson(Person person){
        StringBuilder out = new StringBuilder();

        if(!validerNavn(person.getNavn())) out.append("Feil navn, ");
        if(!validerAlder(person.getAlder())) out.append("Feil alder, ");
        if(!validerDato(person.getDato())) out.append("Feil år, ");
        if(!validerEpost(person.getEpost())) out.append("Feil epost, ");
        if(!validerTlf(person.getTelefonnummer())) out.append("Feil nummer.");

        return out.toString();
    }

    public static boolean validerAlder(int alder){
        return (alder >= 0 && alder < 120);
    }

    public static boolean validerNavn(String navn){
        return ((navn != null)
                && (!navn.equals(""))
                && (navn.matches("^[a-zæoaA-ZÆoa ]*$")));
    }

    public static boolean validerDato(LocalDate dato) {
        //LocalDate ld = LocalDate.parse(dato);
        return  (dato.getYear() > 1900 && dato.getYear() <= Integer.parseInt(String.valueOf(Year.now()))) ;
    }

    public static boolean validerEpost(String epost){
        return ((epost != null)
                && (!epost.equals(""))
                && (epost.matches("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})")));
    }

    public static boolean validerTlf(String tlf){
        return ((tlf != null) && (!tlf.equals("")) && (tlf.matches("(\\d{8})")));
    }
}
