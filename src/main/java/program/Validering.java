package program;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validering {

    public static boolean validerAlder(int alder){
        if(alder<0 || alder>120){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean validerNavn(String navn){
        return ((navn != null)
                && (!navn.equals(""))
                && (navn.matches("^[a-zæøåA-ZÆØÅ ]*$")));
    }
}
