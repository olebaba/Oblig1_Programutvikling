package program;

class ValideringTest {

    @org.junit.jupiter.api.Test
    void validerAlder(String name) {
    }

    @org.junit.jupiter.api.Test
    void validerNavn() {
        String name = "Ole Bastian Løchen";
        if(Validering.validerNavn(name)) System.out.println("ja");
        else System.out.println("nei");
    }
}