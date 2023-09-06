// Conta: representa uma conta bancária

public class Conta{
    private String nome, sobrenome;
    private int nConta;
    private double saldo;

    public Conta(){} // Fim do construtor
    
    public Conta(String nome, String sobrenome, int nConta, double saldo){
        if(nome != "")
            this.nome = nome;
        if(sobrenome != "")
            this.sobrenome = sobrenome;
        if(nConta > 0)
            this.nConta = nConta;
        if(saldo >= 0D)
            this.saldo += saldo;
    } // Fim do construtor (String, String, int, double)

    public Conta(String nome, String sobrenome){
        this.nome = nome;
        this.sobrenome = sobrenome;
    } // Fim do construtor (String, String)

    public void setSaldo(double saldo){
        if(saldo >= 0D) this.saldo += saldo; // this.saldo = this.saldo + saldo;
    } // Fim do método setSaldo

    public double getSaldo(){
        return saldo;
    } // Fim do método getSaldo()

    public void setNConta(int nConta){
        if(nConta > 0)
            this.nConta = nConta;
    } // Fim do método setNConta()

    public int getNConta(){
        return nConta;
    } // Fim do método getNConta()

    public void setNome(String nome){
        if(nome != "")
            this.nome = nome;
    } // Fim do método setNome()

    public String getNome(){
        return this.nome;
    } // Fim do getNome()

    public void setSobrenome(String sobrenome){
        if(sobrenome != "")
            this.sobrenome = sobrenome;
    } // Fim do método setSobrenome

    public String getSobrenome(){
        return sobrenome;
    } // Fim do método getSobrenome

    @Override
    public String toString(){
        return String.format("Conta: %d\nCliente: %s %s\nSaldo: %.2f\n", 
        nConta, nome, sobrenome, saldo);
    } // Fim do toString

    /**
    public void exibir(){
        System.out.printf("Conta: %d\nCliente: %s %s\nSaldo: %.2f\n", 
                                  nConta, nome, sobrenome, saldo);
    } // Fim do método exibir
    */
    
    public static void main(String[] args) {
        Conta ob1 = new Conta();
        Conta ob2 = ob1;
        Conta ob3 = new Conta("José", "Maria", 1000, 1000D);
        System.out.println(ob1.toString());    
        System.out.println(ob2);
        System.out.println(ob3);
        ob1.setNome("João");
        ob2.setNome("Ana");
        System.out.println(ob1);
        System.out.println(ob2);
    } // Fim do main

} // Fim da classe Conta