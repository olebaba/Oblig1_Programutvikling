package program;

public class Validering {

    public static boolean validerAlder(int alder){
        if(alder<0 || alder>120){
            return false;
        }
        else{
            return true;
        }
    }
}
