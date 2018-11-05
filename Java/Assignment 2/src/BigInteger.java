import java.lang.reflect.Array;
import java.util.ArrayList;

public class BigInteger {
    private ArrayList<Integer> digits;
    private int sign;

    private BigInteger(String s) {
        StringBuilder sb = new StringBuilder(s);
        sign = sb.charAt(0) == '-' ? -1 : 1;
        sb.delete(0,0);
        sb = sb.reverse();

        digits = new ArrayList<>(s.length() - sign);
        //adds the digits backwards for easier growth;
        for(char c: sb.toString().toCharArray()){
            digits.add(c & 0x0F);
        }
    }

    private BigInteger(BigInteger other) {
        digits = (ArrayList<Integer>) other.digits.clone();
    }

    public static BigInteger add(BigInteger u, BigInteger v) {
        int uSize = u.digits.size(), vSize = v.digits.size();
        return new BigInteger("temp");
    }

    BigInteger pow(BigInteger u, BigInteger v) {
        return new BigInteger("0");
    }

    public static int parseInt(BigInteger b) {
        return Integer.parseInt(b.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : digits) {
            sb.append(i);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        BigInteger a = new BigInteger("123");
        BigInteger b = new BigInteger(a);
        System.out.printf("a %s\n",a.toString());
        System.out.printf("b %s\n",b.toString());


    }
}
