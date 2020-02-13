package program;

import java.time.Year;

public class Validering {

    public static boolean validerAlder(int alder){
        return (alder > 0 && alder < 120);
    }

    public static boolean validerNavn(String navn){
        return ((navn != null)
                && (!navn.equals(""))
                && (navn.matches("^[a-zæøåA-ZÆØÅ ]*$")));
    }

    public static boolean validerFodselsmnd(int mnd){
        return (mnd > 0 && mnd < 13);
    }

    public static boolean validerFodselsdag(int dag){
        return (dag > 0 && dag < 32);
    }

    public static boolean validerFodselsar(int ar){
        return (ar > 1900 && ar < Integer.parseInt(String.valueOf(Year.now())));
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
