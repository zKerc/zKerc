package Atv1.QuestaoPI;

// QuestaoPI.java

public class QuestaoPI {
    private static double pi = 0D;

    public static double PI(int num) {
        for (int i = 1, j = 1; i <= num; i++, j += 2) {
            if (i % 2 == 0) {
                pi -= 4D / j;
            } else {
                pi += 4D / j;
            }
        } // Fim do for
        return pi;
    } // Fim do método double
} // Fim da classe
