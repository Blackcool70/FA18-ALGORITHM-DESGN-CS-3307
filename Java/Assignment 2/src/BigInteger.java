public class BigInteger {
    private final  int[] INTS;

    public BigInteger(String s) {
        INTS = new int[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
           INTS[i++]  = c & 0x0F;
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i : INTS){
            sb.append(i);
        }
        return  sb.toString();
    }

    public static void main(String[] args) {

        BigInteger a = new BigInteger("123");
        System.out.println(a.toString());



    }
}
