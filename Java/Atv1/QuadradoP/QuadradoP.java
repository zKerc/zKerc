package Atv1.QuadradoP;

// QuadradoP.java

public class QuadradoP{
    private char c = '*';
    private char d = '#';

    public void quadradoP(int num, char c, char d){
        this.c = c;
        this.d = d;

        for(int i = 0; i < num; i++){
            for(int j = 0; j < num; j++){
                if(i == j || i + j == num - 1){
                    System.out.printf("%c ", c);

                } // Fim do if

                else{
                    System.out.printf("%c ", d);

                } // Fim do else

            } // Fim do for interno

            System.out.println();

        } // Fim do for externo

        System.out.println();
        
    } // Fim do método

} // Fim da classe