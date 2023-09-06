// Fibonacci3.java
import java.math.BigInteger;

public class Fibonacci3{
    public static BigInteger fibo3(BigInteger num){
        if(num.equals(BigInteger.ZERO) || num.equals(BigInteger.ONE)) 
            return num;
        else{
            return fibo3(num.subtract(BigInteger.ONE)).add(
                   fibo3(num.subtract(BigInteger.TWO)));
        }
    } // Fim do método
}