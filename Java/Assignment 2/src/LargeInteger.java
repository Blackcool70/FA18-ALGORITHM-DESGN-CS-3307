import java.util.ArrayList;
import java.util.Collections;

public class LargeInteger {
    private ArrayList<Integer> digits;
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
        this.digits = (ArrayList<Integer>) other.digits.clone();
        this.sign = other.sign;
    }

    private static void padWZeros(LargeInteger a, int n){
        while(n > 0){
            a.digits.add(0);
            --n;
        }
    }
    public static LargeInteger add(LargeInteger u, LargeInteger v) {
        LargeInteger largest, other, result;
        if (u.isGreaterThan(v)) {
            largest = u;
            other = v;
        } else {
            //v is greater or equal
            largest = v;
            other = u;
        }
        padWZeros(other,largest.digits.size()-other.digits.size());

        result = new LargeInteger(other);
        int sum,valu,valv;
        int next;
        for (int i = 0; i < largest.digits.size(); ++i) {
            next = i + 1;
            valu = largest.digits.get(i);
            if(next >= result.digits.size()) result.digits.add(0);
            valv = result.digits.get(i);
            sum =  valu + valv;
            result.digits.set(next, result.digits.get(next) + sum / 10);
            result.digits.set(i, sum % 10);
        }
        return result;
    }
    //returns the  mod 10^k
    private static LargeInteger sMod(LargeInteger a, int k){
        LargeInteger result ;
        if(k > 0){
            result = new LargeInteger(a);
            result.digits.clear();
            for(int i = 0; i < k; ++i)
                result.digits.add(a.digits.get(i));
        }else {
            result = new LargeInteger("0");
        }
        return result;
    }
    //raises a number 10 ^k
    private static LargeInteger sPow(LargeInteger a, int k){
        LargeInteger result = new LargeInteger(a);
        Collections.reverse(result.digits);
        for(int i = 0; i < k; ++i){
            result.digits.add(0);
        }
        Collections.reverse(result.digits);
        return  result;
    }
    //divies to 10^k
    private static LargeInteger sDiv(LargeInteger a, int k){
        StringBuilder sb = new StringBuilder();
        for(int i = k; i < a.digits.size(); ++i){
            sb.append(a.digits.get(i));
        }
        return  new LargeInteger(sb.reverse().toString());
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : digits) {
            sb.append(i);
        }
        return sb.reverse().toString();
    }

    private boolean equals(LargeInteger other) {
        if (this == other) return true;
        if (this.sign != other.sign) return false;
        return this.digits.equals(other.digits);
    }

    private boolean isGreaterThan(LargeInteger other) {
        if (this == other) return false;
        if (other.digits.size() < this.digits.size()) return true;
        if (other.digits.size() > this.digits.size()) return false;
        //equal check highest order digits
        int a, b;
        for (int i = this.digits.size() - 1; i >= 0; --i) {
            a = this.digits.get(i);
            b = other.digits.get(i);
            if (a > b) {
                return true;
            } else if (a < b) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LargeInteger a = new LargeInteger("12379");
        LargeInteger b = new LargeInteger("28");
        LargeInteger c =  LargeInteger.sDiv(a,3);
        System.out.printf("%s\n", c);

    }
}
