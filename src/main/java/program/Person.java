package program;

public class Person {
    private String navn;
    private int alder;
    private int fodselsdato;
    private String epost;
    private String telefonnummer;

    public Person(String navn, int alder, int fodselsdato, String epost, String telefonnummer) {
        this.navn = navn;
        this.alder = alder;
        this.fodselsdato = fodselsdato;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
    }
}
