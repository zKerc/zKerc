// Classe DateTeste

public class DateTeste{
    public static void main(String[] args) {
        Date ob1 = new Date();
        Date ob2 = new Date(10, 12, 2023);
        Date ob3 = new Date(-999, -2, 200);
        ob1.displayDate();
        ob2.displayDate();
        ob3.displayDate();
        ob3.setDia(6);
        ob3.setAno(2023);
        ob3.setMes(8);
        ob3.displayDate();
        System.out.println(ob1.toString());
        System.out.println(ob2);
        System.out.println(ob3);
    } // Fim do main
} // Fim da classe