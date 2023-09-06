// Carta.java
// Representa uma carta genéric

public class Carta{
    private String face; // null
    private String naipe; // null

    public Carta(){
        // this("Preencher", "Preencher");
        this.face = "Preencher"; 
        this.naipe = "Preencher";
    } // Fim do construtor
    public Carta(String face, String naipe){
        this.face = face;
        this.naipe = naipe;
    } // Fim do construtor (String, String)
    public void setFace(String face){
        this.face = face;
    }
    public void setNaipe(String naipe){
        this.naipe = naipe;
    }
    public String getFace(){
        return this.face;
    }
    public String getNaipe(){
        return naipe;
    }
    @Override
    public String toString(){
        return String.format("%s de %s", face, naipe);
    }
    public static void main(String[] args) {
        Carta ref1 = new Carta();
        Carta ref2 = new Carta();
        Carta ref3 = new Carta("Ás", "Copas");
        Carta ref4 = new Carta("Dois", "Ouros");
        System.out.println(ref1.toString());
        System.out.println(ref2);
        System.out.println(ref3);
        System.out.println(ref4.toString());
        ref1.setFace("Rainha");
        ref1.setNaipe("Espadas");
        System.out.println(ref1.toString());
        ref2.setFace("Nove");
        ref2.setNaipe("Paus");
        System.out.println(ref2);
    }
} // Fim da classe