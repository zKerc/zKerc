// Fibonacci1.java

// 0 1 1 2 3 5 8 13 21 34 55 89...

public class Fibonacci1{
    public static long fibo1(long num){
        if(num < 2) // if(num == 0 || num == 1) 
            return num;
        else
            return fibo1(num -1) + fibo1(num - 2);
    } // Fim do método
}