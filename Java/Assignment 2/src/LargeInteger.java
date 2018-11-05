import java.math.BigInteger;
import java.util.ArrayList;

public class LargeInteger {
    private ArrayList<Integer> digits;
    private String strInt;
    private int sign;

    private LargeInteger(String s) {
        StringBuilder sb = new StringBuilder(s);
        sign = sb.charAt(0) == '-' ? -1 : 1;
        sb.delete(0, 0);
        sb = sb.reverse();

        digits = new ArrayList<>(s.length() - sign);
        //adds the digits backwards for easier growth;
        for (char c : sb.toString().toCharArray()) {
            digits.add(c & 0x0F);
        }
    }

    private LargeInteger(LargeInteger other) {
        digits = (ArrayList<Integer>) other.digits.clone();
    }

    public static LargeInteger add(LargeInteger u, LargeInteger v) {

        return  new LargeInteger("demo");
    }

    LargeInteger pow(LargeInteger u, LargeInteger v) {
        return new LargeInteger("0");
    }

    public static int parseInt(LargeInteger b) {
        return Integer.parseInt(b.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : digits) {
            sb.append(i);
        }
        return sb.reverse().toString();
    }
    private boolean isGreaterThan(LargeInteger other){
        if(this == other) return  false;
        if (other.digits.size() < this.digits.size()) return true;
        if (other.digits.size() > this.digits.size()) return false;
        //equal check highest order digits
        int a,b;
        for(int i = this.digits.size() -1; i >= 0 ; --i){
            a = this.digits.get(i);
            b = other.digits.get(i);
            if(a > b) {
                return true;
            }else if(a < b) {
                return  false;
            }
        }
        //they are equalk
        return  false;
    }

    public static void main(String[] args) {

        LargeInteger a = new LargeInteger("110");
        LargeInteger b = new LargeInteger("109");
        System.out.printf("a > b is %b\n",a.isGreaterThan(b));


    }
}
