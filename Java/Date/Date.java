// Classe Date
// import java.lang.*; // Importação desnecessária

public class Date{
    private int dia, mes, ano;
    public Date(){
        this(3, 3, 2023);
    }
    public Date(int dia, int mes, int ano){
        if(dia >= 1 && dia <= 30)
            this.dia = dia;
        if(mes >= 1 && mes <= 12)
            this.mes = mes;
        if(ano >= 0)
            this.ano = ano;
    } // Fim do construtor
    public void setDia(int dia){
        if(dia >= 1 && dia <= 30)
            this.dia = dia;
    } // Fim do método setDia
    public void setMes(int mes){
        if(mes >= 1 && mes <= 12)
            this.mes = mes;
    } // Fim do setMes
    public void setAno(int ano){
        if(ano >= 0)
            this.ano = ano;
    } // Fim do setAno
    public int getDia(){
        return this.dia;
    } // Fim do getDia
    public int getMes(){
        return mes;
    } // Fim do getMes
    public int getAno(){
        return ano;
    } // Fim do getAno
    public void displayDate(){
        System.out.printf("Ano atual:%n%02d/%02d/%02d\n", dia, mes, ano); // print(dia+"/"+mes+"/"+ano), println()
    } // Fim do método displayDate

    @Override
    public String toString(){
        return String.format("Objeto Date\nData: %d/%d/%d\nOriginal: %s", dia, mes, ano, super.toString());
    }  // Fim do toString
} // Fim da classe