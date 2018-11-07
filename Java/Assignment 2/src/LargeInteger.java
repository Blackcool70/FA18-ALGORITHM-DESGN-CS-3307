import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Jecsan Blanco
 * 2018FA ALGORITHM DESGN/ANAL (CS-3307-01)
 * This program allows the user to multiply 2 large natural numbers.
 * The functions  that start with s stand for "special" as they do not
 * reflect an actual operations on special cases used for this project.
 * */
public class LargeInteger {
    private ArrayList<Integer> digits;
    /**
     * Creates a LargeInteger which can hold and multiply bigger numbers.
     */
    private LargeInteger(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        digits = new ArrayList<>(s.length());
        if (s.length() != 0) {
            //adds the digits backwards for easier growth;
            for (char c : sb.toString().toCharArray()) {
                digits.add(c & 0x0F);
            }
        } else {
            digits.add(0);
        }
    }

    /**
     * Copy constructor creates a deep copy
     * @param other another LargeInteger
     */
    private LargeInteger(LargeInteger other) {
        this.digits = new ArrayList<>(other.digits);
    }

    /**
     * "Natural addition" of two LargeInteger numbers, only works with positives.
     * @param u  LargeInteger
     * @param v  Large Integer
     * @return   the sum of the two LargeIntegers
     */
    private static LargeInteger sAdd(LargeInteger u, LargeInteger v) {
        LargeInteger largest, other, result;
        if (u.isGreaterThan(v)) {
            largest = u;
            other = v;
        } else {
            //v is greater or equal
            largest = v;
            other = u;
        }
  //      padWZeros(other, largest.digits.size() - other.digits.size());

        result = new LargeInteger(other);
        int sum, valu, valv;
        int next;
        for (int i = 0; i < largest.digits.size(); ++i) {
            next = i + 1;
            valu = largest.digits.get(i);
            //carried over to bigger than ordinal list.
            if (next >= result.digits.size()) result.digits.add(0);
            valv = result.digits.get(i);
            sum = valu + valv;
            //carry
            result.digits.set(next, result.digits.get(next) + sum / 10);
            //remainder
            result.digits.set(i, sum % 10);
        }
        return result;
    }

    /**
     * Helper function, removes any leading zeros mostly for display
     */
    private  void removeLeadingZeros(){
        Iterator<Integer> it = this.digits.iterator();
        int i = 0;
        while (it.hasNext() && it.next() == 0) {
            ++i;
        }
        this.digits.subList(0, i).clear();
        if(this.digits.size() == 0){
            this.digits.add(0);
        }
    }

    /**
     * Returns  a mod 10^k
     */

    private static LargeInteger sMod(LargeInteger a, int k) {
        LargeInteger result;

        if (k > 0) {
            result = new LargeInteger(a);
            result.digits.clear();
            for (int i = 0; i < k; ++i)
                result.digits.add(a.digits.get(i));
        } else {
            result = new LargeInteger("0");
        }
        return result;
    }

    /**
     * @return a * 10^k
     */
    private static LargeInteger sPow(LargeInteger a, int k) {
        LargeInteger result = new LargeInteger(a);
        Collections.reverse(result.digits);
        for (int i = 0; i < k; ++i) {
            result.digits.add(0);
        }
        Collections.reverse(result.digits);
        return result;
    }

    /**
     * @return a / 10^k
     */
    private static LargeInteger sDiv(LargeInteger a, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = k; i < a.digits.size(); ++i) {
            sb.append(a.digits.get(i));
        }
        return new LargeInteger(sb.reverse().toString());
    }

    /**
     * @return string representation of a LargeInteger
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collections.reverse(digits);
        removeLeadingZeros();
        for (int i : digits) {
                sb.append(i).append(" ");
        }
        Collections.reverse(digits);
        return sb.toString();
    }

    /**
     * @return if the two objects are equal
     */
    private boolean equals(LargeInteger other) {
        if (this == other) return true;
        this.removeLeadingZeros();
        other.removeLeadingZeros();
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

    private boolean isZero(){
        return !(this.toString().matches("[^0]+"));

    }
    private static LargeInteger prod(LargeInteger u, LargeInteger v) {
        LargeInteger x, y, w, z;
        int n, m, uSize, vSize;
        uSize = u.digits.size();
        vSize = v.digits.size();
        n = (uSize > vSize) ? uSize : vSize;
        if (u.isZero()|| v.isZero()) {
            return new LargeInteger("0");
        } else if (n <= 1) {
            return new LargeInteger(Integer.toString(u.digits.get(0) * v.digits.get(0)));
        }
        m = Math.floorDiv(n, 2);
        x = LargeInteger.sDiv(u, m);
        w = LargeInteger.sDiv(v, m);

        y = LargeInteger.sMod(u, m);
        z = LargeInteger.sMod(v, m);

        return sAdd(sPow(prod(x, w), 2 * m), sAdd(sPow(sAdd(prod(x, z), prod(w, y)), m), prod(y, z)));
    }

    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a large number: u");
            LargeInteger u = new LargeInteger(bf.readLine().trim());

            System.out.println("Enter a large number: v");
            LargeInteger v = new LargeInteger(bf.readLine().trim());

            System.out.printf("U = %s\n", u);
            System.out.printf("V = %s\n", v);
            System.out.println("Product u*v =");

            System.out.println(prod(u,v));

        } catch (IOException e) {

            System.err.println("Input errors! Aborting!");
            System.err.println(e);
        }
    }
}
