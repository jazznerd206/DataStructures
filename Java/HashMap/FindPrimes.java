import java.util.*;

public class FindPrimes {
    public static void main(String[] args) {
        List<Integer> l = new LinkedList<>();
        for (int i = 7; i > 0 && i < Integer.MAX_VALUE; i++) {
            if (isPrime(i)) {
                l.add(i);
                i = 2 * i;
            }
        }
        System.out.print("{");
        for (Integer i : l) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println("}");
    }

    public static boolean isPrime(int x) {
        if (x == 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(x + 1) + 1; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

}
