// TesteFibo.java
import java.math.BigInteger;
public class TesteFibo{
    public static final BigInteger TRES = BigInteger.valueOf(3);
    public static void main(String[] args) {
        //Integer.MAX_VALUE
        long inicio = System.currentTimeMillis();
        System.out.println(Fibonacci3.fibo3(BigInteger.valueOf(20)));
        System.out.println(Fibonacci2.fibo2(20-1));
        long fim = System.currentTimeMillis();
        System.out.printf("%dms%n", fim - inicio);
    }
}